package javalang.threading;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
I think this is not correct use case of read write lock.
Better would be multiple writers taking write lock condition to signal each other
AND multiple readers taking read lock to ensure write lock is not held while reading

consumer is also a writer in below case as it removes element from sharedresource
 */

public class SingalUsingRentrantReadWriteLock {

    static class SharedResource {
        private LinkedList<Integer> data = new LinkedList<>();
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        // below causes UnsupportedOperationException
        //private Condition dataAvailable = lock.readLock().newCondition();
        private Condition dataAvailable = lock.writeLock().newCondition();
        private Condition spaceAvailable = lock.writeLock().newCondition();
        private Condition some = lock.readLock().newCondition();

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
            lock.writeLock().lock();
            /*
            // lock.readLock().lock();
            The Condition objects (dataAvailable and spaceAvailable) are created using the lock.writeLock().
            ReentrantReadWriteLock's read lock does not support Condition, that's why await won't work
            */
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
                lock.writeLock().unlock();
                // lock.readLock().unlock();
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
