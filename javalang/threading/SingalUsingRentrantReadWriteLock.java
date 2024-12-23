package javalang.threading;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
consumer not starting buggy code
 */

public class SingalUsingRentrantReadWriteLock {

    static class SharedResource {
        private LinkedList<Integer> data = new LinkedList<>();
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        // below causes UnsupportedOperationException
        //private Condition dataAvailable = lock.readLock().newCondition();
        private Condition dataAvailable = lock.writeLock().newCondition();
        private Condition spaceAvailable = lock.writeLock().newCondition();

        public void produce(int newData) {
            lock.writeLock().lock();
            try {
                // Check if there is space available to produce
                while (data.size() >= 10) {
                    System.out.println("Producer waiting for space...");
                    spaceAvailable.await();
                }

                // Produce data
                data.add(newData);
                System.out.println("Produced: " + newData);

                // Signal that data is available
                dataAvailable.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }

        public void consume() {
            lock.readLock().lock();
            try {
                // Check if there is data available to consume
                while (data.isEmpty()) {
                    System.out.println("Consumer waiting for data...");
                    dataAvailable.await();
                }

                // Consume data
                int consumedData = data.removeFirst();
                System.out.println("Consumed: " + consumedData);

                // Signal that space is available
                spaceAvailable.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
    }


    static class Producer implements Runnable {
        private SharedResource sharedResource;

        public Producer(SharedResource sharedResource) {
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
            while (true) {
                sharedResource.produce((int) (Math.random() * 100));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private SharedResource sharedResource;

        public Consumer(SharedResource sharedResource) {
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
            while (true) {
                sharedResource.consume();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // Create producer and consumer threads
        Thread producerThread = new Thread(new Producer(sharedResource));
        Thread consumerThread = new Thread(new Consumer(sharedResource));

        // Start the threads
        producerThread.start();
        consumerThread.start();
    }
}
