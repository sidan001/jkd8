package com.chou.jvm;

/**
 * Created by chou on 2018/3/3.
 */
public class ClassInitTest {
    public static void main(String[] args) {
       /*//没有对Child主动使用，对Prent触发了主动使用，导致Parent初始化
        System.out.println(Child.str);*/


        /*//对Child主动使用，导致Child需要初始化，子类初始化要求父类进行初始化
        System.out.println(Child.str1);*/


        System.out.println(Child.CONSTANT_STR);

        System.out.println(Child.str);
    }
}

class Parent{
    public static String str = "Parent str";
    static {
        System.out.println("Parent init");
    }
}

class Child extends Parent{
    public static String str1 = "Child str";
    public static final String CONSTANT_STR = "Child CONSTANT_STR";
    static {
        System.out.println("Child init");
    }
}