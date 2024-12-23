package javalang.threading;

class StampedWriteLock {
/*
    public static void main( String args[] ) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // create an instance of StampedLock
        StampedLock stampedLock = new StampedLock();

        // main thread acquires the write lock before spawning read thread
        long stamp = stampedLock.writeLock();

        try {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Attempting to acquire read lock");
                    long readStamp = stampedLock.readLock();
                    System.out.println("Read lock acquired");
                    stampedLock.unlock(readStamp);
                }
            });

            // Having the thread sleep for 3 seconds so that the read thread
            // gets blocked
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } finally {
            // unlock write
            System.out.println("Write lock being released");
            stampedLock.unlock(stamp);

            // wait for read thread to exit
            executorService.shutdown();
            try {
                executorService.awaitTermination(1, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

 */
}