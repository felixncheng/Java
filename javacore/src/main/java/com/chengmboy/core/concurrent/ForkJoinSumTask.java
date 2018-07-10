package com.chengmboy.core.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author cheng_mboy
 */
public class ForkJoinSumTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 1000;
    private long start;
    private long end;

    public ForkJoinSumTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool fjp = new ForkJoinPool();
        ForkJoinSumTask task = new ForkJoinSumTask(1, 1000000000);
        ForkJoinTask<Long> f = fjp.submit(task);
        System.out.println(f.get());
    }

    @Override
    protected Long compute() {
        System.out.println(Thread.currentThread() + " " + start + " " + end);
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        long mid = (end + start) / 2;
        ForkJoinSumTask leftTask = new ForkJoinSumTask(start, mid);
        ForkJoinSumTask rightTask = new ForkJoinSumTask(mid + 1, end);
        leftTask.fork();
        rightTask.fork();
        Long r1 = leftTask.join();
        Long r2 = rightTask.join();
        return r1 + r2;
    }
}
