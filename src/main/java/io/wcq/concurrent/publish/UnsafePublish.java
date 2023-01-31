package io.wcq.concurrent.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: Chaoqun Wu
 * @description 不安全的发布代码示例
 * @date: 2023/1/31 11:20
 */
@Slf4j
public class UnsafePublish {
    private String[] states = {"a","b","c"};
    public String[] getStates(){
        //return states;
        //String[] tmp = states;
        // 深拷贝防止通过方法修改私有对象值
        String[] tmp = states.clone();
        return tmp;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
