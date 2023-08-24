package lld.kafka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Driver {

    private static String TOPIC_NAME = "t1";
    private static String CG_NAME = "c1";
    private static int NUM_PARTS = 5;

    public static void main(String[] args) {

        List<BlockingQueue> blockingQueueList;
        TopicManager topicManager = new TopicManager();
        blockingQueueList = topicManager.createTopic(TOPIC_NAME, NUM_PARTS);
        ProducerManager producerManager = new ProducerManager();
        producerManager.createProducers(TOPIC_NAME, NUM_PARTS, blockingQueueList);
        producerManager.startProducers(TOPIC_NAME);

        ConsumerManager consumerManager = new ConsumerManager();
        consumerManager.createConsumers( blockingQueueList, CG_NAME);
        consumerManager.startConsumers( CG_NAME );

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        producerManager.stopProducers(TOPIC_NAME);
        consumerManager.stopConsumers(CG_NAME);

        /*
        p k 0 // kill 0th producer
        c k -1 // all
        p r 0 // restart 0th producer
         */
/*
        while( true ) {
            String inp = takeInputs();
            processInp( inp, producerManager, consumerManager );
        }
*/

    }
/*
    private static void processInp(String inp, ProducerManager producerManager, ConsumerManager consumerManager) {
        String[] commands = inp.split(" ");
        if( commands[0] == "p" ){
            if( commands[1] == "k" ) {
                if( commands[2] == "-1")
                    producerManager.stopProducers(TOPIC_NAME);
                else
                    producerManager.stopProducer(TOPIC_NAME, commands[2]);
            } else if( commands[1] == "r" ){
                producerManager.restartProducer(TOPIC_NAME, commands[2]);
            }
        } else if( commands[0] == "c" ){
            if( commands[1] == "k" ) {
                if( commands[2] == "-1")
                    consumerManager.stopConsumers(CG_NAME);
                else
                    consumerManager.stopConsumers(CG_NAME, commands[2]);
            } else if( commands[1] == "r" ){
                consumerManager.restartConsumers(CG_NAME, commands[2]);
            }
        }
    }

    private static String takeInputs() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
        try {
            String name = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

 */
}
