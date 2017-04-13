package com.chou.default_method;

/**
 * Created by Administrator on 2017/4/12.
 */
public class ClassC  implements InterfaceB,InterfaceA{
    public static void main(String... args) {
        new ClassC().hello();//Hello From Interface B
    }
}
