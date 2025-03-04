package javalang.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/*
to showcase reads can't happen when writter is in sync block
showcasing how fixed thread pool executor work
 */
public class SynchronizedBlockExample {

    private static class SharedStore {
        public static final int SLEEP = 5000;
        int x = 1;

        synchronized int getX() {
            System.out.println("returning value: " + x);
            return x;
        }

        synchronized void setX(int y) {
            System.out.println("going to sleep " + SLEEP + " before setting ");
            try {
                sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            x = y;
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
