package com.chengmboy.design.singleton;

import java.io.Serializable;

/**
 * @author cheng_mboy
 */
public class Singleton implements Cloneable, Serializable {

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
