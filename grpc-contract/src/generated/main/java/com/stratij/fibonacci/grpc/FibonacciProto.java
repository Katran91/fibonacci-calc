// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fibonacci.proto

package com.stratij.fibonacci.grpc;

public final class FibonacciProto {
  private FibonacciProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stratij_fibonacci_grpc_FibonacciRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stratij_fibonacci_grpc_FibonacciRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stratij_fibonacci_grpc_FibonacciReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stratij_fibonacci_grpc_FibonacciReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017fibonacci.proto\022\032com.stratij.fibonacci" +
      ".grpc\"\"\n\020FibonacciRequest\022\016\n\006number\030\001 \001(" +
      "\005\"!\n\016FibonacciReply\022\017\n\007message\030\001 \001(\0052\201\001\n" +
      "\020FibonacciService\022m\n\rCalcFibonacci\022,.com" +
      ".stratij.fibonacci.grpc.FibonacciRequest" +
      "\032*.com.stratij.fibonacci.grpc.FibonacciR" +
      "eply\"\0000\001B.\n\032com.stratij.fibonacci.grpcB\016" +
      "FibonacciProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_stratij_fibonacci_grpc_FibonacciRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stratij_fibonacci_grpc_FibonacciRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stratij_fibonacci_grpc_FibonacciRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_com_stratij_fibonacci_grpc_FibonacciReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_stratij_fibonacci_grpc_FibonacciReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stratij_fibonacci_grpc_FibonacciReply_descriptor,
        new java.lang.String[] { "Message", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
