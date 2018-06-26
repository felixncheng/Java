package com.cheng.thread;


/**
 * @author cheng_mboy
 * @create 2017-07-28-16:10
 */
public class Counter {
    public static int count = 0;

    public static void inc() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }

        count++;
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            new Thread(Counter::inc).start();
        }
        Thread.sleep(5000);
        System.out.println("运行结果:Counter.count=" + Counter.count);
    }
}
