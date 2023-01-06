package io.wcq.concurrent.lab03;

/**
 * @author: Chaoqun Wu
 * @description 线程的顺序，Thread.join()方法保证顺序
 * @date: 2023/1/6 14:14
 */
public class ThreadSort02 {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("thread0");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(() -> System.out.println("thread1"));
        Thread thread2 = new Thread(() -> System.out.println("thread2"));
        Thread thread3 = new Thread(() -> System.out.println("thread3"));
        thread0.start();
        thread0.join();
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();

    }
}
