package javalang.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

public class RWLockExample {
    private static class SharedStore {
        public static final int SLEEP = 5000;
        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        int x = 1;

        int getX() {
            System.out.println("returning value: " + x);
            try {
                rwLock.readLock().lock();
                return x;
            } finally {
                rwLock.readLock().unlock();
            }

        }

        void setX(int y) {
            System.out.println("going to sleep " + SLEEP + " before setting ");
            try {
                rwLock.writeLock().lock();
                sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                rwLock.writeLock().unlock();
            }
            x = y;
            System.out.println("set value " + x);
        }
    }

    public static void main(String[] args) {

        SharedStore sharedStore = new SharedStore();
        Runnable wr = () -> {
            sharedStore.setX(10);
        };
        Runnable rd = () -> {
            int x = sharedStore.getX();
            System.out.println(x);
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            executorService.submit(wr);
            executorService.submit(rd);
        } finally {
            executorService.shutdown();
            try {
                executorService.awaitTermination(1, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
