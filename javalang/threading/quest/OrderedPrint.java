package javalang.threading.quest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
if you are passing ref, put wait notify on that. program still runs incorrectly without error, but that's wrong
ref.wait();
 */
public class OrderedPrint {

    private static final int CAPACITY = 5;
    volatile Integer counter = 1;

    static class Printer implements Runnable {
        int inx;
        OrderedPrint ref;

        public Printer(int inx, OrderedPrint ref) {
            this.inx = inx;
            this.ref = ref;
        }

        @Override
        public void run() {
            System.out.println("thread with " + inx + " starting");
            synchronized (ref) {
                while (ref.counter != inx) {
                    try {

                        ref.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("thread with " + inx + " printed " + ref.counter);
                ref.counter++;
                ref.notifyAll();

            }
            System.out.println("thread with " + inx + " exiting");
        }
    }

    public static void main(String[] args) {

        OrderedPrint orderedPrint = new OrderedPrint();
        ExecutorService pool = Executors.newFixedThreadPool(CAPACITY);
        for (int i = 1; i <= 3; i++) {
            Printer printer = new Printer(i, orderedPrint);
            pool.submit(printer);
        }
        try {
            pool.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("termination completed!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
