package com.chou.function.lambada;

/**
 * Created by Administrator on 2017/4/12.
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {
    public String handleWork(String text){
        return text.replaceAll("labda", "lambda");
    }
}
