package com.chou.practice.number_based_logical_programs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * split number into digits.
 */
public class SplitNumber {

    public static void main(String[] args) {
        System.out.println(digitsToList(16234234).stream().map(String::valueOf).collect(Collectors.joining(" ")));

        System.out.println(digitsToStream(13564687).map(String::valueOf).collect(Collectors.joining(" ")));


    }


    static List<Integer> digitsToList(int i) {
        List<Integer> digits = new ArrayList<Integer>();
        while(i > 0) {
            digits.add(i % 10);
            i /= 10;
        }
        Collections.reverse(digits);
        return digits;
    }

    static Stream<Integer> digitsToStream(int i) {
        Queue<Integer> lifoQueue = Collections.asLifoQueue(new ArrayDeque<Integer>());
        while(i > 0) {
            lifoQueue.add(i % 10);
            i /= 10;
        }
        return lifoQueue.stream();
    }
}
