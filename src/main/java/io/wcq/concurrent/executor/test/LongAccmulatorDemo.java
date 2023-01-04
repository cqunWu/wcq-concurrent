package io.wcq.concurrent.executor.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2022/12/7 19:19
 */
public class LongAccmulatorDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator((x,y) -> x+y, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        IntStream.range(1,10).forEach(i -> executorService.submit(() -> accumulator.accumulate(i)));
        Thread.sleep(2000);
        System.out.println(accumulator.getThenReset());
    }
}
