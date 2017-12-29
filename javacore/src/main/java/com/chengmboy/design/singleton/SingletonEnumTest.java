package com.chengmboy.design.singleton;

import java.io.*;
import java.util.concurrent.*;

import com.chengmboy.util.ThreadUtil;

/**
 * @author cheng_mboy
 */
public class SingletonEnumTest {

    public static void main(String[] args) throws InterruptedException,
            IOException, ClassNotFoundException {
        //enumFunctionTest();
        enumTest();
        // serializeTest();
    }

    /**
     * 测试枚举类成员方法是否线程安全
     * 结论 只有枚举类的构造函数是线程安全的，其他成员方法非线程安全
     * 期待输出 存在false
     */
    private static void enumFunctionTest() throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            int threadCount = 3;
            int blockQueueSize = 3;
            ThreadPoolExecutor executor = ThreadUtil.getThreadPoolExecutor(threadCount, blockQueueSize);
            final CountDownLatch latch = new CountDownLatch(threadCount);
            for (int i = 0; i < threadCount; i++) {
                executor.execute(() -> {
                    SingletonEnum.INSTANCE.increment();
                    latch.countDown();
                });
            }
            latch.await();
            System.out.println(threadCount == SingletonEnum.INSTANCE.getValue());
            executor.shutdown();
        }
    }

    /**
     * 测试枚举项单例是否线程安全。
     * 结论 枚举项单例是线程安全
     * 期待输出 3个true
     */
    private static void enumTest() throws InterruptedException {
        int threadCount = 3;
        int blockQueueSize = 3;
        ThreadPoolExecutor executor = ThreadUtil.getThreadPoolExecutor(threadCount, blockQueueSize);
        final CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                SingletonEnum instance = SingletonEnum.INSTANCE;
                SingletonEnum instance1 = SingletonEnum.INSTANCE;
                //noinspection ConstantConditions
                System.out.println(instance == instance1);
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }

    /**
     * 测试枚举项单例模式能否被序列化破坏
     * 结论 序列化不能破坏枚举项单例模式
     * 期待输出 true
     */
    private static void serializeTest() throws IOException, ClassNotFoundException {
        SingletonEnum instance = SingletonEnum.INSTANCE;
        String name = "SingletonEnum.dat";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(name))) {
            outputStream.writeObject(instance);
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(name))) {
                SingletonEnum deSerialize = (SingletonEnum) inputStream.readObject();
                System.out.println(instance == deSerialize);
            }
        }
        File file = new File(name);
        //noinspection ResultOfMethodCallIgnored
        file.delete();
    }
}

