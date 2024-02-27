package io.wcq.concurrent.hollis;

import com.sun.javaws.exceptions.ErrorCodeResponseException;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: ChaoqunWu
 * @time: 2024/2/3 23:27
 */
public class CASExample {

    private static Unsafe unsafe;



    static {
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            unsafe = (Unsafe) theUnsafeField.get(null);
        } catch (NoSuchFieldException  | IllegalAccessException e) {
           e.printStackTrace();
        }
    }

    private static class Counter{
        private volatile int value;

        public Counter(int initiaValue) {
            this.value = initiaValue;
        }

        // CAS操作
        public void increment() {
            int current;
            int next;
            do {
                current =value;
                next = current +1;
            } while (!unsafe.compareAndSwapInt(this, valueOffset, current,next));
        }

    }
    // 获取value字段在Counter对象中的偏移量
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(Counter.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(0);

        // 创建多个线程并发更新计数器
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for ( Thread thread: threads
             ) {
            thread.join();
        }

        // 输出最终计数值
        System.out.println("Final counter value: " + counter.value);
    }
}

