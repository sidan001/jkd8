package com.chou.concurrent;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/4/14.
 */
public class FutureDemo {
    public static void main(String[] args) {
        //创建Executor-Service，通过它你可以向线程池提交任务
        ExecutorService executor = Executors.newCachedThreadPool();

        //向Executor-Service提交一个Callable对象
        Future<Double> future = executor.submit(new Callable<Double>() {
            public Double call() {
                return doSomeLongComputation();
            }
        });

        //异步操作进行的同时，可以做其他的事情
        doSomethingElse();

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
        } catch (TimeoutException te) {
            // 在Future对象完成之前超过已过期
        }
    }

    private static void doSomethingElse() {
        System.out.println("do something else");
    }

    private static Double doSomeLongComputation() {
        return 0.0d;
    }
}
