package com.chou.function.func_programming;

import java.util.function.Function;

/**
 * 高阶函数接受两个或多个函数， 并返回另一个函数，实现的效果在某种程度上类似于将这些函数进行了结合
 */
public class FuncCombiner {
    static <A,B,C> Function<A,C> compose(Function<A,B> f, Function<B,C> g){
        return a -> g.apply(f.apply(a));
    }

    static <A> Function<A, A> repeat(int n, Function<A, A> function) {
        //如果n为0,直接返回'什么都不做'的标识符
        /*return n == 0 ? x -> x :
                compose(function,repeat(n-1,function));*/

       /* return n == 0 ? Function.identity() :
                compose(function,repeat(n-1,function));*/

        return n == 1 ? function :
                compose(function,repeat(n-1,function));
    }


    public static void main(String[] args) {

        Function<Integer, Integer> f = (Integer i) -> 2 * i;
        System.out.println(repeat(3, f).apply(10));//80


    }


}
