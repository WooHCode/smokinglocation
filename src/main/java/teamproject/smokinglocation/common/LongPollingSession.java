package teamproject.smokinglocation.common;


import org.springframework.web.context.request.async.DeferredResult;

public class LongPollingSession {

    private final long userId;
    private final DeferredResult<String> deferredResult;
    
    public LongPollingSession(final long userId, final DeferredResult<String> deferredResult) {
        this.userId = userId;
        this.deferredResult = deferredResult;
    }
    
    public long getUserId() {
        return userId;
    }

    public DeferredResult<String> getDeferredResult() {
        return deferredResult;
    }
}
