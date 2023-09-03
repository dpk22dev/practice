package lld.distq;

import lld.distq.exceptions.ConsumerNotSubscribedException;

import static lld.distq.ConsumerManager.getConsumerId;

public class Consumer {

    ConsumerManager consumerManager;
    public Consumer(ConsumerManager consumerManager) {
        this.consumerManager = consumerManager;
    }

    // topicName, cgroupName, cgThreadId
    public String consume(String topic, String cg, String cgThreadId ){
        Topic existingTopic = consumerManager.getTopicForConsumer( cg );
        if( ! topic.equals( existingTopic.getName() )){
            throw new ConsumerNotSubscribedException();
        }
        PartitionQueue queue = consumerManager.getPartitionQueueForConsumer( getConsumerId(cg, cgThreadId) );

        String res = queue.consume( getConsumerId(cg, cgThreadId) );
        return res;
    }
}
