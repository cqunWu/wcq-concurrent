package io.wcq.concurrent.threadPool_learning;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * @author: Chaoqun Wu
 * @description synchronized
 * @date: 2023/2/2 10:40
 */
@Slf4j
public class TestCount {
    private static long count = 0;
    public synchronized long getCount() {
        log.info("getCount = {}", count);
        return count;
    }
    public static synchronized void incrementCount(){
        count += 1;
        log.info("count = {}", count);
    }

    public static void main(String[] args) {
        IntStream.range(0,2000).forEach(i -> new Thread(() ->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TestCount testCount = new TestCount() ;
            TestCount.incrementCount();
            testCount.getCount();
        }).start());
    }
}
