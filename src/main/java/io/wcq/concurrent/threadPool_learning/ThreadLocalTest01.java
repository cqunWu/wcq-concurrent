package io.wcq.concurrent.threadPool_learning;

/**
 * @author: Chaoqun Wu
 * @description 测试ThreadLocal传递性
 * @date: 2023/1/17 15:33
 */
public class ThreadLocalTest01 {
    private  static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public static void main(String[] args) {
        threadLocal.set("threadLocal");
        System.out.println("主线程threadLocal = " + threadLocal.get());
        Thread thread = new Thread(()->{
            System.out.println("子线程threadLocal = " + threadLocal.get());
        });
        thread.start();
    }
}
