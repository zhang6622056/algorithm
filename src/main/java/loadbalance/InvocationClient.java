package loadbalance;

public class InvocationClient {


    private String requestId;

    public InvocationClient(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
