package lld.distq;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class PartitionQueue {
    private int capacity;
    private int tail;
    private final ArrayList<String> list;

    //cgname+threadId
    private final ConcurrentHashMap<String, Integer> offsetMap;

    public PartitionQueue(int capacity) {
        this.capacity = capacity;
        this.tail = 0;
        list = new ArrayList<>( capacity );
        this.offsetMap = new ConcurrentHashMap<>();
    }

    public boolean append(String k, String v){
        this.list.add(tail, v);
        this.tail++;
        return false;
    }

    public String consume( String cgThreadId ){
        offsetMap.computeIfAbsent( cgThreadId, k -> 0);
        int pos = offsetMap.get( cgThreadId );
        if( pos >= tail ){
            // can't consume anything block
        }
        String res = list.get( pos );
        pos++;
        offsetMap.put( cgThreadId, pos);
        return res;
    }
}
