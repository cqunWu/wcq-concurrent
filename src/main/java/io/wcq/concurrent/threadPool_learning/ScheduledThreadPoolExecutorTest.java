package io.wcq.concurrent.threadPool_learning;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2023/1/13 18:00
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("测试ScheduledThreadPool");
        }, 1, 1, TimeUnit.SECONDS);
        // 主线程休眠10s
        Thread.sleep(10000);
        System.out.println("开始关闭线程池");
        scheduledExecutorService.shutdown();
        boolean isClose = false;
        do{
            isClose = scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("正在等待线程池中的任务执行完成");

        } while (!isClose);
        System.out.println("所有线程执行结束，线程池关闭");

    }
}
