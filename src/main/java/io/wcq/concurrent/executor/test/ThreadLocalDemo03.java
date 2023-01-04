package io.wcq.concurrent.executor.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2022/12/7 19:44
 */
public class ThreadLocalDemo03 {
//    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
     public static ExecutorService threadPool = new ThreadPoolExecutor(16,
    16,
    1000,
        TimeUnit.MINUTES,
        new SynchronousQueue());
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finall = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalDemo03().date(finall);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds){
        Date date = new Date(1000*seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(date);
    }
}
