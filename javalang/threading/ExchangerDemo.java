package javalang.threading;

import java.util.concurrent.*;

/*
each thread gets paired with other thread
in odd #threads one thread will get paired with 2 threads others would be 1 to 1
 */
class ExchangerDemo {

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(9);

        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        work();
                    }
                });
            }

        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }
    }

    static void work() {
        String name = Thread.currentThread().getName();
        String received = "";

        try {
            received = exchanger.exchange(name);
        } catch (InterruptedException ie) {
            // ignore for now
        }

        System.out.println("I am thread " + name + " and I received the string " + received);
    }

}