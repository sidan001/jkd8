package com.chou.functional_interface;

/**
 * Created by Administrator on 2016/12/21.
 */
public class Hello {

    public Runnable r = new Runnable() {
        public void run() {
            System.out.println(this);
            System.out.println(toString());
        }
    };

    public Runnable r1 = () -> {
        System.out.println(this);
        System.out.println(toString());
    };


    public Runnable r2 = new Runnable() {
        public void run() {
            System.out.println(Hello.this);
            System.out.println(Hello.this.toString());
        }
    };

    public Runnable r3 = () -> {
        System.out.println(Hello.this);
        System.out.println(Hello.this.toString());
    };

    public String toString() {
        return "Hello's custom toString()";
    }

    public static void main(String... args) {
        Hello h = new Hello();
        h.r.run();

        h.r1.run();
        h.r2.run();
        h.r3.run();


        StringBuilder message = new StringBuilder();
        Runnable r = () -> System.out.println(message);
        message.append("Howdy, ");
        message.append("world!");
        r.run();






    }

}
