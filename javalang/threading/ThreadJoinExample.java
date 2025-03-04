package javalang.threading;

/*
experimentation with multiple scenarios
tries to join multiple times
sets global exception handler
 */
public class ThreadJoinExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable("t1"), "t1");
        Thread t2 = new Thread(new MyRunnable("t2"), "t2");
        Thread t3 = new Thread(new MyRunnable("t3"), "t3");

        t1.setUncaughtExceptionHandler((t, ex) -> {
            System.out.println(t.getName() + " threw " + ex.getMessage());
        });
        t1.start();
        t1.interrupt();


        try {
            t1.join(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Main: after t1 joined");
        /*
        // start second thread after waiting for 2 seconds or if it's dead
        t2.start();

        // start third thread only when first thread is dead
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: 2 - after t1 joined");

        t3.start();

        // let all threads finish execution before finishing main thread
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
*/
        System.out.println("All threads are dead, exiting main thread");

    }

}

class MyRunnable implements Runnable {
    private String name;

    MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() throws RuntimeException {
        System.out.println("Thread started: " + Thread.currentThread().getName());
        try {
            Thread.sleep(100);
            throw new RuntimeException("hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended: " + Thread.currentThread().getName());
    }
}