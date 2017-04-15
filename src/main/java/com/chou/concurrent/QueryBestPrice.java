package com.chou.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2017/4/14.
 */
public class QueryBestPrice {

    static List<Shop>  shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("JdShop"),
            new Shop("taoBaoShop"),
            new Shop("BuyItAll"));

    //采用顺序查询所有商店的方式实现的findPrices方法
    public static List<String> findPrices(String productName) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(productName)))
                .collect(toList());
    }
    //采用并行流
    public static List<String> findPrices_parallel(String productName) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(productName)))
                .collect(toList());
    }
    //使用CompletableFuture 发起异步请求
    public static List<String> findPrices_useCompletableFuture(String productName) {
        List<CompletableFuture<String>> priceFutures =shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(), shop.getPrice(productName))))
                .collect(toList());


        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }



    public static void main(String[] args) {
        long start = System.nanoTime();

//        System.out.println(findPrices("myPhone27S"));  //Done in 4092 msecs
//        System.out.println(findPrices_parallel("myPhone27S")); //Done in 1098 msecs
        System.out.println(findPrices_useCompletableFuture("myPhone27S")); //Done in 2085 msecs

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");





    }






}
