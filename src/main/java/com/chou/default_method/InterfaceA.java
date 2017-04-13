package com.chou.default_method;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface InterfaceA {
    default void hello(){
        System.out.println("Hello From Interface A");
    };
}
