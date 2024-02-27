package io.wcq.concurrent.Hollis;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/22 15:01
 */
public class CompletableFutureThreadExecute {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建CompletableFuture对象
//        CompletableFuture<Void> future1 =
//                CompletableFuture.runAsync(new MyThread5("T1"));
//        // 等待线程T1完成
//        future1.join();
//
//        // 创建CompletableFuture对象
//        CompletableFuture<Void> future2 =
//                CompletableFuture.runAsync(new MyThread5("T2"));
//        // 等待线程T1完成
//        future2.join();
//
//        // 创建CompletableFuture对象
//        CompletableFuture<Void> future3 =
//                CompletableFuture.runAsync(new MyThread5("T3"));
//        // 等待线程T1完成
//        future3.join();
        // 创建CompletableFuture对象
        CompletableFuture<Void> future = CompletableFuture.runAsync(
           new MyThread5("T1")).thenRun(new MyThread5("T2")).
                thenRun(new MyThread5("T3"));
        future.get();
    }

}

class MyThread5 implements Runnable {
    private String name;

    public MyThread5(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // 模拟执行任务
        try {
            Thread.sleep(1000);
            System.out.println(name + " is Running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
