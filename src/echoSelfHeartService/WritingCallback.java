package echoSelfHeartService;

public interface WritingCallback {

    void onSuccess();
    void onFail(byte[] data, int offset, int len);

}
