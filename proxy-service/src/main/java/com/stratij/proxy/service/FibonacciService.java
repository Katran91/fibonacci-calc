package com.stratij.proxy.service;

import com.stratij.fibonacci.grpc.FibonacciReply;
import com.stratij.fibonacci.grpc.FibonacciRequest;
import com.stratij.fibonacci.grpc.FibonacciServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class FibonacciService {
    @GrpcClient("fibonacci-service")
    private FibonacciServiceGrpc.FibonacciServiceBlockingStub fibonacciStub;

    public Iterator<FibonacciReply> calcFibonacci(Integer number) {
        FibonacciRequest fibonacciRequest = FibonacciRequest.newBuilder().setNumber(number).build();

        return fibonacciStub.calcFibonacci(fibonacciRequest);
    }
}
