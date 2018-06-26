package com.cheng;

import java.util.*;

/**
 * @author cheng_mboy
 * @create 2017-07-07-15:31
 */
public class Hash {
    public static void main(String[] args) {
        Set set = new HashSet();

        Iterator sit = set.iterator();

        LinkedList list = new LinkedList<>();

        LinkedList list1 = new LinkedList();
        list.add(list);

        list.add("1");
        list.add("3");

        ListIterator iterator = list.listIterator();
        iterator.add("0");
        iterator.next();
        iterator.add("2");


        System.out.println(list.toString());
    }
}
