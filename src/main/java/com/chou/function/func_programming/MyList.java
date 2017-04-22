package com.chou.function.func_programming;

import java.util.function.Predicate;

/**
 * Created by chou on 2017/4/22.
 */
interface MyList<T> {
    T head();
    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    default MyList<T> filter(Predicate<T> p){ throw new UnsupportedOperationException();}
}
