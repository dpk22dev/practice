package lld.kafka;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    private static int CONSUMER_SLEEPTIME = 1_000;
    BlockingQueue<String> blockingQueue;
    String cgName;
    String consName;


    boolean shouldStop = false;
    public Consumer(BlockingQueue<String> blockingQueue, String cgName, String consName) {
        this.blockingQueue = blockingQueue;
        this.cgName = cgName;
        this.consName = consName;
    }

    @Override
    public void run() {
        super.run();
        String data;
        while( !shouldStop ){
            try {
                data = blockingQueue.take();
                System.out.println("consumer " + consName + " got " + data);
                Thread.sleep(CONSUMER_SLEEPTIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public boolean isShouldStop() {
        return shouldStop;
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

}
