package com.chou.default_method;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface InterfaceC extends InterfaceA{
    default void hello(){
        System.out.println("Hello From  Interface C");
    };
}
