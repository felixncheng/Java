package com.cheng;

import com.Super;

/**
 * @author cheng_mboy
 */
public class Sub extends Super {

    @Override
    protected void say() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Sub a = new Sub();
        a.dothat();
    }
}
