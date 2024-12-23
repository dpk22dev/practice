package javalang.threading;

import java.util.concurrent.*;

class FixedThreadPoolWithShutdown {

    public static void main(String args[]) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            for (int i = 0; i < 5; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {

                            System.out.println(Thread.currentThread().getName() + " acquires the lock.");
                            // simulate work by sleeping
                            try {
                                Thread.sleep(ThreadLocalRandom.current().nextInt(20));
                            } catch (InterruptedException ie) {
                                // ignore for now
                            }

                        }
                    }
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }

        System.out.println("Main thread exiting successfully");
    }
}