package com.stratij.fibonacci.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0-pre2)",
    comments = "Source: fibonacci.proto")
public class FibonacciServiceGrpc {

  private FibonacciServiceGrpc() {}

  public static final String SERVICE_NAME = "com.stratij.fibonacci.grpc.FibonacciService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.stratij.fibonacci.grpc.FibonacciRequest,
      com.stratij.fibonacci.grpc.FibonacciReply> METHOD_CALC_FIBONACCI =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "com.stratij.fibonacci.grpc.FibonacciService", "CalcFibonacci"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.stratij.fibonacci.grpc.FibonacciRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.stratij.fibonacci.grpc.FibonacciReply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FibonacciServiceStub newStub(io.grpc.Channel channel) {
    return new FibonacciServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FibonacciServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FibonacciServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static FibonacciServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FibonacciServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FibonacciServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void calcFibonacci(com.stratij.fibonacci.grpc.FibonacciRequest request,
        io.grpc.stub.StreamObserver<com.stratij.fibonacci.grpc.FibonacciReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CALC_FIBONACCI, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CALC_FIBONACCI,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.stratij.fibonacci.grpc.FibonacciRequest,
                com.stratij.fibonacci.grpc.FibonacciReply>(
                  this, METHODID_CALC_FIBONACCI)))
          .build();
    }
  }

  /**
   */
  public static final class FibonacciServiceStub extends io.grpc.stub.AbstractStub<FibonacciServiceStub> {
    private FibonacciServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FibonacciServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FibonacciServiceStub(channel, callOptions);
    }

    /**
     */
    public void calcFibonacci(com.stratij.fibonacci.grpc.FibonacciRequest request,
        io.grpc.stub.StreamObserver<com.stratij.fibonacci.grpc.FibonacciReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_CALC_FIBONACCI, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FibonacciServiceBlockingStub extends io.grpc.stub.AbstractStub<FibonacciServiceBlockingStub> {
    private FibonacciServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FibonacciServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FibonacciServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.stratij.fibonacci.grpc.FibonacciReply> calcFibonacci(
        com.stratij.fibonacci.grpc.FibonacciRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_CALC_FIBONACCI, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FibonacciServiceFutureStub extends io.grpc.stub.AbstractStub<FibonacciServiceFutureStub> {
    private FibonacciServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FibonacciServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FibonacciServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CALC_FIBONACCI = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FibonacciServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(FibonacciServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALC_FIBONACCI:
          serviceImpl.calcFibonacci((com.stratij.fibonacci.grpc.FibonacciRequest) request,
              (io.grpc.stub.StreamObserver<com.stratij.fibonacci.grpc.FibonacciReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_CALC_FIBONACCI);
  }

}
