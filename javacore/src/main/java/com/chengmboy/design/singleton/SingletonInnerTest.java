package com.chengmboy.design.singleton;

import java.util.concurrent.*;

import com.chengmboy.util.ThreadUtil;

/**
 * @author cheng_mboy
 */
public class SingletonInnerTest {

    public static void main(String[] args) throws InterruptedException {
        //inStartTest();
        nestedTest();
    }

    /**
     * 按需加载，跑了多个线程，获取多次实例，只实例化一次。
     * 输出一次实例化，三个true
     */
    private static void nestedTest() throws InterruptedException {
        int threadCount = 3;
        int blockQueueSize = 3;
        ThreadPoolExecutor executor = ThreadUtil.getThreadPoolExecutor(threadCount, blockQueueSize);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                SingletonInner instance = SingletonInner.getInstance();
                SingletonInner instance1 = SingletonInner.getInstance();
                System.out.println(instance == instance1);
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }

    /**
     * 调用其他静态方法时会加载实例化。
     * 去掉SingletonInner静态变量实例化前的注释//，再执行。
     * 输出实例化，staticMethod
     */
    private static void inStartTest() {
        SingletonInner.staticMethod();
    }
}
