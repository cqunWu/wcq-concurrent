package io.wcq.concurrent.executor.state;

/*
 * @description: 创建waitingstate类，获取当前类Class对象的synchronized锁，无论创建
 * 多少个对象，synchronized锁都是同一个，并且线程会处于等待状态，接下来，在synchronized中使用
 * 当前类的Class对象的wait()方法，来验证线程的waiting状态
 * @author: Chaoqun Wu
 * @date: 2022/11/4 16:56
 **/
public class WaitingState implements Runnable {


    @Override
    public void run() {
        while (true) {
            synchronized (WaitingState.class) {
                try {
                    WaitingState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
