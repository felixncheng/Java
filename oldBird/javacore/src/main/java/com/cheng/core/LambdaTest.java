package com.cheng.core;



import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017/6/1.
 */
public class LambdaTest {
    public static void main(String[] args) {
       /* String[] planets =new String[] {"mercury","venus","Earth","Mars","Jupiter","Saturn","Uranus","Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length: ");
        Arrays.sort(planets,(first,second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));
        Timer t = new Timer(1000, event -> System.out.println("The time is " + new Date()));
        t.start();
        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);*/
       List<String> a=new ArrayList();
       a.add("A");
        List b = new ArrayList();
        b.addAll(a);
        List c = new ArrayList();
        c.add(b);
        a.clear();
        a.add("b");
        List d = new ArrayList();
        d.add(a);
        c.add(d);
        System.out.println(c.toString());

    }

}

