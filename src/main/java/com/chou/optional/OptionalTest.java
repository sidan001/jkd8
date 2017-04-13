package com.chou.optional;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/13.
 */
public class OptionalTest {
    public static void main(String[] args) {
        //Optional的map方法将参数mapper的结果 U u 用Optional包装后返回Optional<U>
        //实现了Optional<T> --> Optional<U>的转换
        Optional<Insurance> optInsurance = Optional.ofNullable(null);
        Optional<String> name = optInsurance.map(Insurance::getName);

        //Optional的flatMap将参数mapper的结果Optional<U>直接返回Optional<U>
        //实现了Optional<T> --> Optional<U>的转换
        Optional<Person> optPerson = Optional.of(new Person());
        final String carInsuranceName = getCarInsuranceName(optPerson);
        System.out.println(carInsuranceName);
    }

    //需要将Optional类型的成员变量初始化为Optional.empty(),否则会抛出NullPointerException
    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        Insurance cheapestCompany = new Insurance();
        return cheapestCompany;
    }
    public Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance_UseLambada(
            Optional<Person> person, Optional<Car> car) {
        return Objects.nonNull(person) && Objects.nonNull(car)
                ?person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)))
                :Optional.empty();
    }
}
