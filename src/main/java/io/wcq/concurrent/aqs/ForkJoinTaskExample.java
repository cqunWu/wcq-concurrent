package io.wcq.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author: Chaoqun Wu
 * @description ForkJoin测试类
 * @date: 2023/1/31 10:33
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {
    public static final int threshold = 2;
    private int start;
    private int end;
    public ForkJoinTaskExample(int start, int end){
        this.start = start;
        this.end =end;
    }
    @Override
    protected Integer compute() {
        AtomicInteger sum = new AtomicInteger();
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if(canCompute){
//            for (int i = start; i <= end; i++) {
//                sum += i;
//            }
            IntStream.range(start, end+1).forEach((i) -> {
                sum.addAndGet(i);
            });
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum.set(leftResult + rightResult);
        }
        return sum.get();
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，计算1+2+3+4
        ForkJoinTaskExample task = new ForkJoinTaskExample(1, 100);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            log.info("result: {}", result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
