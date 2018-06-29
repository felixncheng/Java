package com.chengmboy.action.schedule;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cheng_mboy
 */
public class ScheduleCenter {

    private static final int DEFAULT_EVENT_LOOP_THREADS;

    static {
        DEFAULT_EVENT_LOOP_THREADS = Math.max(1, Runtime.getRuntime().availableProcessors() * 2);
    }

    private ThreadPoolExecutor bossGroup;
    private DelayQueue<DelayTask> workQueue;
    private ThreadFactory threadFactory;
    private int nThreads;


    public ScheduleCenter(int nThreads, ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        this.nThreads = nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads;
        init();
    }

    public ScheduleCenter() {
        this.threadFactory = new ScheduleCenterFactory("ScheduleCenter");
        this.nThreads = DEFAULT_EVENT_LOOP_THREADS;
        init();
    }

    public ScheduleCenter(int nThreads) {
        this.threadFactory = new ScheduleCenterFactory("ScheduleCenter");
        this.nThreads = nThreads;
        init();
    }

    private void init() {
        bossGroup = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.NANOSECONDS,
                new LinkedBlockingQueue<>(nThreads), threadFactory);
        workQueue = new DelayQueue<>();
    }

    public void schedule(Runnable runnable, long delay, TimeUnit unit) {
        DelayTask task = new DelayTask(triggerTime(delay, unit), runnable);
        workQueue.add(task);
        bossGroup.submit(this::execute);
    }

    public void scheduleAtFixRate(Runnable runnable, long initDelay, long period, TimeUnit unit) {
        DelayTask task = new DelayTask(triggerTime(initDelay, unit), runnable, unit.toNanos(period));
        workQueue.add(task);
        bossGroup.submit(this::execute);
    }

    public void scheduleAtFixDelay(Runnable runnable, long initDelay, long delay, TimeUnit unit) {
        DelayTask task = new DelayTask(triggerTime(initDelay, unit), runnable, unit.toNanos(-delay));
        workQueue.add(task);
        bossGroup.submit(this::execute);
    }

    private void execute() {
        try {
            DelayTask task = workQueue.take();
            if (task != null) {
                Throwable thrown = null;
                try {
                    beforeExecute(Thread.currentThread(), task);
                    task.run();
                    if (task.isPeriodic()) {
                        reExecute(task);
                    } else {
                        task = null;//help gc
                    }
                } catch (Exception e) {
                    thrown = e;
                    throw e;
                } finally {
                    afterExecute(task, thrown);
                }
            }
        } catch (Exception ignored) {
        }
    }

    private void reExecute(DelayTask task) {
        long period = task.period;
        if (period > 0) {
            task.time += period;
        } else {
            task.time = triggerTime(-period, TimeUnit.NANOSECONDS);
        }
        workQueue.add(task);
        execute();
    }

    protected void afterExecute(Runnable r, Throwable t) {
    }

    protected void beforeExecute(Thread t, Runnable r) {
    }

    private long triggerTime(long delay, TimeUnit unit) {
        return System.nanoTime() + unit.toNanos(delay);
    }

    private static class ScheduleCenterFactory implements ThreadFactory {

        private static final AtomicInteger poolId = new AtomicInteger();

        private final AtomicInteger nextId = new AtomicInteger();
        private final String prefix;

        public ScheduleCenterFactory(String poolName) {
            prefix = poolName + '-' + poolId.incrementAndGet() + '-';
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + nextId.incrementAndGet());
        }
    }

    private class DelayTask implements Delayed, Runnable {

        private final Runnable task;
        private long time;
        private long period;

        public DelayTask(long time, Runnable task) {
            this.time = time;
            this.task = task;
            this.period = 0;
        }

        public DelayTask(long time, Runnable task, long period) {
            this.time = time;
            this.task = task;
            this.period = period;
        }

        @Override
        public void run() {
            task.run();
        }

        private boolean isPeriodic() {
            return period != 0;
        }

        private long triggerTime(long delay, TimeUnit unit) {
            return System.nanoTime() + unit.toNanos(delay);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return this.getDelay(TimeUnit.NANOSECONDS) > o.getDelay(TimeUnit.NANOSECONDS) ? 1 : -1;
        }
    }
}
