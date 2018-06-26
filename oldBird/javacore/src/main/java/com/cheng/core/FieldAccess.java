package com.cheng.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FieldAccess {
    public static int a(int n){
        try {
            int r=n * n;
            return r;
        }finally {
            if (n==2) return 0;
        }

    }
	 public static void main(String[] args) {
	        /*Super sup = new Sub();
	        System.out.println("sup.filed = " + sup.field + 
	                ", sup.getField() = " + sup.getField());
	        Sub sub = new Sub();
	        System.out.println("sub.filed = " + sub.field + 
	                ", sub.getField() = " + sub.getField() + 
	                ", sub.getSuperField() = " + sub.getSuperField());
            */
		 
		/* List l=new ArrayList<>();
		 Date date=new Date();
		 for(int i=0;i<10;i++){
			 Map A=new HashMap<>();
			 A.put("A", i);
			 l.add(A);
		 }
		 System.out.println(l.toString());*/
		 
		/* Object[] array =new String[10];
		 array[0]=10;
		 System.out.println(array[0]);*/

         System.out.println(a(2));


		 
		 
	 }
}

//Direct field access is determined at compile time.
class Super {
 public int field = 0;
 public int getField() {
	 System.out.println("Super");
     return field;
 }
}

class Sub extends Super {
 public int field = 1;
 public int getField() {
	 System.out.println("Sub");
     return field;
 }
 public int getSuperField() {
     return super.field;
 }
}
