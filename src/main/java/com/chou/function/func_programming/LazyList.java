package com.chou.function.func_programming;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 */
class LazyList<T> implements MyList<T> {
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        //这里tail使用了一个Supplier方法提供了延迟性
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /* TODO 问题在 于每次实时访问LazyList的元素时，tail中的Supplier都会被重复调用;
      你可以设定tail中 的Supplier方法仅在第一次实时访问时才执行调用，从而修复这一问题——计算的结果会缓存 起来——效果上对列表进行了增强。
       实现这一目标，你可以在LazyList的定义中添加一个私 有的Optional<LazyList<T>>类型字段alreadyComputed，tail方法会依据情况查询及更新 该字段的值。*/
    // lambada 表达式执行具有延迟行
    public static LazyList<Integer> from(int n) {
        System.out.println(n);
        return new LazyList<Integer>(n, () -> from(n + 1));//tail 就是() -> from(head + 1)
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this :
                p.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(p)) :
                        tail().filter(p);
    }

    public static void main(String[] args) {
        MyList<Integer> l =
                new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));


        LazyList<Integer> numbers = from(2);
        /*int two = numbers.head();
        int three = numbers.tail().head();//调用tail()方法才会出发from方法的调用，
        int four = numbers.tail().tail().head();
        */

        int two = primes(numbers).head();
        int three = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();

        System.out.println(two + " " + three + " " + five);

//        printAll(primes(from(2)));

    }


    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail()
                                .filter(n -> n % numbers.head() != 0)
                )
        );
    }

   /* static <T> void printAll(MyList<T> list){
        while (!list.isEmpty()){
            System.out.println(list.head());
            list = list.tail();
        }
    }*/

    //这个程序不会永远地执行下去；他最终会由于栈溢出而失效，因为java不支持尾部调用消除（tail call elimination）
    static <T> void printAll(MyList<T> list) {
        if (list.isEmpty())
            return;
        System.out.println(list.head());
        printAll(list.tail());
    }
}
