package io.wcq.concurrent.Hollis;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/22 10:53
 */
public class CyclicBarrierThreadExecute {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        // 创建CycliBarrier对象，用来做线程通信
        CyclicBarrier barrier = new CyclicBarrier(2);

        // 创建并启动线程T1
        Thread t1 = new Thread(new MyThread2(barrier), "T1");
        t1.start();
        // 等待线程T1执行完
        barrier.await();

        // 创建并启动线程T2
        Thread t2 = new Thread(new MyThread2(barrier), "T2");
        t2.start();
        // 等待线程T1执行完
        barrier.await();

        // 创建并启动线程T3
        Thread t3 = new Thread(new MyThread2(barrier), "T3");
        t3.start();
        // 等待线程T1执行完
        barrier.await();
    }



}

class MyThread2 implements Runnable{

    private CyclicBarrier barrier;

    public MyThread2(CyclicBarrier barrier) {
        this.barrier = barrier;
    }
    @Override
    public void run() {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is Running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 等待其他线程完成
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
