package javalang.threading;

class StampedReadLock {
/*
    public static void main( String args[] ) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // create an instance of StampedLock
        StampedReadLock stampedLock = new StampedReadLock();

        // main thread attempts to acquire the lock twice, which is granted
        long readStamp1 = stampedLock.readLock();

        long readStamp2 = stampedLock.readLock();

        try {

            // create 3 threads
            for (int i = 0; i < 3; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            long readStamp = stampedLock.readLock();
                            System.out.println("Read lock count in spawned thread " + stampedLock.getReadLockCount());

                            // simulate thread performing read of shared state
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ie) {
                                // ignore
                            } finally {
                                stampedLock.unlockRead(readStamp);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });
            }
            // let the main thread simulate work for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            // wait for spawned threads to finish
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);

            // remember to unlock
            stampedLock.unlock(readStamp1);
            stampedLock.unlock(readStamp2);
        }

        System.out.println("Read lock count in main thread " + stampedLock.getReadLockCount());
        System.out.println("stampedLock.isReadLocked() " + stampedLock.isReadLocked());
    }
*/

}