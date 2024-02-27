package io.wcq.concurrent.Hollis;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/21 19:40
 */
public class SharedDataExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> shareData = new ConcurrentHashMap<String, String>();
        MyThread thread = new MyThread(shareData);
        thread.start();
        shareData.put("key", "value");
        System.out.println("shareData in main thread: " + shareData.get("key"));
    }
}
     class MyThread extends Thread {
        ConcurrentHashMap<String, String> shareData;
        public MyThread(ConcurrentHashMap<String, String> data) {
            this.shareData = data;
        }
        @Override
        public void run() {
            shareData.put("key", "new value");
            System.out.println("shareData in child thread: " + shareData.get("key"));
        }
    }

