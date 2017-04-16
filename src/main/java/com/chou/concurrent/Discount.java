package com.chou.concurrent;

/**
 * Created by chou on 2017/4/15.
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }
    //将折扣代码应用于商品最初的原始价格
    public static String applyDiscount(Quote quote) {
        return String.format("%s price is %f", quote.getShopName(),
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode()));
    }

    private static double apply(double price, Code code) {
        //模拟Discount服务响应的延迟
        delay();

        return price * (100 - code.percentage) / 100;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}