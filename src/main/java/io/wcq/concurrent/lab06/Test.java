package io.wcq.concurrent.lab06;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2023/1/12 20:42
 */
public class Test {
    public static void main(String[] args) {
        int i = 0;
        for( ;;) {

            ++i;
            System.out.println("1");
            if (i == 10) {
                return;
            }
        }
    }
}
