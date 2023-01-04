package io.wcq.concurrent.executor.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2022/11/30 18:09
 */
public class Test {
    public static int i;
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                   // i++;
                    ++i;
                }
            }
        };
//        new Thread (() -> {
//            for (int j = 0; j < 10000; j++) {
//                i++;
//            }
//        }).start();
//        new Thread (() -> {
//            for (int j = 0; j < 10000; j++) {
//                i++;
//            }
//        }).start();
        Thread thread1 = new Thread(r);
        thread1.start();
        Thread thread2 = new Thread(r);
        thread2.start();
        // 当前main方法主线程等待++线程执行完再结束
        thread1.join();
        thread2.join();
        System.out.println(i);

    }
}
