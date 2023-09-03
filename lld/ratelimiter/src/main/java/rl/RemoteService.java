package rl;

import java.util.concurrent.ThreadLocalRandom;

public class RemoteService implements RemoteServiceIface{
    @Override
    public String generateResponse() {
        int sleep = ThreadLocalRandom.current().nextInt(1, 1000);
        try {
            System.out.println("going to sleep for sec" + sleep);
            Thread.sleep( sleep );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "ok";
    }
}
