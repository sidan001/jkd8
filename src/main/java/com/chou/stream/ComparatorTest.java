package com.chou.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by liuchou on 2017/3/18.
 */
public class ComparatorTest {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("hello", "world", "nice", "work", "system", "comparator");

        Collections.sort(stringList, (s1, s2) -> s1.length() - s2.length());
        System.out.println(stringList);

        Collections.sort(stringList, Comparator.comparingInt(String::length).reversed());
        System.out.println(stringList);
        //======================================================================
        Collections.sort(stringList, Comparator.comparingInt(s -> s.length()));
        //TODO  加上reversed()方法后，无法推断出 s的类型，变成了Object类型
        //阅读 https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html  #Target Types
        //https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-typing
        //Collections.sort(stringList, Comparator.comparingInt(s -> s.length()).reversed());
        Collections.sort(stringList, Comparator.<String>comparingInt(s -> s.length()).reversed());
        Collections.sort(stringList, Comparator.comparingInt(s -> ((String) s).length()).reversed());

        Collections.sort(stringList,  Comparator.reverseOrder());

        Collections.sort(stringList, Comparator.comparingInt((String s) -> s.length()).reversed());

        Comparator<String> c = Comparator.comparingInt(s -> s.length());
        Comparator<String> reversed = c.reversed();
        Collections.sort(stringList, reversed);
        System.out.println(stringList);
        //======================================================================


    }
}
