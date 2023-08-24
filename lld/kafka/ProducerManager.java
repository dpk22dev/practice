package lld.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.random.RandomGenerator;

public class ProducerManager {
    ConcurrentHashMap<String, List<Producer>> producerMap = new ConcurrentHashMap<>();
    ProducerFactory producerFactory = new ProducerFactory();
    public void createProducers(String topicName, int numThreads, List<BlockingQueue> blockingQueueList){
        List<Producer> producers = producerMap.get(topicName);
        if( producers == null || producers.size() == 0 ){
            List<Producer> producersList = new ArrayList<>();
            for (int i = 0; i < numThreads; i++) {
                Producer p = producerFactory.createProducer( topicName+"_"+i, blockingQueueList.get(i) );
                producersList.add(p);
            }
            producerMap.put( topicName, producersList);
        } else {
            System.out.printf("producers are already present for this topic:", topicName);
        }
    }

    public void startProducers(String topicName){
        List<Producer> producers = producerMap.get(topicName);
       // producers.stream().forEach( p -> p.start() );
        for (int i = 0; i < producers.size(); i++) {
            System.out.println("starting producer " + i );
            producers.get(i).start();
        }
    }


/*    public void produceData( String topicName, String key, String data){
        List<Producer> producerList = producerMap.get( topicName );
        // use key to get respective producer
        int x = RandomGenerator.getDefault().nextInt( producerList.size() );
        Producer chosen = producerList.get( x );
        chosen.produce( data );
    }*/

    public void stopProducers( String topicName){
        System.out.println("producer manager is going to set shouldStop to true");
        List<Producer> producers = producerMap.get(topicName);
        for (int i = 0; i < producers.size(); i++) {
            producers.get(i).setShouldStop(true);
        }
    }

    public void stopProducer( Producer producer ){
        System.out.println("producer manager is going to set shouldStop to true");
        producer.setShouldStop(true);
    }

}
