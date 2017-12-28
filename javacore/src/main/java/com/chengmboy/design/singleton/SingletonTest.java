package com.chengmboy.design.singleton;

import java.util.concurrent.*;

/**
 * @author cheng_mboy
 */
public class SingletonTest {

    private static int threadCount = 3;
    private static CountDownLatch LATCH = new CountDownLatch(threadCount);
    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(threadCount, threadCount,
            0L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(threadCount),
            THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        testEnumFunction();
        EXECUTOR.shutdown();
    }

    /**
     * 测试枚举类成员方法是否线程安全
     * 结论 只有枚举类的构造函数是线程安全的，其他成员方法非线程安全
     */
    private static void testEnumFunction() throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            LATCH = new CountDownLatch(threadCount);
            long start = System.currentTimeMillis();
            for (int i = 0; i < threadCount; i++) {
                EXECUTOR.execute(SingletonTest::run);
            }
            LATCH.await();
            System.out.println("主线程执行完毕! 总耗时" + (System.currentTimeMillis() - start) + ": " + (threadCount == Singleton.INSTANCE.getValue()));
        }
    }


    private static void run() {
        Singleton.INSTANCE.increment();
        LATCH.countDown();
    }

}

