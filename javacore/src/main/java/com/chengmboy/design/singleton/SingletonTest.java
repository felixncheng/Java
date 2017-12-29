package com.chengmboy.design.singleton;

import java.util.concurrent.*;

import com.chengmboy.util.ThreadUtil;

/**
 * @author cheng_mboy
 */
public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {
        /*
         * 执行测试10次 true 代表线程安全，false代表线程不安全
         * 存在输出结果false。
         */
        for (int i = 0; i < 10; i++) {
            enumFunctionTest();
        }
        //enumTest();
    }

    /**
     * 测试枚举类成员方法是否线程安全
     * 结论 只有枚举类的构造函数是线程安全的，其他成员方法非线程安全
     */
    private static void enumFunctionTest() throws InterruptedException {
        int threadCount = 3;
        int blockQueueSize = 3;
        ThreadPoolExecutor executor = ThreadUtil.getThreadPoolExecutor(threadCount, blockQueueSize);
        final CountDownLatch latch = new CountDownLatch(threadCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                Singleton.INSTANCE.increment();
                latch.countDown();
            });
        }
        latch.await();
        System.out.println(threadCount == Singleton.INSTANCE.getValue());
        executor.shutdown();
    }

    /**
     * 测试枚举项单例是否线程安全。
     */
    private static void enumTest() throws InterruptedException {
        int threadCount = 3;
        int blockQueueSize = 3;
        ThreadPoolExecutor executor = ThreadUtil.getThreadPoolExecutor(threadCount, blockQueueSize);
        final CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                Singleton instance = Singleton.INSTANCE;
                Singleton instance1 = Singleton.INSTANCE;
                System.out.println(instance == instance1);
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }
}

