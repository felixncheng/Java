package com.chengmboy.action.schedule;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author cheng_mboy
 */
public class ScheduleCenter {

    private DelayQueue<DelayTask> workQueue = new DelayQueue<>();

    public static void main(String[] args) {
        ScheduleCenter center = new ScheduleCenter();
        center.schedule(() -> {
            System.out.println("Hello World " + System.currentTimeMillis() / 1000);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, TimeUnit.SECONDS);
    }

    public void schedule(Runnable runnable, long delay, TimeUnit unit) {
        DelayTask task = new DelayTask(triggerTime(delay, unit), runnable);
        workQueue.add(task);
        new Thread(this::execute).start();
    }

    public void scheduleAtFixRate(Runnable runnable, long initDelay, long period, TimeUnit unit) {
        DelayTask task = new DelayTask(triggerTime(initDelay, unit), runnable, unit.toNanos(period));
        workQueue.add(task);
        new Thread(this::execute).start();
    }

    public void scheduleAtFixDelay(Runnable runnable, long initDelay, long delay, TimeUnit unit) {
        DelayTask task = new DelayTask(triggerTime(initDelay, unit), runnable, unit.toNanos(-delay));
        workQueue.add(task);
        new Thread(this::execute).start();
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
