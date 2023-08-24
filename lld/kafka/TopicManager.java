package lld.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {
    private final Map<String, List<BlockingQueue>> topicQueueMap;

    private static int BQ_SIZE = 100;
    /*
    public TopicManager(Map<String, List<BlockingQueue>> topicQueueMap) {
        this.topicQueueMap = topicQueueMap;
    }
*/

    public TopicManager() {
        this.topicQueueMap = new ConcurrentHashMap<>();
    }
    public List<BlockingQueue> createTopic(String topicName, int numParts){

        List<BlockingQueue> queueList = topicQueueMap.getOrDefault( topicName, new ArrayList<BlockingQueue>());
        for (int i = 0; i < numParts; i++) {
            BlockingQueue blockingQueue = new ArrayBlockingQueue<String>( BQ_SIZE );
            queueList.add( blockingQueue );
        }
        // maybe below is not required
        topicQueueMap.put( topicName, queueList );
        return  queueList;
    }

    public List<BlockingQueue> getBlockingQueues(String topicName){
        List<BlockingQueue> queueList = topicQueueMap.get( topicName );
        return queueList;
    }



}
