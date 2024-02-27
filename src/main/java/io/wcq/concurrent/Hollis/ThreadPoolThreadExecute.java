package io.wcq.concurrent.Hollis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/22 14:45
 */
public class ThreadPoolThreadExecute {
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 创建并启动线程T1
        executorService.submit(new MyThread4("T1"));

        // 创建并启动线程T2
        executorService.submit(new MyThread4("T2"));

        // 创建并启动线程T3
        executorService.submit(new MyThread4("T3"));

        // 关闭线程池
        executorService.shutdown();
    }
}

class MyThread4 implements Runnable{
    private String name;

    public MyThread4(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(name + " is Running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
