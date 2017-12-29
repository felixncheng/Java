package com.chengmboy.design.singleton;

/**
 * @author cheng_mboy
 */
public class SingletonInner implements Cloneable {

    /**
     * 如果写成静态变量，则在使用类的其他静态方法会直接加载所有静态变量，降低内存使用效率。
     */
    //private final static SingletonInner INSTANCE = new SingletonInner();
    private SingletonInner() {
        System.out.println("实例化");
    }

    public static void staticMethod() {
        System.out.println("staticMethod");
    }

    public static SingletonInner getInstance() {
        return Nested.INSTANCE;
    }

    private static class Nested {

        final static SingletonInner INSTANCE = new SingletonInner();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
