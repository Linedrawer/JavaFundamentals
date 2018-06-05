package org.zeroturnaround.jf.homework3.task2;

import java.util.*;

public class Task2 {

    public static <T> List<T> unique(List<? extends T>... lists) {
        HashMap<T, Boolean> map = new HashMap<>();
        for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                if (!map.containsKey(lists[i].get(j))) {
                    map.put(lists[i].get(j), true);
                } else {
                    map.put(lists[i].get(j), false);
                }
            }
        }
        List<T> list = new ArrayList<>();
        map.forEach((x,y) -> {if(y) list.add(x);});
        return list;
    }
}
