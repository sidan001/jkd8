package com.chou.function.lambada;

/**
 * Created by Administrator on 2017/4/12.
 */
public class HeaderTextProcessing extends ProcessingObject<String> {
    public String handleWork(String text){
        return "From Raoul, Mario and Alan: " + text;
    }
}
