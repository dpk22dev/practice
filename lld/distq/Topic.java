package lld.distq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Topic {
    private int numPartitions;
    private String name;
    private static int PARTITION_CAPACITY = 100;

    List<PartitionQueue> queueList;

    public Topic(int numPartitions, String name) {
        this.numPartitions = numPartitions;
        this.name = name;
        this.queueList = new ArrayList<>(numPartitions);
        for ( int i = 0; i < numPartitions; i++ ){
            queueList.add( new PartitionQueue(PARTITION_CAPACITY));
        }
    }

    public int getPartition( String k ){
        //@todo return hashed partition
        int foo;
        try {
            foo = Integer.parseInt(k) % numPartitions;
        }
        catch (NumberFormatException e) {
            foo = ThreadLocalRandom.current().nextInt(0, numPartitions + 1);
        }
        return foo;
    }

    public PartitionQueue getPartitionQueue( String k ){
        //@todo return hashed partition
        return queueList.get(0);
    }

    public List<PartitionQueue> getAllPartitionQueue( ){
        //@todo return hashed partition
        return queueList;
    }

    public String getName() {
        return name;
    }
}
