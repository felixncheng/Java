package com.chengmboy.design.singleton;


/**
 * @author cheng_mboy
 */
public enum SingletonEnum {
    /**
     * 单例
     */
    INSTANCE;
    private int counter;

    public void increment() {
        /*
         * 增加线程竞争机会
         * */
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter++;
    }

    public int getValue() {
        return counter;
    }
}
