package javalang.threading;

public class ThreadWaitInterrupt {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t = new Thread(myRunnable);
        t.start();
        try {
            /*
            even though get method of MyRunnable runs on main thread's stack.
            but synchronized block in MyRunnable waits on myRunnable object itself.
            notify in run method wakes up thread running get method which is blocked on myRunnable object
             */
            Integer res = (Integer) myRunnable.get();
            System.out.println("main: got result " + res);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
            e.printStackTrace();
        }
    }

    static class MyRunnable implements Runnable {
        Integer num = null;

        @Override
        public void run() {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized (this) {
                System.out.println(Thread.currentThread() + " notifying all threads");
                num = 5;
                notifyAll();
            }

        }

        public synchronized Object get() throws InterruptedException {
            while (null == num) {
                System.out.println(Thread.currentThread() + " waiting for result");
                wait();
            }
            System.out.println(Thread.currentThread() + " returning result" + num);
            return num;
        }

    }

}
