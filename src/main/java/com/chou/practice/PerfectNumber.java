package com.chou.practice;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Perfect Number: a perfect number is a positive integer that is equal to the
 * sum of its proper positive divisors, that is, the sum of its positive
 * divisors excluding the number itself.
 */
public class PerfectNumber {
    public static void main(String[] args) {
        IntStream iterate = IntStream.iterate(1, i -> ++i);
        IntStream limit = iterate.limit(10000);

        String collect = limit.filter(PerfectNumber::isPerfectNumber)
                .mapToObj(PerfectNumber::getDivisors)
                .collect(Collectors.joining());

        System.out.println(collect);
    }

    public static boolean isPerfectNumber(int n){
        int i = 1, sum = 0;
        while (i < n) {
            if (n % i == 0) {
                sum = sum + i;
            }
            i++;
        }
        return (sum == n)?true:false;
    }

    public static String getDivisors(int n){
        StringBuilder sb = new StringBuilder();
        sb.append(n+" divisors: ");
        int i = 1, sum = 0;
        while (i < n) {
            if (n % i == 0) {
                sum = sum + i;
                sb.append(i).append(", ");
            }
            i++;
        }
        return sb.delete(sb.length() -2,sb.length()).append("\n").toString();
    }
}