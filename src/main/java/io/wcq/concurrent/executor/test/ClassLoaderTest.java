package io.wcq.concurrent.executor.test;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2022/12/20 18:08
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader:"+classLoader);
        System.out.println("parent loader:" + classLoader.getParent());
        System.out.println("grandparent loader:"+classLoader.getParent().getParent());
    }
}
