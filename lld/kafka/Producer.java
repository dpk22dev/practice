package lld.kafka;

import java.util.concurrent.BlockingQueue;
import java.util.random.RandomGenerator;

public class Producer extends Thread{
    private final BlockingQueue blockingQueue;
    private static int PRODUCER_SLEEPTIME = 1_000;

    private boolean shouldStop = false;
    String name;
    Producer(String name, BlockingQueue blockingQueue){
        System.out.println("creating producer with name: " + name);
        this.name = name;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        super.run();
        String data;
        int random;
        while( !shouldStop ){
            try {
                random = RandomGenerator.getDefault().nextInt();
                data = name+"_"+random;
                System.out.println( name + "thread generating data: " + data);
                this.produce( data );
                sleep( PRODUCER_SLEEPTIME );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("thread" + name + " is going to stop with shouldStop: " + shouldStop);
    }

    public void produce( String data ){
        this.blockingQueue.add( data );
    }

    public boolean isShouldStop() {
        return shouldStop;
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

}
