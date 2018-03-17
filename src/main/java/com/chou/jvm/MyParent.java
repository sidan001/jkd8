package com.chou.jvm;

/**
 * Created by chou on 2018/2/4.
 */
public class MyParent {
    public static void main(String[] args) {
        System.out.println(MyTest.STR);//ldc int,float,String类型的常量值从产量池推送到栈顶
        System.out.println(MyTest.a);//iconst_1
        System.out.println(MyTest.b);//iconst_5
        System.out.println(MyTest.c);//bipush [-128 -- 127]
        System.out.println(MyTest.d);//ldc
        System.out.println(MyTest.s);//sipush [-32768 -- 32767]

        System.out.println(MyTest.STR_1);//getstatic

    }
}

class MyTest{
    public static final String STR = "hello world";
    public static final int a = 1;
    public static final int b = 5;
    public static final int c = 6;
    public static final int d = 100000;
    public static final short s = 32767;

    public static String STR_1 = "hello_world_1";

    static {
        System.out.println("This is MyTest static block");
    }

}