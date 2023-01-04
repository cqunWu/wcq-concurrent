package io.wcq.concurrent.executor.test;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2022/12/13 19:30
 */
public class Singleton {
    private static volatile  Singleton singleton;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
