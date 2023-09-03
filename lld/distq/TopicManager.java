package lld.distq;

import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {
    private ConcurrentHashMap<String, Topic> topicMap;

    public TopicManager() {
        this.topicMap = new ConcurrentHashMap<>();
    }

    public void createTopic(String topicName, int numPart){
        topicMap.put( topicName, new Topic(numPart, topicName));
    }

    public Topic getTopic( String name ){
        return topicMap.get( name );
    }

    //@TODO: 03/09/23 create delete topic
    public void deleteTopic( String topicName ){

    }

}
