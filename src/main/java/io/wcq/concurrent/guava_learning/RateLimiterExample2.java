package io.wcq.concurrent.guava_learning;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Chaoqun Wu
 * @description 令牌桶算法实现 acquire
 * @date: 2023/2/1 21:02
 */
@Slf4j
public class RateLimiterExample2 {
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            rateLimiter.acquire();
            handle(i);
        }
    }
    private static void handle(int i) {
        log.info("{}", i);
    }
}
