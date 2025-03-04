package javalang.threading;

/*
without volatile counter value becomes wrong
 */
public class ThreadRaceCondition {
    private static int NT = 10000;
    private static int SLEEP = 10;

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread threads[] = new Thread[NT];
        for (int i = 0; i < NT; i++) {
            threads[i] = new Thread(new MyRunnable(counter), "th" + i);
        }
        for (int i = 0; i < NT; i++) {
            threads[i].start();
        }
        for (int i = 0; i < NT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("after finish counter" + counter.getCount());
    }

    static class MyRunnable implements Runnable {

        /*
        this volatile is imp. each thread inc, dec value in counter. if each thread doesn't have same visibility
        counter value can go haywire
         */
        volatile Counter counter;

        MyRunnable(Counter c) {
            this.counter = c;
        }

        @Override
        public void run() {
            System.out
                    .println(Thread.currentThread().getName() + ": before increment counter -> " + counter.getCount());
            counter.increment();
            System.out
                    .println(Thread.currentThread().getName() + ": after increment  counter -> " + counter.getCount());
            // try {
            // Thread.sleep(1000);
            // } catch (InterruptedException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            System.out
                    .println(Thread.currentThread().getName() + ": before decrement counter -> " + counter.getCount());
            counter.decrement();
            System.out
                    .println(Thread.currentThread().getName() + ": after decrement  counter -> " + counter.getCount());

        }
    }

    static class Counter {
        private int count = 0;

        public int getCount() {
            return count;
        }

        public void increment() {
            count++;
        }

        public void decrement() {
            count--;
        }
    }

}
