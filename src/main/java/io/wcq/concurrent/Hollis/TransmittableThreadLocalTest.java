package io.wcq.concurrent.Hollis;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/26 10:02
 */
public class TransmittableThreadLocalTest {
    public static void main(String[] args) {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        // 在父线程中设置
        context.set("value-set-in-parent");

        // 在子线程中可以读取，值是"value-set-in-parent";
        String value = context.get();
    }


    public void ThreadPool () {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        // 在父线程中设置
        context.set("value-set-parent");

//        Runnable task = new Runnable;
//        // 额外的处理，生成了修饰的对象ttlRunnable
//        Runnable ttlRunnable = TtlRunnable.get(task);
//        e


    }
}
