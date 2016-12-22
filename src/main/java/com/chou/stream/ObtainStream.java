package com.chou.stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/12/22.
 */
public class ObtainStream {

    public static void main(String[] args) {
        Collection<Integer> collection = Arrays.asList(1, 2, 4, 3, 5, 6);
        Stream<Integer> stream = collection.stream();
        Stream<Integer> parallelStream = collection.parallelStream();

        Stream<Integer> stream1 = Arrays.stream(new Integer[]{12,312,442,4,55,53});

        IntStream intStream = Arrays.stream(new int[]{1, 2, 3, 4});

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 5, 4, 6);


        String collect = integerStream.map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(collect);

//        integerStream.forEach(i -> System.out.printf(" %d ",i));
//        System.out.println(integerStream.findAny());         //integerStream已经被消费过，需要重新获取stream

        IntStream range = IntStream.range(0, 10);

        Stream<Integer> infiniteSequentialOrderedStream = Stream.iterate(1, (seed) -> ++seed);//1到无穷大的Stream
        Stream<Integer> limit = infiniteSequentialOrderedStream.limit(10);
        limit.forEach(i -> System.out.printf("%d ",i));

        System.out.println();
        new Random().ints(5).limit(10).forEach(i -> System.out.printf("%d ",i));


    }


}
