package io.wcq.concurrent.executor.test;

import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2022/11/23 19:53
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
        // 创建生产者
        Runnable producer = () -> {
          while (true){
              try {
                  Object object = new Object();
                  queue.put(object);
                  System.out.println(object.hashCode());
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };
        new Thread(producer).start();
        new Thread(producer).start();
        // 创建消费者
        Runnable consumer = () -> {
            while (true){
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
