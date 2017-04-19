package com.chou.stream;

import sun.nio.ch.FileChannelImpl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        final IntStream stream = Arrays.stream(new int[]{1, 31, 24, 5, 6, 6, 67, 7});


        stream.map( i -> {
            System.out.printf("map%d\t", i);
            return i;})
            .filter(i -> {
                System.out.printf("filter%d\t", i);
                return i <20;
            }).skip(2).limit(3).forEach(System.out::println);

        System.out.println(Runtime.getRuntime().availableProcessors());
        new Thread(() -> System.out.println() );


        final Demo demo = new Demo();
        demo.setfunInterface(new FuncA());
        demo.test();
        final Demo de = new Demo();
        de.setfunInterface(() -> System.out.println("lambada funcInterface test"));
        de.test();

    }

    FunInterface funcInterface;

    public void setfunInterface(FunInterface funcInterface){
            this.funcInterface = funcInterface;
    }
    public void test(){
        funcInterface.test();
    }


    public interface FunInterface{
        public  void test();

        default  void otherMethod(){};
    }

    public static class FuncA implements   FunInterface{

        @Override
        public void test() {
            System.out.println("FunA test method");
        }
    }


}
