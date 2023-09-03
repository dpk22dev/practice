package rl;

import org.joda.time.DateTime;

public interface RateLimiterIface {
    public boolean process(String userId, RequestType requestType, DateTime dateTime);
}
