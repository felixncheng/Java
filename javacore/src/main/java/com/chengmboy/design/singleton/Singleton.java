package com.chengmboy.design.singleton;

import java.util.UUID;

/**
 * @author cheng_mboy
 */
public enum Singleton {
    /**
     * 单例
     */
    INSTANCE;
    private String id = UUID.randomUUID().toString();
    private int counter;

    public String getId() {
        return this.id;
    }

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
