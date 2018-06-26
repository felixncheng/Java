package com.cheng;


import java.time.LocalDate;
import java.util.*;

/**
 * @author cheng_mboy
 * @create 2017-07-05-10:07
 */

public class Collection {
    public static void main(String[] args) {



        System.out.println(Thread.currentThread().isInterrupted());

        System.out.println(Thread.currentThread().getState());

    }

    private static void view() {
        List<String> settings = Collections.nCopies(100, "DEFAULT");

        System.out.println(settings.size());


    }

    private static void map() {
        /*Map m = new HashMap();
        System.out.println(m.getOrDefault("", "空"));
        */
        /*Map<String ,Integer> counts = new HashMap();
        String word = "word";
        counts.merge(word,1, Integer::sum);*/

    }

    private static void set() {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));
        System.out.println("iterating over elements...");
        for (LocalDate localDate : pq)
            System.out.println(localDate);
        System.out.println("removing elements...");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

    private static void list() {
        List array = new ArrayList();

        List link = new LinkedList();

        long arrayCurrent = new Date().getTime();

        for (int i = 0; i < 10000; i++) {
            array.add(new Object());
        }

        System.out.println(new Date().getTime() - arrayCurrent);

        long linkCurrent = new Date().getTime();

        for (int i = 0; i < 10000; i++) {
            link.add(new Object());
        }

        System.out.println(new Date().getTime() - linkCurrent);
    }

}













