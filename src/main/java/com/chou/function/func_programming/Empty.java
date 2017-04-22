package com.chou.function.func_programming;

/**
 * Created by chou on 2017/4/22.
 */
class Empty<T> implements MyList<T> {
    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }
    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }
}