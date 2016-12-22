package com.chou.functional_interface;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by Administrator on 2016/12/20.
 */
public class Test {
    public static void main(String[] args) {
        TestFunctionalInterface<String> t = System.out::println;

        t.test("hello world");

        t = str -> System.out.println(str);

        t.test("hello str ");

        //--------------------------------------------------------

        Consumer<String> consumer = System.out::println;

        consumer.accept("hello world");

        consumer = t::concumer;

        consumer.accept("hello str ");

        //--------------------------------------------------------

        Supplier<String> supplier = t::supplier;
        System.out.println(supplier.get());

    }















}

