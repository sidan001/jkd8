package com.chou.function;

import java.util.function.Function;


public class FunctionTest {
    public static void main(String[] args) {
        Function<Integer,Integer> function = i -> i+2;
        System.out.println(function.apply(2));


        Function<String, Integer> compose = function.compose(Integer::valueOf);
        System.out.println(compose.apply("4"));

        Function<Integer, String> toBinaryString = Integer::toBinaryString;
        System.out.println(toBinaryString.apply(4));

        Function<Integer, Integer> integerIntegerFunction = toBinaryString.andThen(compose);
        System.out.println(integerIntegerFunction.apply(4));



    }
}
