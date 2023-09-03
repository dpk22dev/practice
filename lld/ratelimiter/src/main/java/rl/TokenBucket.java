package rl;

import org.joda.time.DateTime;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket extends Thread implements RateLimiterIface {
    private int TOKEN_REFILL_PER_MIN = 10;
    private int MAX_TOKEN = 10000;
    private Map<String, Integer> bucket;
    private static boolean shouldRun=true;

    TokenBucket(){
        this.bucket = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        super.run();
        // lousy code, can have different count
        while( shouldRun ){
            Map<String, Integer> newBucket = new ConcurrentHashMap<>();
            for(Map.Entry<String,Integer> entry: this.bucket.entrySet() ){
                newBucket.put( entry.getKey(), Math.max(entry.getValue() + TOKEN_REFILL_PER_MIN, MAX_TOKEN) );
            }
            bucket = newBucket;
            try {
                Thread.sleep(60_000 );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // below can cause raceconditions
    @Override
    public boolean process(String userId, RequestType requestType, DateTime dateTime) {
        if( !bucket.containsKey(userId) ){
            bucket.put( userId, TOKEN_REFILL_PER_MIN );
        }
        /*
        int tokenCount = bucket.get(userId);
        if( tokenCount == 0 ){
            return false;
        }
        */

        int tokenCount = bucket.compute( userId, (u,tc) -> tc != 0 ? tc-1 : 0 );
        return tokenCount > 0 ? true : false;
    }
}
