package com.chou.optional;

import java.util.Optional;

/**
 * Created by Administrator on 2017/4/13.
 */
public class Car {
    private Optional<Insurance> insurance = Optional.empty();//车可能进行了保险，也可能没有保险，所以将这个字段声明为Optional
    public Optional<Insurance> getInsurance() { return insurance; }

}