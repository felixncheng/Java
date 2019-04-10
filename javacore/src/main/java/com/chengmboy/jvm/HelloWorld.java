package com.chengmboy.jvm;

/**
 * @author cheng_mboy
 */
public class HelloWorld {

    public static void main(String[] args) {
        Thread thread = new Thread(()->
        {
            for (int i = 0; i < 5; i++) {
                System.out.println("hello");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        System.out.println(Thread.currentThread().isDaemon());
        thread.start();
        System.out.println("main thread end");
    }
}
