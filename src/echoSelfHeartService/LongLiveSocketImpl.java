package echoSelfHeartService;

import java.net.Socket;

public class LongLiveSocketImpl implements LongLiveSocket {

    private static final String TAG = "LongLiveSocket";

    private static final long RETRY_INTERVAL_MILLIS = 3 * 1000;
    private static final long HEART_BEAT_INTERVAL_MILLIS = 5 * 1000;
    private static final long HEART_BEAT_TIMEOUT_MILLIS = 2 * 1000;


    private final Object mLock = new Object();
    private Socket mSocket;  // guarded by mLock
    private boolean mClosed; // guarded by mLock

    private final String mHost = "localhost";
    private final int mPort = 9877;
    private final DataCallback mDataCallback = new DataCallbackImpl();
    private final ErrorCallback mErrorCallback = new ErrorCallbackImpl();


    public LongLiveSocketImpl(String host, int port,
                          DataCallback dataCallback, ErrorCallback errorCallback) {



    }

    private final Runnable mHeartBeatTask = new Runnable() {
        private byte[] mHeartBeat = new byte[0];

        @Override
        public void run() {
            // 我们使用长度为 0 的数据作为 heart beat
            write(mHeartBeat, new WritingCallbackImpl() {
                @Override
                public void onSuccess() {
                    // 每隔 HEART_BEAT_INTERVAL_MILLIS 发送一次

                }

                @Override
                public void onFail(byte[] data, int offset, int len) {
                    // nop
                    // write() 方法会处理失败
                }
            });
        }
    };

    @Override
    public void write(byte[] data, WritingCallback callback) {

    }

    @Override
    public void write(byte[] data, int offset, int len, WritingCallback callback) {

    }

    @Override
    public void close() {

    }
}
