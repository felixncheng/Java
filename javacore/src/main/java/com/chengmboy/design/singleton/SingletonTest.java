package com.chengmboy.design.singleton;

import java.io.*;

/**
 * @author cheng_mboy
 */
public class SingletonTest {


    public static void main(String[] args) throws
            CloneNotSupportedException,
            IOException, ClassNotFoundException {
        //   cloneTest();
        serializeTest();
    }

    /**
     * 测试单例模式能否被克隆破坏
     * 结论 克隆破坏单例模式
     * 期待输出 false
     */
    private static void cloneTest() throws CloneNotSupportedException {
        Singleton instance = Singleton.getInstance();
        Singleton clone = (Singleton) instance.clone();
        System.out.println(instance == clone);
    }

    /**
     * 测试饿加载单例模式能否被序列化破坏
     * 结论 序列化破坏单例模式
     * 期待输出 false
     */
    private static void serializeTest() throws IOException, ClassNotFoundException {
        Singleton instance = Singleton.getInstance();
        String name = "Singleton.dat";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(name))) {
            outputStream.writeObject(instance);
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(name))) {
                Singleton deSerialize = (Singleton) inputStream.readObject();
                System.out.println(instance == deSerialize);
            }
        }
        File file = new File(name);
        //noinspection ResultOfMethodCallIgnored
        file.delete();
    }
}
