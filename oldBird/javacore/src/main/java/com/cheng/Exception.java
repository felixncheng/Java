package com.cheng;

/**
 * @author cheng_mboy
 */
public class Exception extends Throwable {

    public static void main(String[] args) {
        System.out.println(test());
    }

    private static int test() {

        try {
            return 2;
        }finally {
            return 3;
        }
    }
}
