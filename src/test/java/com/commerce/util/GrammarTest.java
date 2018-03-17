package com.commerce.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrammarTest {
    @Test
    public void testLambda() {

        String[] strArr = {"aaaaa", "bbb", "cc", "dddd"};

        List strList = Arrays.asList(strArr);

        // list.forEach( `lambda func` );
        // lambda func must have expected argument
        strList.forEach((s) -> {
            System.out.println(s);
        });

        Map<String, Integer> map = new HashMap<>();

        map.put("aaaaa", 5);
        map.put("bbb", 3);
        map.put("c", 1);
        map.put("dd", 2);


        map.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });


    }
}