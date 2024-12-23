package javalang.threading.quest;

import java.util.concurrent.*;

/*
not working
*/
public class FooBarUsingCL {
    private static int TIMES = 5;

    private static final int CAPACITY = 1;

    static class FooPrinter implements Runnable {
        CountDownLatch cb;

        public FooPrinter(CountDownLatch cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            //    for (int i = 0; i < TIMES; i++) {
            System.out.println("foo");
            try {
                cb.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // }
        }
    }

    static class BarPrinter implements Runnable {
        CountDownLatch cb;

        public BarPrinter(CountDownLatch cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
//                for (int i = 0; i < TIMES; i++) {
            try {
                cb.await();
                System.out.println("bar");
                cb.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //cb.reset();
//                }
        }
    }

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService pool = Executors.newFixedThreadPool(CAPACITY);
        FooPrinter fooPrinter = new FooPrinter(countDownLatch);
        BarPrinter barPrinter = new BarPrinter(countDownLatch);
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
