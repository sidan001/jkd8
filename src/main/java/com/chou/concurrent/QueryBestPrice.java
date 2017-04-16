package com.chou.concurrent;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

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
            new Shop("LetsSaveBig1"),
            new Shop("MyFavoriteShop1"),
            new Shop("JdShop1"),
            new Shop("taoBaoShop1"),
            new Shop("BuyItAll"));

    static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),//线程上限100个
            new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    //使用守护线程--这种方式不会阻止程序的关停
                    t.setDaemon(true);
                    return t;
                }
            });

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
                        () -> String.format("%s price is %.2f",shop.getName(), shop.getPrice(productName))))
                .collect(toList());


        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
    public static List<String> findPrices_useExecutor(String productName) {

        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(productName)),
                        executor))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
    //两个同步任务顺序执行 ,换成并行流，商品数增加时不容易扩展程序的性能，因为Stream底层依赖的是线程数量固定的通用线程池
    public static List<String> findPrices_useDiscount(String productName) {
        return shops.stream()
                .map(shop -> shop.getPrice_useDiscount(productName))//取得每个shop对象中的商品的原始价格
                .map(Quote::parse)          //在Quote对象中对shop返回的字符串进行转换
                .map(Discount::applyDiscount)   //联系Discount服务，为每隔Quote申请折扣
                .collect(toList());
    }

    public static List<String> findPrices_useDiscount_withCompletableFuture(String productName) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(
                                () -> shop.getPrice_useDiscount(productName), executor))//异步方式获取每隔shop中指定产品的原始价格
                .map(future -> future.thenApply(Quote::parse))
                .map(future ->
                        future.thenCompose(
                                quote ->
                                        CompletableFuture.supplyAsync(//使用另外一个异步任务构造期望的Future，申请折扣
                                                () -> Discount.applyDiscount(quote), executor)))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join) //等待流中的说有Future执行完毕，并提取各自的返回值
                .collect(toList());
    }

    public static Stream<CompletableFuture<String>> findPricesStream(String productName) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice_useDiscount(productName), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future ->
                        future.thenCompose(
                            quote ->
                                    CompletableFuture.supplyAsync(
                                            () -> Discount.applyDiscount(quote), executor)));

    }

    public static void main(String[] args) {
//        testCompletableFuture();

        useFutureThenAccept();
    }

    private static void testCompletableFuture() {
        long start = System.nanoTime();

//        System.out.println(findPrices("myPhone27S"));
//        System.out.println(findPrices_parallel("myPhone27S"));
//        System.out.println(findPrices_useCompletableFuture("myPhone27S"));
//        System.out.println(findPrices_useExecutor("myPhone27S"));


//        System.out.println(findPrices_useDiscount("myPhone27S"));
//        System.out.println(findPrices_useDiscount_withCompletableFuture("myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    private static void useFutureThenAccept() {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone")
                .map(f ->
                        f.thenAccept(s ->
                                System.out.printf("%s (done in %d msecs)%n", s, (System.nanoTime() - start) / 1_000_000)))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();//依次打印每个查询完成的产品的价格
//        CompletableFuture.anyOf(futures).join(); //打印出第一个查询完成的产品的价格

        System.out.printf("All shops have now responded in %d msecs%n", (System.nanoTime() - start) / 1_000_000);
    }


}
