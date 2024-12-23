package javalang.threading.quest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FooBarWaitNotify {

    private static final int CAPACITY = 2;
    private static final int TIMES = 5;
    private static Object lock = new Object();
    private static boolean printFoo = true;

    public static void main(String[] args) {

        Runnable fooPrinter = () -> {
            for (int i = 0; i < TIMES; i++) {
                synchronized (lock) {
                    while (!printFoo) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("foo");
                    printFoo = false;
                    lock.notifyAll();
                }
            }
        };

        Runnable barPrinter = () -> {
            for (int i = 0; i < TIMES; i++) {
                synchronized (lock) {
                    while (printFoo) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("bar");
                    printFoo = true;
                    lock.notifyAll();
                }
            }

        };

        ExecutorService pool = Executors.newFixedThreadPool(CAPACITY);
        pool.submit(fooPrinter);
        pool.submit(barPrinter);
        try {
            pool.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("termination completed!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
