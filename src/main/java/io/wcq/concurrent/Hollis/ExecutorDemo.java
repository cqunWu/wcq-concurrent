package io.wcq.concurrent.Hollis;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/27 9:28
 */
public class ExecutorDemo {
    private static ExecutorService executor =
            new ThreadPoolExecutor(10, 10 ,60L, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10));

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-ppol-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(1024),
            namedThreadFactory);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            pool.execute(() -> {
                System.out.println("Hello World");
            });
        }
    }
}
