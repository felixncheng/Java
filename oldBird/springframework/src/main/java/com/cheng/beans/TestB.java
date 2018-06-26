package com.cheng.beans;

/**
 * @author cheng_mboy
 */
public class TestB {

    private TestC testC;

    public TestC getTestC() {
        return testC;
    }

    public void setTestC(TestC testC) {
        this.testC = testC;
    }

    public void b() {
        testC.c();
    }
}
