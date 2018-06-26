package com.cheng.serializable;

import java.io.Serializable;

/**
 * @author cheng_mboy
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 7708869507308723148L;

    private Integer age;

    private String name;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    protected void a() {
        System.out.println("sup");
    }
}

