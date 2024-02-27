package io.wcq.concurrent.Hollis;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Chaoqun Wu
 * @description CountDownLatch对象，用来做线程通信
 * @date: 2024/2/22 9:59
 */
public class CountDownLatchThreadExecute {
    public static void main(String[] args) throws InterruptedException {
        // 创建CountDownLatch对象，用来做线程通信
        CountDownLatch latch =  new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);

        // 创建并启动线程T1
        Thread t1 = new Thread(new MyThread1(latch), "T1");
        t1.start();

        // 等待线程T1执行完
        latch.await();

        // 创建并启动线程T2
        Thread t2 = new Thread(new MyThread1(latch2), "T2");
        t2.start();

        // 等待线程T2执行完
        latch2.await();

        // 创建并启动线程T3
        Thread t3 = new Thread(new MyThread1(latch3), "T3");
        t3.start();

        // 等待线程T3执行完
    }
}

class MyThread1 implements Runnable {
    private CountDownLatch latch;

    public MyThread1(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is Running.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 完成一个线程，计数器减1
            latch.countDown();
        }
    }
}
