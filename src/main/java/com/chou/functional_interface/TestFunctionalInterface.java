package com.chou.functional_interface;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2016/12/19.
 */
@FunctionalInterface
public interface TestFunctionalInterface<T> {

    void test(T t);

    boolean equals(Object obj);

    String toString();

    default void defaultMethod1(){
        System.out.println("hello world");
    }

    default String concumer(T t ){

        return t.toString();
    }

    default String supplier(){
        return "hello world";
    }

}
