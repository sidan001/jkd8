package com.chou.default_method;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface InterfaceABC extends InterfaceB,InterfaceC {
    // inherits unrelated defaults for hello() from InterfaceB  and InterfaceC
    //继承有相同签名默认方法的两个接口，会出现下面的错误，需要实现覆盖有冲突签名的方法
    @Override
    default void hello() {
        InterfaceB.super.hello();//显式地选择调用InterfaceB中的方法
    }

}
