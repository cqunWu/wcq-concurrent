package io.wcq.concurrent.Hollis;

import java.util.concurrent.TimeUnit;

/**
 * @author: Chaoqun Wu
 * @description 守护线程测试
 * @date: 2024/1/9 17:30
 */
public class Main {
//    public static void main(String[] args) {
//        Thread t1 = new Thread();
//        System.out.println(t1.isDaemon());
//        t1.setDaemon(true);
//        System.out.println(t1.isDaemon());
//        t1.start();
//        t1.setDaemon(false);
//    }
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("I'm child thread..");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("I'm main thread..");

    }
}
