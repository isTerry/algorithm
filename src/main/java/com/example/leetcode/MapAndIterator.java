package com.example.leetcode;

import java.util.*;

public class MapAndIterator {
    public static void main(String[] args){
//        listIterateAndRemove();
        mapIterateAndRemove();
    }

    public static void mapIterateAndRemove(){
        Map<Integer,String> map = new HashMap<>(2^2);
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.put(4,"d");

//        Set<Integer> keySet = map.keySet();
//        for(Integer it : keySet){
//            System.out.println(it);
//            System.out.println(map.get(it));
//        }
//
//        Set<Map.Entry<Integer,String>> entrySet = map.entrySet();
//        for(Map.Entry<Integer,String> entry : entrySet){
//            System.out.println(entry.toString());
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
//        Iterator<Map.Entry<Integer,String>> entryIt = map.entrySet().iterator();
//        while (entryIt.hasNext()){
//            Map.Entry<Integer,String> entry = entryIt.next();
//            System.out.println(entry.toString());
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//            entryIt.remove();
//        }
//        System.out.println(map.size());

        Collection<String> values = map.values();
        System.out.println(values);


    }

    public static void listIterateAndRemove(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.size());
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            it.remove();
        }
        System.out.println(list.size());
    }
}
