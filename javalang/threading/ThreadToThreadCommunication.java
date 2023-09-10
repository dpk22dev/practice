package javalang.threading;


import java.util.concurrent.*;

class Producer{
    ArrayBlockingQueue arrayBlockingQueue;
    ThreadPoolExecutor threadPoolExecutor;
    Service service;
    RejectedExecutionHandler rejectedExecutionHandler;

    int maxParallelThreads = 0;
    Producer( Service service){
        rejectedExecutionHandler = (r,tpe) -> {
            System.out.println("rejected");
        };

         arrayBlockingQueue = new ArrayBlockingQueue( 1000 );
         threadPoolExecutor = new ThreadPoolExecutor(3, 100, 10000, TimeUnit.MILLISECONDS,
                arrayBlockingQueue, rejectedExecutionHandler );
         this.service = service;
    }
    public void produce( int i ){
        Callable<Integer> runnable = new Callable() {
            @Override
            public Integer call() {
                int res;
                System.out.println( Thread.currentThread().threadId() + " want twice of " + i +
                        " blocking queue size" + arrayBlockingQueue.size());
                maxParallelThreads = Math.max( threadPoolExecutor.getActiveCount(), maxParallelThreads );
                System.out.println( "Producer tpe active thread count: " + threadPoolExecutor.getActiveCount() + " maxThreads "+ maxParallelThreads);
                res = service.process( i );
                System.out.println( "producer " + Thread.currentThread().threadId() + " got twice of " + i + " as " + res);
                return res;
            }

        };
        Future<Integer> future;
        threadPoolExecutor.submit( runnable );
        /*
        int res;
        try {
            res = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println( "producer " + Thread.currentThread().threadId() + " got twice of " + i + " as " + res);
         */
    }

    public void produceBulk( int x ){
        for (int i = 0; i < x; i++) {
            produce( i );
        }
    }

}

class Service {
    ArrayBlockingQueue arrayBlockingQueue;
    ThreadPoolExecutor threadPoolExecutor;
    int maxParallelThreads = 0;
    Service(){
        arrayBlockingQueue = new ArrayBlockingQueue( 1000 );
        threadPoolExecutor = new ThreadPoolExecutor(10, 100, 10000, TimeUnit.MILLISECONDS,
                arrayBlockingQueue );
    }
    public Integer process( int x ){
        Callable<Integer> runnable = new Callable() {
            @Override
            public Integer call() {
                try {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
                    randomNum = 0;
                    System.out.println( "Service " + Thread.currentThread().threadId() + "sleeping for " + randomNum + " calculating twice of " + x + " blocking queue size" + arrayBlockingQueue.size());
                    maxParallelThreads = Math.max( threadPoolExecutor.getActiveCount(), maxParallelThreads );
                    System.out.println( "Server tpe active thread count: " + threadPoolExecutor.getActiveCount() +" maxThreads "+ maxParallelThreads);
                    Thread.sleep( randomNum );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 2*x;
            }
        };
        Future<Integer> future = threadPoolExecutor.submit(runnable);
        int res;
        try {
            res = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

}


public class ThreadToThreadCommunication {

    public static void main(String[] args) {
        Service service = new Service();
        Producer producer = new Producer( service );
        producer.produceBulk( 1000_000_000 );
    }

}
