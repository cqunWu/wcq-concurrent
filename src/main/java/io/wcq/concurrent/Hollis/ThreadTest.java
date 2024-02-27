package io.wcq.concurrent.Hollis;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/21 19:19
 */
public class ThreadTest {
    public static InheritableThreadLocal<Integer> shareData = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        shareData.set(0);
        MyThread thread = new MyThread();
        thread.start();
        shareData.set(shareData.get() + 1);
        System.out.println("sharedData in main thread: " + shareData.get());
    }
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("shareData in child thread: " +  shareData.get());
            shareData.set(shareData.get() + 1);
            System.out.println("shareData  in child thread after increment: " + shareData.get());
        }
    }
}
