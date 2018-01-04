package com.chengmboy.core.inner;

/**
 * @author cheng_mboy
 *         接口解决了多重继承部分问题，内部类使多重继承更加完整
 */
public class OuterClass {

    private int var;

    /**
     * 成员内部类
     */
    class Inner {

        private int var;
    }

    /**
     * 获取成员内部类实例
     */
    Inner getInnerInstance() {
        return new Inner();
    }

    /**
     * 静态内部类
     * 可用于实现按需单例模式
     */
    static class InnerStatic {

    }

    static void eat(AbstractClass c) {
        c.eat();
    }

    public static void main(String[] args) {

        /* 实例化成员内部类*/
        OuterClass outerObject = new OuterClass();
        Inner inner = outerObject.new Inner();
        System.out.println("使用outObject.new实例化成员内部类");

        /*实例化静态内部类*/
        InnerStatic innerStatic = new OuterClass.InnerStatic();
        System.out.println("使用OuterClass.InnerClas实例化静态内部类");
        /**
         *方法内部类
         * */
        class Method {

            private int num;

            private Method(int num) {
                this.num = num;
            }

            private void get() {
                System.out.println("方法在调用方法内部类的成员方法。");
            }

        }
        Method m = new Method(3);
        m.get();
        System.out.println("方法可以访问方法内部类成员变量" + m.num);
        /**
         * 匿名内部类
         * */
        eat(new AbstractClass() {
            @Override
            public void eat() {
                System.out.println("实现匿名内部类方法eat");
            }
        });
    }
}
