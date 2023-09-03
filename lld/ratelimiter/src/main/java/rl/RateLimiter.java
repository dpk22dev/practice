package rl;

import org.joda.time.DateTime;

public class RateLimiter implements RateLimiterIface{
    private final RemoteService remoteService;
    public RateLimiter(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    @Override
    public String process(String userId, RequestType requestType, DateTime dateTime) {
        switch ( requestType ){
            case TOKEN_BUCKET:

        }
        return remoteService.generateResponse();

    }
}
