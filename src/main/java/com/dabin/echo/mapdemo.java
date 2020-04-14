package com.dabin.echo;

import java.util.HashMap;

/**
 * @ClassName:mapdemo
 * @author: dabin
 * @date: 2020/4/922:26
 */
public class mapdemo {
    public static void main(String[] args) {

//        HashMap map = new HashMap(14);
//        map.put("dabin", 123);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                nvpengyou();
            }

        });

        for (; ; ) {
            thread.start();
        }
    }

    public static void nvpengyou() {
        new GiflFriend();
    }
}
