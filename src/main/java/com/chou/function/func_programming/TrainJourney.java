package com.chou.function.func_programming;

/**
 *破坏式更新和函数式更新
 */
public class TrainJourney {

    public int price;
    public TrainJourney onward;
    public TrainJourney(int p, TrainJourney t) {
        price = p;
        onward = t;
    }



    static TrainJourney link(TrainJourney a, TrainJourney b){
        if (a==null) return b;
        TrainJourney t = a;
        while(t.onward != null){
            t = t.onward;
        }
        t.onward = b;
        return a;
    }


    static TrainJourney append(TrainJourney a, TrainJourney b){
        return a==null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

}
