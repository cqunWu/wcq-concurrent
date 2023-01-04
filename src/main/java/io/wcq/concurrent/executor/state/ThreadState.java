package io.wcq.concurrent.executor.state;

/**
 * @author: Chaoqun Wu
 * @description 测试各个线程状态
 * @date: 2022/11/4 17:25
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new WaitingTime(), "WaitingTimeThread").start();
        new Thread(new WaitingState(), "WaitingStateThread").start();
        new Thread(new BlockedThread(), "BlockedThread-01").start();
        new Thread(new BlockedThread(), "BlockedThread-02").start();
    }
}
