package javalang.threading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class LongAdderDemo {

    static AtomicLong atomicLong = new AtomicLong(0);
    static LongAdder longAdder = new LongAdder();

    public static void main(String args[]) throws Exception {
        withAtomicLong();
        withLongAddr();
    }

    static void withAtomicLong() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(15);
        long start = System.currentTimeMillis();

        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000000; i++) {
                            atomicLong.incrementAndGet();
                        }
                    }
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }

        long timeTaken = System.currentTimeMillis() - start;
        System.out.println("Time taken by atomic long " + timeTaken + " milliseconds and count " + atomicLong.get());
    }

    static void withLongAddr() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(15);
        long start = System.currentTimeMillis();

        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000000; i++) {
                            longAdder.increment();
                        }
                    }
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }
        long timeTaken = System.currentTimeMillis() - start;
        System.out.println("Time taken by long adder " + timeTaken + " milliseconds and count = " + longAdder.sum());
    }
}