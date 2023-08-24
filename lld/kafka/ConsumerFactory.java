package lld.kafka;

import java.util.concurrent.BlockingQueue;

public class ConsumerFactory {

    public Consumer createConsumer(BlockingQueue blockingQueue, String cgName, String consName ) {
        return new Consumer( blockingQueue, cgName, consName);
    }
}
