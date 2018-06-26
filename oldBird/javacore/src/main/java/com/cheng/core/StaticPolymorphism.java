package com.cheng.core;

public class StaticPolymorphism {
	
	private String base="base";
	StaticPolymorphism(){
		test();
	}
	public void test(){
		System.out.println(base);
	}
	public static void main(String[] args) {
		/*StaticSuper sup = new StaticSub();
        System.out.println(sup.staticGet());
        System.out.println(sup.dynamicGet());*/
       // System.exit(0);
		
		//System.out.println(SubClass.value);
	
		/**
		 * ����ʹ�����ֶ���ʾ2
		 * ͨ�����������࣬������������ĳ�ʼ��
		 * */
		/*SuperClass[] arr=new SuperClass[10];
		System.out.println(arr.toString());*/
		
		/**
		 * ����ʹ�����ֶ���ʾ3
		 * �������ڱ���׶δ��������ĳ����أ�������û��ֱ�����õ����峣�����࣬���ᴥ������ĳ�ʼ��
		 * */
	//	System.out.println(ConstClass.value);
       
        //new RoundGlyph(5);
		new Subtest();
		
		
	}
}


class Subtest extends StaticPolymorphism {
	
	private String base="subbase";
	Subtest(){
		test();
	}
	public void test(){
		System.out.println(base);
		
	}
}


class Glyph {
     void draw() {
        System.out.println("Glyph.draw()");
    }
    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph(). radius = " + radius);
    }

    void draw() {
        System.out.println("RoundGlyph.draw(). radius = " + radius);
    }
}


class StaticSuper {
    public static String staticGet() {
        return "Base staticGet()";
    }

    public String dynamicGet() {
        return "Base dynamicGet()";
    }
}

class StaticSub extends StaticSuper {
    public static String staticGet() {
        return "Derived staticGet()";
    }

    public String dynamicGet() {
        return "Derived dynamicGet()";
    }
}

/**
 * ����ʹ�����ֶ���ʾ1
 * ͨ���������ø���ľ�̬�ֶΣ����ᴥ������ĳ�ʼ��
 * */
class SuperClass{
	static {System.out.println("--------SuperClass Init");}
	static int value=123;
}

class SubClass extends SuperClass {
	static {System.out.println("-------SubClass Init");}
}

/**
 * ����ʹ�����ֶ���ʾ3
 * �������ڱ���׶δ��������ĳ����أ�������û��ֱ�����õ����峣�����࣬���ᴥ������ĳ�ʼ��
 * */

class ConstClass{
	static{System.out.println("-------ConstClass Init");}
	public static final String value="Hello World";
}


