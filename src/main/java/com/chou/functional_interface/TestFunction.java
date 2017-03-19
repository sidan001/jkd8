package com.chou.functional_interface;


import java.util.function.Function;

public class TestFunction {

    public static void main(String[] args) {
        Function<Integer,Integer> function =  i -> i*2;



//        Function<Function<Integer,Integer>,Function<Integer,Integer>> f =  fun -> fun.andThen(fun);
//
//        Function<Integer, Integer> apply = f.apply(function);
//
//        System.out.printf(apply.apply(1).toString());


        Integer integer = function.andThen(function).apply(1);
        System.out.println(integer);

        int i = Integer.bitCount(32);
        System.out.println(i);
    }



}
