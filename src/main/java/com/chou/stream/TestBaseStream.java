package com.chou.stream;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/4/1.
 */
public class TestBaseStream {
    public static void main(String[] args) {
        final List integers = Arrays.asList(21, 2, 4, 556, 4, 53, 523, 523, 632);
        final BaseStream<Integer, Stream<Integer>> stream = integers.stream();
        //spliterator()   terminal operation.
        final Spliterator<Integer> spliterator = integers.stream().spliterator();

        showCharacteristics(spliterator);

        System.out.println("未遍历前estimateSize: " + spliterator.estimateSize());
        final boolean b = spliterator.tryAdvance(i -> System.out.printf("tranAdvance先取第一个元素: %d  \testimateSize: %d\n", i, spliterator.estimateSize()));
        System.out.printf("tranAdvance后estimateSize:%d\n", spliterator.estimateSize());
        System.out.print("forEachRemaining()\n");
        /*see java.util.Spliterators.ArraySpliterator.forEachRemaining()
        方法中将index修改为fence的值，这样在进行forEachRemainig操作时，外围其他线程对同一个spliterator的tryAdvance或forEachRemaining的调用不能进行遍历，否则可能会出现不一致的结果*/
        spliterator.forEachRemaining(i -> System.out.printf("\t取出：%d\testimateSize: %d\n", i, spliterator.estimateSize()));
        System.out.printf("forEachRemaining后estimateSize:%d\n", spliterator.estimateSize());

        //forEachRemaing修改了index，导致无法进行分割返回null的Spliterator
        final Spliterator<Integer> integerSpliterator = spliterator.trySplit();

        final Spliterator spliterator_for_split = integers.stream().spliterator();
        System.out.printf("this spliterator_for_split size before trySplit: %d\n", spliterator_for_split.estimateSize());
        final Spliterator<Integer> subSpliterator = spliterator_for_split.trySplit();
        System.out.printf("this spliterator_for_split size  after trySplit : %d\t\tsubSpliterator size: %d\n", spliterator_for_split.estimateSize(), subSpliterator.estimateSize());



        //iterator() terminal operation.
        final Iterator<Integer> iterator = integers.stream().iterator();
        iterator.forEachRemaining(i -> System.out.print(i + " "));
    }

    private static void showCharacteristics(Spliterator<Integer> spliterator) {
        System.out.println("has SIZED: " + spliterator.hasCharacteristics(Spliterator.SIZED));
        System.out.println("has SUBSIZED: " + spliterator.hasCharacteristics(Spliterator.SUBSIZED));
        System.out.println("has IMMUTABLE: " + spliterator.hasCharacteristics(Spliterator.IMMUTABLE));
        System.out.println("has DISTINCT: " + spliterator.hasCharacteristics(Spliterator.DISTINCT));
        System.out.println("has NONNULL: " + spliterator.hasCharacteristics(Spliterator.NONNULL));
        System.out.println("has ORDERED: " + spliterator.hasCharacteristics(Spliterator.ORDERED));
        System.out.println("has SORTED: " + spliterator.hasCharacteristics(Spliterator.SORTED));
        System.out.println("has CONCURRENT: " + spliterator.hasCharacteristics(Spliterator.CONCURRENT));
    }


}
