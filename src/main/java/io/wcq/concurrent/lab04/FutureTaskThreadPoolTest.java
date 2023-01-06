package io.wcq.concurrent.lab04;

import java.util.concurrent.*;

/**
 * @author: Chaoqun Wu
 * @description 线程池提交FutureTask获取异步结果
 * @date: 2023/1/6 17:28
 */
public class FutureTaskThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return "测试FutureTaskTest获取异步结果";
            }
        });
        executorService.execute(futureTask);
        System.out.println(futureTask.get());
        executorService.shutdown();
    }
}
