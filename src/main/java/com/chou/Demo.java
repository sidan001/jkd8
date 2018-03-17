package com.chou;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {
    private final int anInt;
    ;
    //1xx Informational
    public int code;
    public int pi;
    private int pri;

    public Demo(int i) {
        anInt = i;
    }

    public static void main(String[] args) {
        /*Demo demo = new Demo(10);
        demo.helloWorld("");

        Consumer<Integer> cons = i -> System.out.println(i);
        IntConsumer intConsumer = i -> System.out.println(i);

        Integer one = 1;

        cons.accept(1);
        cons.accept(one);

        intConsumer.accept(1);
        intConsumer.accept(one);*/

        //勾股定理(毕达哥拉斯定理)
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.limit(5)
                .forEach(t ->
                        System.out.printf("%d, %d, %d%n", t[0], t[1], t[2]));


        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0));

        System.out.println(subsets(Arrays.asList(1,4,9)));

        printPrimeNumber(10);
        printFibonacci(20);
    }
    public void setPi(int pi) {
        this.pi = pi;
        for (int i = 0; i < pi; i++) {
            System.out.println("hello." + pi);
        }
    }

    public String helloWorld(String name) {
        return "hello" + name;
    }


    //算出一个集合的子集
    static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1,list.size());
        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);
    }
    static List<List<Integer>> insertAll(Integer first,
                                         List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }
    static List<List<Integer>> concat(List<List<Integer>> a,
                                      List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

    static void printPrimeNumber(int number){
        IntStream.range(2,number)
                .peek(t ->{
                    IntStream.range(2,t)
                            .filter(x -> t % x == 0)
                            .findAny()
                            .ifPresent(i -> System.out.printf("%d is equals %d * %d%n", t, i, t / i));
                })
                .filter(t -> IntStream.range(2,t).noneMatch(i -> t % i == 0))
                .forEach(t -> System.out.println(t + " is a prime number"));
    }

    static void printFibonacci(int n) {
        List<Integer> collect = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .map(t -> t[0])
                .peek(t -> System.out.println(t + " "))
                .limit(n)
                .collect(Collectors.toList());
        System.out.println(collect);


    }
}
