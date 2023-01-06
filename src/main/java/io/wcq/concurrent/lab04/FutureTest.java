package io.wcq.concurrent.lab04;

import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * @author: Chaoqun Wu
 * @description 测试Future的异步返回结果
 * @date: 2023/1/6 16:22
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String  call() throws Exception {
                System.out.println(System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println(System.currentTimeMillis());
                return "测试Future获取异步结果";
            }
        });
        System.out.println(future.get());
        executorService.shutdown();
    }
}
