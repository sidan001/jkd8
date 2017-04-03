package com.chou.function;

import com.chou.functional_interface.TestFunctionalInterface;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerTest {

    public static void main(String[] args) {
        Consumer<Integer> consumer = (Integer i) -> System.out.println(i);
        IntConsumer intConsumer = (int i) -> System.out.println(i);
        System.out.println(consumer);
        System.out.println(intConsumer);

        passCons(consumer);
        passCons(consumer::accept);
//        passCons(intConsumer);       //Error
        passCons(intConsumer::accept);


        passIntCons(intConsumer);
        passIntCons(intConsumer::accept);
//        passIntCons(consumer);      //Error
        passIntCons(consumer::accept);



        Consumer<Integer> consFromConsAcc = consumer::accept;
        IntConsumer intConsFromConsAcc = consumer::accept;

        Consumer<Integer> consFromIntconsAcc = intConsumer::accept;
        IntConsumer intConsFromIntconsAcc = intConsumer::accept;



        System.out.println(consFromConsAcc);
        System.out.println(intConsFromConsAcc);
        System.out.println(consFromIntconsAcc);
        System.out.println(intConsFromIntconsAcc);




    }

    private static void passCons(Consumer<Integer> consumer) {
        consumer.accept(100);
    }
    private static void passIntCons(IntConsumer intConsumer) {
        intConsumer.accept(100);
    }





}
