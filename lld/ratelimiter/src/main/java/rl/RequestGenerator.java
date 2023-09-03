package rl;

import java.util.concurrent.*;

public class RequestGenerator {
    public void generateRequest(){

        BlockingQueue tasks = new ArrayBlockingQueue(1000);

        ThreadPoolExecutor tpe = new ThreadPoolExecutor( 3, 20, 100, TimeUnit.SECONDS, tasks,
                null, (r,e) -> {
            System.out.println("exception in task");
        } );


    }
}
