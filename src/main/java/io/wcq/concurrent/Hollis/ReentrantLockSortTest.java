package io.wcq.concurrent.Hollis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/22 16:52
 */
public class ReentrantLockSortTest {
    private static final int WORK_COUNT = 3;
    private static int countIndex = 0;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        final List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < WORK_COUNT; i++) {
            // 为每一个线程分配一个condition
            Condition condition = LOCK.newCondition();
            conditions.add(condition);
            Worker worker = new Worker(i, conditions);
            worker.start();
        }

    }

    static class Worker extends Thread {
        int index;
        List<Condition> conditions;

        public Worker(int index, List<Condition> conditions) {
            super("Thread-" + index);
            this.index = index;
            this.conditions = conditions;
        }

        private void signalNext() {
            int nextIndex = (index + 1) % conditions.size();
            conditions.get(nextIndex).signal();
        }

        @Override
        public void run() {
            while (true){
                // 锁住 保证操作间同时只有一个线程
                LOCK.lock();
                try {
                    // 如果当前线程不满足打印条件，则等待
                    if (countIndex % 3 != index) {
                        conditions.get(index).await();
                    }
                    if (countIndex > 100) {
                        // 唤醒下一个线程，保证程序正常退出
                        signalNext();
                        // 退出循环 线程运行结束
                        return;
                    }
                    System.out.println(this.getName() + " " + countIndex);
                    // 计数器 + 1
                    countIndex ++;
                    // 通知下一个干活
                    signalNext();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }

            }
        }


        private static volatile  int count = 0;
        private static final int MAX = 100;
        static class OtherWorker implements Runnable{

            @Override
            public void run() {
                while (count < MAX) {
                    while (count % 3 != countIndex) {
                        Thread.yield();
                    }
                    if (count > MAX) {
                        return;
                    }
                    System.out.println("Thread-" + countIndex + " " + count);
                    count ++;
                }
            }
        }
    }

}
