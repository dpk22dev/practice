package lld.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class ConsumerManager {

    ConsumerFactory consumerFactory;

    Map<String, List<Consumer>> consumerMap;

    ConsumerManager(){
        this.consumerFactory = new ConsumerFactory();
        consumerMap = new ConcurrentHashMap<>();
    }

    // for simplicity keeping #consumers same as producers
    public void createConsumers(List<BlockingQueue> blockingQueueList, String cgName){
        String consName;
        List<Consumer> consumerList = consumerMap.get(cgName);
        if( consumerList == null || consumerList.size() == 0 ) {
            consumerList = new ArrayList<>();
            for (int i = 0; i < blockingQueueList.size(); i++) {
                consName = cgName + "_" + i;
                consumerList.add( this.consumerFactory.createConsumer(blockingQueueList.get(i), cgName, consName) );
            }
            consumerMap.put( cgName, consumerList);
        }else{
            System.out.println("consumer list is already created");
        }
    }


    public void startConsumers( String cgName ) {
        List<Consumer> consumerList = consumerMap.get(cgName);
        for (int i = 0; i < consumerList.size(); i++) {
            consumerList.get(i).start();
        }
    }

    public void stopConsumers(String cgName) {
        List<Consumer> consumerList = consumerMap.get(cgName);
        for (int i = 0; i < consumerList.size(); i++) {
            consumerList.get(i).setShouldStop(true);
        }
    }
}
