package javalang.threading;

import static java.lang.Thread.sleep;

public class ThreadWaitNotifyTest {
        public static void main(String [] args) throws InterruptedException {
      ThreadB b = new ThreadB();
      b.start();

      /* sequecing of wait notify is imp. if main thread goes into sleep and threadB is run first.
      main thread will stuck at b.wait(). uncomment below to see
       */
            // sleep(5000);
      synchronized(b) {
      /*
      synchronized can be applied on thread object itself. threadB won't enter in its synch block untill sleep here
      is completed and this thread goes into wait state
       */
                System.out.println("Main: Waiting for b to complete...");
          sleep(1000);
                b.wait();
                System.out.println("Main: after wait, resuming");
                System.out.println("Main: Total is: " + b.total);

      }
    }


    static class ThreadB extends Thread {
    int total;

    public void run() {
        System.out.println("ThreadB: starting run");
        synchronized (this) {
            System.out.println("ThreadB: going inside synch block");
            for (int i = 0; i < 100; i++) {
                total += i;
            }
            notify();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("ThreadB: woke up after sleeping in synchronized");
        }

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("ThreadB: woke up after sleeping outside synchronized");

    }
    }

}
