package io.wcq.concurrent.threadPool_learning;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2023/1/13 17:04
 */
public class TimerTest {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("测试Time类");
            }
        }, 1000, 1000);


        //获取学生类的字节码对象
        Class clazzClass=Class.forName("java.util.Timer");
        //获取学生对象
        Object timeClass=clazzClass.newInstance();
        //获取私有的字段对象
        Field field=clazzClass.getDeclaredField("thread");
        field.setAccessible(true);//设置发射时取消Java的访问检查，暴力访问
        //使其获取到值
        Thread thread = (Thread)field.get(timeClass);
        thread.join();

        // 主线程需要等待子线程执行；
//        Thread thread = timer.getClass().getDeclaredConstructor("thread");
//        thread.join();
        //Thread.sleep(10000);
        timer.cancel();

    }
}
