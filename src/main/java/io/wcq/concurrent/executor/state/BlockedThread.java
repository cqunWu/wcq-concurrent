package io.wcq.concurrent.executor.state;

/**
 * @author: Chaoqun Wu
 * @description BlockeThread主要在synchronized代码块中，当启动两个BlockedThread线程，先启动的线程处于TIMED_WAITING,后启动的线程处于BLOCKED状态
 * @date: 2022/11/4 17:19
 */
public class BlockedThread implements Runnable{
    @Override
    public void run() {
        synchronized (BlockedThread.class) {
            while (true) {
                WaitingTime.waitSecond(100);
            }
        }
    }
}
