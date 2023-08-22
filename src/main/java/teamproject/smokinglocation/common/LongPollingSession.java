package teamproject.smokinglocation.common;


import org.springframework.web.context.request.async.DeferredResult;

public class LongPollingSession {

    private final Long dossierId;
    private final DeferredResult<String> deferredResult;
    
    public LongPollingSession(final Long dossierId, final DeferredResult<String> deferredResult) {
        this.dossierId = dossierId;
        this.deferredResult = deferredResult;
    }
    
    public Long getDossierId() {
        return dossierId;
    }

    public DeferredResult<String> getDeferredResult() {
        return deferredResult;
    }
}
