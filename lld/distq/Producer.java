package lld.distq;

public class Producer {

    private TopicManager topicManager;

    public Producer(TopicManager topicManager) {
        this.topicManager = topicManager;
    }

    public void produce(String topicName, String k, String v){
        Topic topic = topicManager.getTopic( topicName );
        PartitionQueue pq = topic.getPartitionQueue( k );
        pq.append( k, v);
    }
}
