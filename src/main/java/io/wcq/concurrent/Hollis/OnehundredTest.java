package io.wcq.concurrent.Hollis;

/**
 * @author: Chaoqun Wu
 * @description
 * @date: 2024/2/22 15:43
 */
public class OnehundredTest {
    private static int i = 1;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Print(i)).start();
        }
    }

    private static  class Print implements Runnable {
        private final int index;

        public  Print(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            while (true) {
                synchronized (Print.class) {
                    if (i >= 101) {
                        return;
                    }
                    System.out.println("Thread-" + index + " " + i++);
                }
            }
        }
    }

}
