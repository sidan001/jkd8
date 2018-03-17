package com.chou.jvm;

/**
 * -XX:+TraceClassLoading 查看class加载信息
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        //Test1：先加载父类，在加载子类
        System.out.println(ChildClass.b);

        /*//Test2: ChildClass.B 是final的（编译期值能确定下来，放入到了ClassLoadTest的常量池中），不需要加载ChildClass，和ParentInterface
        System.out.println(ChildClass.B);*/

       /*//Test3：对于接口常量（编译期值能确定下来，放入到了ClassLoadTest的常量池中），只加载了引用接口常量的mian方法所在的类ClassLoadTest，没有加载ChildInterface和ParentInterface
        System.out.println(ChildInterface.c);*/
    }
}

interface ParentInterface{
    public static final int a = 6;
    public Thread thread = new Thread(){
        {
            System.out.println("init thread");
        }
    };
}

class ChildClass implements ParentInterface{
    public static int b = 5;
    public static final int B = 5;

    static {
        System.out.println("this is MyClass");
    }
}
interface ChildInterface extends ParentInterface{
    public static final int c = 5;
}
