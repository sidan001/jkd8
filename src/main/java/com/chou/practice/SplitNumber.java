package com.chou.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * split number into digits.
 */
public class SplitNumber {

    public static void main(String[] args) {
        List<Integer> digits = digits(16234234);

        String collect = digits.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(collect);
    }


    static List<Integer> digits(int i) {
        List<Integer> digits = new ArrayList<Integer>();
        while(i > 0) {
            digits.add(i % 10);
            i /= 10;
        }
        Collections.reverse(digits);
        return digits;
    }
}
