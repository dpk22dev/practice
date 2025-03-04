package javalang.threading;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Exceptions are swallowed by Executor service. Thread isn't killed, runnables are scheduled on same thread
weiredly, even after global exception handler is attached in each thread in thread factory, still no exception is caught !!
even overriding afterExecute is not getting exception

below demonstrates way to create thread pool executor with thread factory

 */
public class RunnableFailureExample {

    public static final int THREAD_MAX = 10;
    public static final int BOUND = 4;

    public static void main(String[] args) {
        List<Runnable> runnables = IntStream.range(0, THREAD_MAX).mapToObj((inx) -> {
            Runnable r = () -> {
                int rand = ThreadLocalRandom.current().nextInt(0, BOUND);
                System.out.println(Thread.currentThread().getName() + " got val " + rand);
                if (rand > BOUND / 2) {
                    System.out.println(Thread.currentThread().getName() + " Going to raise exception");
                    throw new RuntimeException("Exception!!! rand got" + rand);
                }
            };
            return r;
        }).collect(Collectors.toList());

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("Exception occurred in thread: " + t.getName() + ". Exception: " + e);
                    }
                });
                return t;
            }
        };

//        ExecutorService executorService = Executors.newFixedThreadPool(1, threadFactory);
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2, // Core pool size
                5, // Maximum pool size
                1, TimeUnit.SECONDS, // Keep alive time
                new LinkedBlockingQueue<>(), // Work queue
                threadFactory // Custom thread factory with exception handler
        ) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                if (t != null) {
                    System.out.println("Uncaught exception occurred in thread: " + Thread.currentThread().getName() + ". Exception: " + t);
                }
            }
        };

        try {
            runnables.stream().forEach((r) -> executorService.submit(r));
        } finally {
            executorService.shutdown();
            try {
                executorService.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
