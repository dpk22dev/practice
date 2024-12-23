package javalang.threading.quest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBarUsingSignal {

    private static final int CAPACITY = 2;
    private static final int TIMES = 5;
    private static ReentrantLock reLock = new ReentrantLock();
    private static Condition printFooCondition = reLock.newCondition();
    private static boolean printFoo = true;

    public static void main(String[] args) {


        Runnable fooPrinter = () -> {
            for (int i = 0; i < TIMES; i++) {
                try {
                    reLock.lock();
                    while (!printFoo) {
                        printFooCondition.await();
                    }
                    System.out.println("foo");
                    printFoo = false;
                    //printFooCondition.signal();
                    printFooCondition.signalAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    reLock.unlock();
                }
            }
        };

        Runnable barPrinter = () -> {
            for (int i = 0; i < TIMES; i++) {
                try {
                    reLock.lock();
                    while (printFoo) {
                        printFooCondition.await();
                    }
                    System.out.println("bar");
                    printFoo = true;
                    //printFooCondition.signal();
                    printFooCondition.signalAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    reLock.unlock();
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
