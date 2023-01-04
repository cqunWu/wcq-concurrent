package io.wcq.concurrent.executor.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2022/12/1 18:04
 */
public class HashMapTest {
    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();
        final Integer key = 65535;
        final String value = "test";
        new Thread(() -> {
                IntStream.range(0, key).forEach(key1 -> map.put(key1, "someV"));
        }) .start();
        while (true){
            if (null == map.get(key)) {
                throw  new RuntimeException("HashMap is not thread safe");
            }
        }


    }
}
