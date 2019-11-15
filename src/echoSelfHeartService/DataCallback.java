package echoSelfHeartService;

public interface DataCallback {

    void onData(byte[] data, int offset, int len);

}
