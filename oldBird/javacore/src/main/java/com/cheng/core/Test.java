package com.cheng.core;

import java.util.function.IntFunction;
import java.util.function.Supplier;

public class Test {
    private int i;

    public void get() {
        System.out.println("sup");
    }

    public static void main(String[] args) {
    /*	String A="(0asdfasdf,1asfasfasf)_(2,3)";
        System.out.println(A);
		String[] b=A.split("_");
		for(String c:b){
			System.out.println(c);
			System.out.println("classId="+c.substring(1, c.indexOf(","))
			+"******"+"subjectId="+c.substring(c.indexOf(","),c.length()-1));
		}*/


		/*int n=123_456_789;
        float f=n;
		int s=(int) f;
		System.out.println(f);
		System.out.println(s);*/

		/*int sum=0;
		read_data:
		for(int i=0;i<10;i++){
			sum++;
			for (int j=10;j>0;j--){
				if(8==j)
					continue read_data;
				sum++;
			}
		}
		System.out.println(sum);*/

		/*int[] A=new int[9];
		for(int i=0;i<9;i++){
			A[i]=i;
		}
		System.out.println(Arrays.toString(A));
		System.out.println(Arrays.binarySearch(A, 111111));
		int b=0;
		Arrays.fill(A,b);
		System.out.println(Arrays.toString(A));
		System.out.println();*/

	/*	String sentence="⊙ is";
		char ch=sentence.charAt(0);
		System.out.println();
		new A().get();*/

	/*	*//**
         * 泛型会有擦除机制，所以不能实例化泛型数组
         * 可用ArryaList处理泛型数组。
         *//*
		String[] strings = {
				"A", "b", "c"
		};

		Object[] objs = strings;
		objs[0] = new Integer(1);
*/
        /**
         * 可变参数实际为数组，即可传多参数，也可以传数组
         * */
        a(new int[]{1, 2, 3});
        a(1, 2, 3);
       // makePair(()-> c());
        minmax(i->{return i+3;} ,"2");


    }
    public static <T> Pair<T> makePair(d<T> constr) {
        return new Pair<>(constr.get() , constr.get());
    }

    private static void a(int... args) {
        for (int arg : args) {
            System.out.println(arg);
        }
    }

    private static int c(int i) {
        return i;
    }

    public static <T extends Comparable> T minmax(IntFunction<T> constr , T... a) {
        T mm = constr.apply(2);
        return  mm;
    }
}

class Pair<T>{
    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
}

class b implements Supplier<Integer> {

    @Override
    public Integer get() {
        return 3;
    }
}

interface d<T>{
    T get();
}

class a extends Test {
    public void get(String fmt, Object... args) {
        System.out.println("sub");
    }
}

