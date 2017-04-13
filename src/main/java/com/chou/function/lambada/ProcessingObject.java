package com.chou.function.lambada;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * <p>责任链模式是一种创建处理对象序列（比如操作序列）的通用方案。一个处理对象可能需要
 * 在完成一些工作之后，将结果传递给另一个对象，这个对象接着做一些工作，再转交给下一个处
 * 理对象，以此类推。
 * 通常，这种模式是通过定义一个代表处理对象的抽象类来实现的，在抽象类中会定义一个字
 * 段来记录后续对象。一旦对象完成它的工作，处理对象就会将它的工作转交给它的后继
 */
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;
    public void setSuccessor(ProcessingObject<T> successor){
        this.successor = successor;
    }
    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }
    abstract protected T handleWork(T input);

    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        System.out.println(p1.handle("Aren't labdas really sexy?!!"));

//        使用Lambda表达式
        UnaryOperator<String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline =
                headerProcessing.andThen(spellCheckerProcessing);
        System.out.println(pipeline.apply("Aren't labdas really sexy?!!"));


    }
}
