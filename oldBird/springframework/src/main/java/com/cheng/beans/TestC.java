package com.cheng.beans;

/**
 * @author cheng_mboy
 */
public class TestC {

    private TestA testA;

    public TestA getTestA() {
        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }

    public void c() {
        testA.a();
    }
}
