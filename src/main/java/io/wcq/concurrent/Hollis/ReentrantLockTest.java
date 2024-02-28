package io.wcq.concurrent.Hollis;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/27 19:07
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        reentrantLockTest.method1();

    }
    public synchronized  void method1() {
        method2();
        System.out.println("invoke method1");
    }

    public synchronized void method2() {
        System.out.println("invoke method2");
    }
}
