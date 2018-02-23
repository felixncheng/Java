package com.chengmboy.core.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cheng_mboy
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadResearch {

    private static ReentrantLock lockA = new ReentrantLock();
    private static ReentrantLock lockB = new ReentrantLock();

    public static void main(String[] args) {
        testDeadLock();
    }


    /**
     * 线程死锁例子
     */
    private static void testDeadLock() {
        Thread threadA = new Thread(ThreadResearch::A);
        Thread threadB = new Thread(ThreadResearch::B);
        threadA.start();
        threadB.start();
    }

    private static void A() {
        try {
            lockA.lock();
            Thread.sleep(1000);
            lockB.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockA.unlock();
            lockB.unlock();
        }
    }

    private static void B() {
        try {
            lockB.lock();
            Thread.sleep(1000);
            lockA.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockB.unlock();
            lockA.unlock();
        }
    }

    /**
     * 测试join与countDownLatch
     */
    private static void testJoinAndCountDownLatch() throws InterruptedException {
        int threadCount = 5;
        long sleepTimes = 1000;
        /*
         * joinTest是同步执行，所以总耗时大于{threadCount*sleepTime}
         * countDownLatch是异步执行，所以总耗时大于{sleepTime}
         * */
        joinTest(threadCount, sleepTimes);
        countDownLatchTest(threadCount, sleepTimes);
    }

    private static void countDownLatchTest(int threadCount, final long sleepTimes) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(threadCount);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(sleepTimes);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.println("countDownLatch耗时" + " " + (costTime > sleepTimes ? "测试通过" : "测试失败"));
    }


    private static void joinTest(int threadCount, final long sleepTimes) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(sleepTimes);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.println("join耗时" + costTime + " " + (costTime + costTime > threadCount * sleepTimes ? "测试通过" : "测试失败"));
    }
}
