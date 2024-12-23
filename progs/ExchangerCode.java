package progs;

import java.util.concurrent.atomic.AtomicReference;

public class ExchangerCode {

    private static class Exchanger {
        AtomicReference<Thread> t1;
        String t1val;
        AtomicReference<Thread> t2;
        String t2val;

        public synchronized String exchange(String x) {
            Thread ct = Thread.currentThread();
            if (t1 == null) {
                t1 = new AtomicReference<>(ct);
                t1val = x;
                try {
                    while (t2 == null) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                t2 = new AtomicReference<>(ct);
                t2val = x;
                notifyAll();
            }
            if (ct == t1.get()) return t2val;
            else return t1val;
        }

    }

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                String t1val = "t1";
                try {
                    sleep(1000);
                    String r = exchanger.exchange(t1val);
                    System.out.println("thread t1 received: " + r);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                super.run();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                String t2val = "t2";
                try {
                    sleep(3000);
                    String r = exchanger.exchange(t2val);
                    System.out.println("thread t2 received: " + r);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                super.run();
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join(5000);
            t2.join(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
