package io.wcq.concurrent.guava_learning;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: Chaoqun Wu
 * @description 令牌桶苏纳法实现tryAcquire
 * @date: 2023/2/1 20:52
 */
@Slf4j
public class RateLimiterExample {
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) {
        for (int index = 0; index < 100; index++) {
            if (rateLimiter.tryAcquire(190, TimeUnit.MICROSECONDS)) {
                handle(index);
            }
        }
    }
    private static void handle(int i) {
        log.info("{}", i);
    }
}
