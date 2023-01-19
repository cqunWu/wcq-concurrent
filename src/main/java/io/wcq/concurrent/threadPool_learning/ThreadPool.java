package io.wcq.concurrent.threadPool_learning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

/**
 * @author: Chaoqun Wu
 * @description 自定义线程池
 * @date: 2023/1/18 17:32
 */
public class ThreadPool {

    // 默认阻塞队列大小
    private static final int DEFAULT_WORKQUEUE_SIZE = 5;
    // 模拟实际的线程池用阻塞阻塞来实现生成消费模式
    private BlockingQueue<Runnable> workQueue;
    // 模拟实际的线程池使用List集合保存线程池内部的工作线程
    private List<workThread> workThreads = new ArrayList<workThread>();

    public ThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        // 创建poolSize个工作线程并将其加入到workThreads集合中
        IntStream.range(0, poolSize).forEach((i) -> {
          workThread workThread = new workThread();
          workThread.start();
          workThreads.add(workThread);
        });
    }

    public ThreadPool(int poolSize) {
        this(poolSize, new LinkedBlockingDeque<>(DEFAULT_WORKQUEUE_SIZE));
    }

    public void execute(Runnable task) {
        try {
            workQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /** 内部类WorkThread，模拟线程池中的工作线程
     * 主要的作用就是消费workQueue队列中任务，并执行
     * 由于工作线程需要不断从workQueue中获取任务，使用while(true)循环不断尝试消费队列中的任务
     */
    class workThread extends Thread {
        @Override
        public void run() {
            // 不断获取队列中的任务
            while (true) {
                try {
                    Runnable workTask = workQueue.take();
                    workTask.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
