package com.tomjung.fakegrpcserver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;

public class ServerService extends Service {
    private static final String TAG = ServerService.class.getSimpleName();

    private HandlerThread handlerThread;
    private GRPCServer server;
    private Handler handler;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int port = intent.getIntExtra("port", 8080);
        Log.d(TAG, "port : " + port);

        handlerThread = new HandlerThread("gRPC Server");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper());
        server = new GRPCServer();
        server.setPort(port);
        handler.post(server);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
        server.stop();
        handler.removeMessages(0);
        handlerThread.quitSafely();
        handlerThread = null;
    }
}
