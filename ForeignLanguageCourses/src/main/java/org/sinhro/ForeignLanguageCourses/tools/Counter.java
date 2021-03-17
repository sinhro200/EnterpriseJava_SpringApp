package org.sinhro.ForeignLanguageCourses.tools;

import java.util.HashMap;
import java.util.Map;

public class Counter {

    private final Object o;

    public Counter(Object obj){
        this.o = obj;
    }

    public void inc(){
        Counter.inc(o);
    }

    public void dec(){
        Counter.dec(o);
    }

    public Integer value(){
        return Counter.value(o);
    }

    public Integer valueThenInc(){
        return Counter.valueThenInc(o);
    }

    public Integer valueThenDec(){
        return Counter.valueThenDec(o);
    }


    private static Map<Object, Integer> map = new HashMap<>();

    public static void inc(Object o) {
        map.put(o, value(o) + 1);
    }

    public static void dec(Object o) {
        map.put(o, value(o) - 1);
    }

    /**
     * If there is no value with specified key
     * put one with value 0
     *
     * @param o object for which counter is needed
     * @return count
     */
    public static Integer value(Object o) {
        Integer cnt = map.get(o);
        if (cnt == null) {
            cnt = 0;
            map.put(o, cnt);
        }
        return cnt;
    }

    public static Integer valueThenInc(Object o) {
        Integer res = value(o);
        inc(o);
        return res;
    }

    public static Integer valueThenDec(Object o) {
        Integer res = value(o);
        dec(o);
        return res;
    }
}
