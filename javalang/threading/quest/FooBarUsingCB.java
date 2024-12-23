package javalang.threading.quest;

import java.util.concurrent.*;

/*
not working using cyclic barrier. tried from chatgpt but not correct
 */

public class FooBarUsingCB {
    private static int TIMES = 5;

    private static final int CAPACITY = 2;

    static class FooPrinter implements Runnable {
        CyclicBarrier cb;

        public FooPrinter(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            for (int i = 0; i < TIMES; i++) {
                System.out.println("foo");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class BarPrinter implements Runnable {
        CyclicBarrier cb;

        public BarPrinter(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            for (int i = 0; i < TIMES; i++) {
                try {
                    cb.await();
                    System.out.println("bar");
                    cb.reset();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                //cb.reset();
            }
        }
    }

    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(2);
        ExecutorService pool = Executors.newFixedThreadPool(CAPACITY);
        FooPrinter fooPrinter = new FooPrinter(cb);
        BarPrinter barPrinter = new BarPrinter(cb);
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
