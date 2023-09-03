package lld.distq;

public class Driver {

    public static void main(String[] args) {
        TopicManager topicManager = new TopicManager();
        topicManager.createTopic("topic10", 10);
        Topic topic10 = topicManager.getTopic("topic10");
        ConsumerManager consumerManager = new ConsumerManager( topicManager );
        consumerManager.addConsumerGroup("topic10", "cg10", 10);
        Producer producer = new Producer( topicManager );
        producer.produce("topic10", "1", "one");
        producer.produce("topic10", "2", "two");
        Consumer consumer = new Consumer( consumerManager );
        String res = consumer.consume("topic10", "cg10", "0" );
        System.out.println( res );
    }


}
