package lld.kafka;

import java.util.concurrent.BlockingQueue;

public class ProducerFactory {

    public Producer createProducer(String producerName, BlockingQueue blockingQueue){
        return new Producer( producerName, blockingQueue );
    }
}
