package com.chou.stream;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {

        Stream<String> stream = Stream.of("a","b","c");



//        String[] arra = stream.toArray(len -> new String[len]);
        String[] array = stream.toArray(String[]::new);


        Stream.of("a","b","c").map(String::toUpperCase).forEach(System.out::print);

        System.out.println();

        Stream.of(1,3,4,5).map(i -> i*i).forEach(n -> System.out.print(n + " "));

        System.out.println();

        Stream<String> generate = Stream.generate(UUID.randomUUID()::toString);
        generate.findAny().ifPresent(System.out::println);


        Stream<Integer> integerStream = Stream.iterate(1, item -> item + 2).limit(6);

        IntSummaryStatistics intSummaryStatistics = integerStream.filter(i -> i > 2).mapToInt(i -> i * 2).skip(2).limit(2).summaryStatistics();

        System.out.println(intSummaryStatistics.toString());

        Stream.of("hello world","hello welcome","hello  xx  google  "," hello apple inc").
                flatMap(str -> Stream.of(str.trim().split(" +")))
                .flatMap(string -> Stream.of(string.split("(?!^)")))
                .distinct().sorted().forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(11, 3, 4, 5, 9, 7);
        List<Integer> result =
                numbers.stream()
                        .peek(x -> System.out.println("from stream: " + x))//输出来自数 据源的当前 元素值
                        .map(x -> x + 17)
                        .peek(x -> System.out.println("after map: " + x))//输出map操作的结果
                        .filter(x -> x % 2 == 0)
                        .peek(x -> System.out.println("after filter: " + x))//输出经过filter操作的结果
                        .limit(3)
                        .peek(x -> System.out.println("after limit: " + x))//输出经过limit操作的结果
                        .collect(Collectors.toList());



        final Stream<String> buildStream = Stream.<String>builder().add("world").add("hi").build();

        System.out.println(buildStream.collect(Collectors.toList()));

    }

}
