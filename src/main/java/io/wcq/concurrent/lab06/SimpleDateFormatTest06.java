package io.wcq.concurrent.lab06;

import java.security.PrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: Chaoqun Wu
 * @description 使用DateTimeFormmat方式处理
 * @date: 2023/1/10 16:01
 */
public class SimpleDateFormatTest06 {
    //执行总次数
    private static final int EXECUTE_COUNT = 1000;
    //同时运行的线程数量
    private static final int THREAD_COUNT = 20;
    //simpleDateFormat对象
    //private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                       // dateTimeFormatter.parse("2022-01-10");
                    LocalDate.parse("2022-01-10", dateTimeFormatter);
//                    } catch (ParseException e) {
//                        System.out.println("线程" + Thread.currentThread().getName() + "格式化日期失败");
//                        e.printStackTrace();
//                        System.exit(1);
//                    } catch (NumberFormatException e) {
//                        System.out.println("线程" + Thread.currentThread().getName() + "格式化日期失败");
//                        e.printStackTrace();
//                        System.exit(1);
//                    }
                    semaphore.release();
                } catch (InterruptedException e){
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }
}
