package io.wcq.concurrent.Hollis;

import java.util.concurrent.*;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/1/10 18:58
 */
public class FutureAndCallableExample {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Callable<String> callable = () ->{
//            System.out.println("Entered Callable");
//            Thread.sleep(2000);
//            return "Hello from Callable";
//        };
//        FutureTask<String> futureTask = new FutureTask<>(callable);
//        Thread thread = new Thread(futureTask);
//        thread.start();
//
//        System.out.println("Do something else while callable is getting executed");
//        System.out.println("Retrieved: " + futureTask.get());
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable= () -> {
            System.out.println("Entered Callable");
            Thread.sleep(2000);
            return "Hello form Callable";
        };
        System.out.println("Submitting Callable");
        Future<String> future = executor.submit(callable);

        System.out.println("Do something else while callable is getting executed");
        System.out.println("Retrieved：" + future.get());

        executor.shutdown();
    }

}
