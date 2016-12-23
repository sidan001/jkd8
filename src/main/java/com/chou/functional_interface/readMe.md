### 函数式接口

1. 函数式接口只能有一个抽象方法
2. 可以有多个默认方法
3. 可以有`java.lang.Object`中的`public`方法,不会增加抽象方法的个数
4. `instances of functional interfaces can be created with lambda expressions, method references, or constructor references`
5. 只有一个抽象方法，并且没有加 `@functionalInterface` 的接口也会被编译器看着是函数式接口
