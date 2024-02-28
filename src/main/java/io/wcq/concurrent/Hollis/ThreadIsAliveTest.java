package io.wcq.concurrent.Hollis;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/27 16:38
 */
public class ThreadIsAliveTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t1 end");
        });
        Thread t2 = new Thread(() -> {
            synchronized (t1) {
                System.out.println("t2 begin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("t2 end");
                System.out.println("t1 isAlive:" + t1.isAlive());
            }
        });
        t1.start();
        t2.start();
    }
}
