package com.my.utils;

import java.util.HashMap;
import java.util.Map;

public class Sort {

    public static Map<Integer,String> getListSortForEvent(){
        Map<Integer,String> result=new HashMap<>();
        result.put(1,"По даті від сьогодні до");
        result.put(2,"По даті від майбутнього до сьогодні");
        result.put(3,"Кількість репортів від більшого до меншого");
        result.put(4,"Кількість репортів від меншого до більшого ");
        result.put(5,"Кількість учасників від більшого до меншого");
        result.put(6,"Кількість учасників від меншого до більшого ");
        return result;
    }
}
