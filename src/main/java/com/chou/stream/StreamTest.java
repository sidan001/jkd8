package com.chou.stream;


import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.UUID;
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







    }

}
