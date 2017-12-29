package com.chengmboy.jvm;

/**
 * @author cheng_mboy
 */
public class Jmm {

    public static void main(String[] args) {
        // stacks(0);
        // stringTest();
        //objectTest();
        outOfMemory();
    }


    /**
     * 测试内存溢出
     */
    private static void outOfMemory() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String a = "hello";
        while (true) {
            a += a;
        }
    }

    /**
     * 测试栈溢出
     */
    private static void stacks(int deep) {
        try {
            Jmm.stacks(++deep);
        } catch (Throwable e) {
            System.out.println("当前栈深度:" + deep);
            e.printStackTrace();
        }
    }

    /**
     * 测试改变String对象
     * 结论 不能改变String对象,方法传递String是值传递
     * 期待输出 a
     */
    private static void stringTest() {
        String s1 = "a";
        stringTestInner(s1);
        System.out.println(s1);
    }

    private static void stringTestInner(String s) {
        s = "b";
    }

    /**
     * 测试方法传递是否是引用传递
     * 结论 上层方法传递对象引用给参数，也就是方法局部变量。
     * 局部变量通过引用，可以修改原对象也就是物理内存。如果修改引用，
     * 只是修改当前的局部变量引用。并不会影响原物理内存。
     * 期待输出 b 对象地址
     */
    private static void objectTest() {
        Inner o = new Jmm.Inner();
        o.setVar("a");
        objectTestInner(o);
        System.out.println(o.getVar());
        objectTestInnerAlterAddress(o);
        System.out.println(o);
    }

    private static void objectTestInner(Inner b) {
        b.setVar("b");
    }

    private static void objectTestInnerAlterAddress(Inner c) {
        c = null;
    }

    static class Inner {

        private String var;

        public String getVar() {
            return var;
        }

        public void setVar(String var) {
            this.var = var;
        }
    }

    /**
     * 测试基本类型传递
     */
    private static void primitiveTest() {

    }
}
