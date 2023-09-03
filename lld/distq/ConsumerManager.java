package lld.distq;

import lld.distq.exceptions.ConsumerGroupAlreadyAssignedException;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConsumerManager {

    // cg, topic
    ConcurrentHashMap<String, Topic> cgTopicMap;

    // cg_cons#, PartQueue
    ConcurrentHashMap<String, PartitionQueue> qSubscriptionMap;

    TopicManager topicManager;
    public ConsumerManager( TopicManager topicManager ) {
        this.cgTopicMap = new ConcurrentHashMap<>();
        this.qSubscriptionMap = new ConcurrentHashMap<>();
        this.topicManager = topicManager;
    }

    public void addConsumerGroup(String topicName, String cgName, int numConsumer){
        Topic existingTopic = topicManager.getTopic( topicName );
        if( existingTopic.equals( cgTopicMap.get( cgName ) ) ){
            throw new ConsumerGroupAlreadyAssignedException();
        }
        cgTopicMap.put( cgName, existingTopic );
        List<PartitionQueue> partitionQueueList = existingTopic.getAllPartitionQueue();
        int cIndex = 0; String key;
        for( int i = 0; i < partitionQueueList.size(); i++ ){
            key = cgName + "_" + cIndex;
            qSubscriptionMap.put( key, partitionQueueList.get(i));
            cIndex = (cIndex + 1 )%numConsumer;
        }
    }

    public PartitionQueue getPartitionQueueForConsumer( String cgThreadName ){
        return qSubscriptionMap.get( cgThreadName );
    }

    public Topic getTopicForConsumer( String cg ){
        return cgTopicMap.get(cg);
    }

    public static String getConsumerId( String cg, String tid){
        return cg + "_" + tid;
    }

}
