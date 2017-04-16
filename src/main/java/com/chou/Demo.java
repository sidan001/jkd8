package com.chou;


import java.util.function.Consumer;
import java.util.function.IntConsumer;
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


}
