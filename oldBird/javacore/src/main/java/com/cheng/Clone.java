package com.cheng;

import java.util.LinkedList;

/**
 * @author cheng_mboy
 */
public class Clone {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        Object clone = list.clone();
    }
}
