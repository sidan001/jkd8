package com.chou.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/4/14.
 */
public class Shop {
    public static final Random RANDOM = new Random();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop(String shopName) {

        this.name = shopName;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    //将同步方法转换为异步方法
    public CompletableFuture<Double> getPriceAsync(String product) {
        //创建CompletableFuture对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //在另一个线程中以异步方式执行计算
        new Thread( () -> {
            try {
                double price = calculatePrice(product);

                //需长时间计算的任务结束并得出结果时，设置Future的返回值
                //如果价格计算正常结束，完成Future操作并设置商品价格
                futurePrice.complete(price);
            } catch (Exception ex) {
                //否则就抛出导致失败的异常，完成这次Future操作
                futurePrice.completeExceptionally(ex);
            }
        }).start();

        //无需等待还没结束的计算，直接返回Future对象
        return futurePrice;
    }

    //使用工厂方法supplyAsync创建CompletableFuture对象
    public CompletableFuture<Double> getPriceAsync_useSupplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    public String getPrice_useDiscount(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                RANDOM.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
//        delay();
        randomDelay();
        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void randomDelay() {
        int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);//[0.5s---2.5s]
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void doSomethingElse() {
        System.out.println("do something else");
    }



    //使用异步API
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        //查询商店，试图取得商品的价格
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.printf("Invocation returned after %d msecs%n", invocationTime);


        // 执行更多任务，比如查询其他商店
        doSomethingElse();
        // 在计算商品价格的同时

        try {
            //从Future对象中读取价格，如果价格未知，会发生阻塞
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

}
