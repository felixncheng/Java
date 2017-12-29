package com.chengmboy.design.singleton;

import java.util.concurrent.*;

import com.chengmboy.util.ThreadUtil;

/**
 * @author cheng_mboy
 */
public class SingletonInnerTest {

    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {
        inStartTest();
        //nestedTest();
        //cloneTest();
    }

    /**
     * 测试内部类单例模式是否线程安全
     * 结论 按需加载，跑了多个线程，获取多次实例，只实例化一次。
     * 期待输出 实例化 3个true
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
     * 测试在饿加载情况下，调用其他静态方法，也会加载实例。
     * （去掉SingletonInner单例静态变量实例化前的注释//，再执行。）
     * 结论 调用其他静态方法时会加载实例化。
     * 期待输出 实例化，staticMethod
     */
    private static void inStartTest() {
        SingletonInner.staticMethod();
    }

    /**
     * 测试内部类实现单例模式是否能避免克隆
     * 结论 不能避免克隆，克隆破坏单例
     * 输出 false
     */
    private static void cloneTest() throws CloneNotSupportedException {
        SingletonInner instance = SingletonInner.getInstance();
        SingletonInner clone = (SingletonInner) instance.clone();
        System.out.println(instance == clone);
    }
}
