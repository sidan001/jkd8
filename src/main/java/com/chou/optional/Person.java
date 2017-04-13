package com.chou.optional;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/13.
 */
public class Person {
    public static final String UNKNOWN = "Unknown";
    private Optional<Car> car = Optional.empty();//人可能有车，也可能没有车，因此将这个字段声明为Optional,但Person如果要序列化可能会出现问题
    private int age;

    public Optional<Car> getCar() { return car; }

    public int getAge() {
        return age;
    }

    public  String getCarInsuranceName() {
        return getCar().flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse(UNKNOWN);
    }

    /*如果你一定要实现序列化的域模型，作为替代方案，可以提供一个能访问声明为Optional、变量值可能缺失的接口，
    代码清单如下：*/
   /* public class Person implements Serializable {
        private Car car;
        public Optional<Car> getCarAsOptional() {
            return Optional.ofNullable(car);
        }
    }*/
}

