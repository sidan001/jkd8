package com.chou.function.func_programming;

/**
 * Created by chou on 2017/4/22.
 */
public class MyLinkedList<T> implements MyList<T> {
    private final T head;
    private final MyList<T> tail;
    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }
    @Override
    public T head() {
        return head;
    }
    @Override
    public MyList<T> tail() {
        return tail;
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}
