package com.cheng.beans;

/**
 * @author cheng_mboy
 */
public abstract class GetBeanTest {

    public void showMe() {
        this.getBean().showMe();
    }
    public abstract User getBean();
}
