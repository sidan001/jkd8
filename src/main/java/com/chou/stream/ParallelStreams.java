package com.chou.stream;

import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * 求1到n的等差序列之和
 * 对比了并行流，串行流，普通迭代，以及使用算法公式(体现出算法的重要性)的时间消耗
 */
public class ParallelStreams {

    public static final long N = 10_999_999_9L;

    public static void main(String[] args) {
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::sequentialSum, N) + " msecs");

        System.out.println("Iterative sum done in:" +
                measureSumPerf(ParallelStreams::iterativeSum, N) + " msecs");

        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParallelStreams::parallelSum, N) + " msecs" );

        System.out.println("FormulaSum sum done in: " +
                measureSumPerf(ParallelStreams::FormulaSum, N) + " msecs" );
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
    public static long parallelSum(long n) {


        // iterate 方法不适合并行化，因为每次执行累加计算都依赖前一次的结果
       /* return LongStream.iterate(1L, i -> i + 1) .limit(n) .parallel() .reduce(0L, Long::sum);*/
       // return LongStream.rangeClosed(0, n).parallel().reduce(0L, Long::sum);

        return LongStream.rangeClosed(0, n).parallel().sum();//sum() 内部实现就是reduce(0L,Long::sum)
    }
    public static long sequentialSum(long n) {

        return LongStream.rangeClosed(0, n).sum();
        //使用LongStream 可以避免装箱拆箱的性能损耗
        /*return LongStream.iterate(1, i -> i + 1) .limit(n) .reduce(0, Long::sum);*/
        /*return Stream.iterate(1L, i -> i + 1) .limit(n) .reduce(0L, Long::sum);*/
    }
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 可以使用等差序列公式更快求出结果
     * <pre>
     *     n(a1 + an)  / 2
     * </pre>
     * @param n
     * @return
     */
    public static long FormulaSum(long n) {
        return n * ( 1 + n)  >>> 1 ;
    }
}
