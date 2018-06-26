package com.cheng;

/**
 * @author cheng_mboy
 * @create 2017-07-04-17:02
 */
public interface A {
    void a();

    default void b() {
        System.out.println("i am A b");
    }
}
