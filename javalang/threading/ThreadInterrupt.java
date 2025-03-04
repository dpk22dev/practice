package javalang.threading;

/*
send interupt when sleeping and non-sleeping
 */
public class ThreadInterrupt {

    public static final int THREAD_SLEEP = 5;
    public static final int MAIN_SLEEP = 500;

    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();
        System.out.println("Main: going to sleep");

        try {
            Thread.sleep(MAIN_SLEEP);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        b.interrupt();
        try {
            Thread.sleep(MAIN_SLEEP);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        b.interrupt();
        try {
            Thread.sleep(MAIN_SLEEP);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        b.interrupt();
    }

    static class ThreadB extends Thread {
        private volatile boolean stop = false;

        public void stopThread() {
            this.stop = true;
        }

        public void run() {
            synchronized (this) {
                while (!stop) {
                    //System.out.println("ThreadB: going to sleep with stop:" + stop);
                    /* current thread is not sleeping and interrupt is received */
                    if (isInterrupted()) {
                        System.out.println("ThreadB: interrupted flag is set");
                        interrupted(); // get and clear interrupt flag
                    }


                    try {
                        sleep(THREAD_SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // caught interrupted exception, did nothing => loop will continue
                        // ideally should make stop = true to stop loop
                    }


                    //System.out.println("ThreadB: woke up after sleeping");
                }

            }
            System.out.println("ThreadB: exiting");

        }
    }

}
