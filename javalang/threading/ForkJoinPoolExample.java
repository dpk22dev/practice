package javalang.threading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
fork join pool extends AbstractExecutorService just like scheduled thread pool executor; that means it can execute tasks.
fjp works on divide and conquer.
abstract ForkJoinTask extends Future and can wait for result. RecursiveTask<T>, RecursiveAction<T>  are concrete impl of it
each worker thread has deque, if any worker's deque gets empty it steals from back of another thread
good for CPU intensive tasks, not good for io tasks

 */
public class ForkJoinPoolExample {
    private static int ITEMS = 1000;

    private static class SumTask extends RecursiveTask<Integer> {
        private final int[] arr;
        private final int s, e;
        private static final int THRESHOLD = 5;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.s = start;
            this.e = end;
        }

        @Override
        public Integer compute() {
            System.out.println(Thread.currentThread() + " computing " + s + ":" + e);
            if (e - s < THRESHOLD) {
                int sum = 0;
                for (int i = s; i <= e; i++) {
                    // mimic action
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    sum += arr[i];
                }
                return sum;
            } else {
                int m = (s + e) / 2;
                SumTask ltask = new SumTask(arr, s, m);
                SumTask rtask = new SumTask(arr, m + 1, e);
                ltask.fork(); // remember to fork it
                rtask.fork();
                //int lresult = ltask.compute();
                int lresult = ltask.join();
                int rresult = rtask.join();
                return lresult + rresult;
            }

        }

    }

    public static void main(String[] args) {
        int arr[] = new int[ITEMS];
        for (int i = 0; i < ITEMS; i++) {
            arr[i] = i;
        }

        ForkJoinPool fjp = new ForkJoinPool();
        SumTask sumTask = new SumTask(arr, 0, arr.length - 1);
        //fjp.submit( sumTask );
        int result = fjp.invoke(sumTask);
        System.out.println("Sum: " + result);

        // Monitor the pool
        System.out.println("Stolen Tasks: " + fjp.getStealCount());
        System.out.println("Active Threads: " + fjp.getActiveThreadCount());
        System.out.println("Pool Size: " + fjp.getPoolSize());
        System.out.println("Queued Tasks: " + fjp.getQueuedTaskCount());
    }
}
