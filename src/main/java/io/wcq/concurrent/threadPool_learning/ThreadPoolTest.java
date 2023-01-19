package io.wcq.concurrent.threadPool_learning;

import java.util.stream.IntStream;

/**
 * @author: Chaoqun Wu
 * @description 自定义线程池测试类
 * @date: 2023/1/19 9:07
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10);
        IntStream.range(0,10).forEach((i) -> {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+ "---->Hello World");
            });
        });
    }
}
