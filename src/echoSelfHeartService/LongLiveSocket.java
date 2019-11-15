package echoSelfHeartService;

public interface LongLiveSocket {

    public void write(byte[] data, WritingCallback callback);

    public void write(byte[] data, int offset, int len, WritingCallback callback);

    public void close();

}
