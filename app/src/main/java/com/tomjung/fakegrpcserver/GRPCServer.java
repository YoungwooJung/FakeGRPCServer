package com.tomjung.fakegrpcserver;

import android.util.Log;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;

public class GRPCServer implements Runnable {
    private static final String TAG = GRPCServer.class.getSimpleName();

    private Server grpcServer;
    private int mPort = 8080;

    public void setPort(int port) {
        mPort = port;
    }

    private void start() throws IOException {
        grpcServer = NettyServerBuilder.forPort(mPort)
                .addService(new GreeterImpl())
                .build()
                .start();

        Log.i(TAG, "gRPC Server Started");
    }

    public void stop() {
        if (grpcServer != null) {
            Log.d(TAG, "gRPC Server Stopped");
            grpcServer.shutdown();
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello Local gRPC Server : " + request.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
