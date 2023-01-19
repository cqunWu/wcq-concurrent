package io.wcq.concurrent.threadPool_learning;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2023/1/17 14:00
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            threadLocal.set("threadA: "+ Thread.currentThread().getName());
            System.out.println("threadA的本地变量为："+ threadLocal.get());
            threadLocal.remove();
            System.out.println("threadA移除本地变量后值为："+ threadLocal.get());
        });
        Thread threadB = new Thread(()->{
            threadLocal.set("threadB: "+ Thread.currentThread().getName());
            System.out.println("threadB的本地变量为："+ threadLocal.get());
            System.out.println("threadB没有移除本地变量后值为："+ threadLocal.get());
        });
        threadA.start();
        threadB.start();
    }
}
