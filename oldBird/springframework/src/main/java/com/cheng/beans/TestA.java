package com.cheng.beans;

/**
 * @author cheng_mboy
 */
public class TestA {

    private TestB testB;

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }

    public void a() {
        testB.b();
    }
}
