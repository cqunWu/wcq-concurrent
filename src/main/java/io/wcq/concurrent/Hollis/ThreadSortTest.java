package io.wcq.concurrent.Hollis;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/21 20:48
 */
public class ThreadSortTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() ->{
            System.out.println("Thread 1 running");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 running");
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("Thread 3 running");
        });
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}
