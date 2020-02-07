package com.stratij.fibonacci.service;

import com.stratij.fibonacci.grpc.FibonacciReply;
import com.stratij.fibonacci.grpc.FibonacciRequest;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class FibonacciServiceTest {
    private FibonacciService fibonacciService;

    @BeforeEach
    void setUp() {
        fibonacciService =  new FibonacciService();
    }

    @Test
    void calcFibonacciFor5() throws Exception {
        FibonacciRequest fibonacciRequest = FibonacciRequest.newBuilder().setNumber(5).build();
        StreamRecorder<FibonacciReply> streamRecorder = StreamRecorder.create();

        fibonacciService.calcFibonacci(fibonacciRequest, streamRecorder);
        assertNull(streamRecorder.getError());
        streamRecorder.awaitCompletion();

        List<FibonacciReply> results = streamRecorder.getValues();

        assertEquals(6, results.size());
        assertEquals(0,results.get(0).getMessage());
        assertEquals(1,results.get(1).getMessage());
        assertEquals(1,results.get(2).getMessage());
        assertEquals(2,results.get(3).getMessage());
        assertEquals(3,results.get(4).getMessage());
        assertEquals(5,results.get(5).getMessage());
    }

    @Test
    void calcFibonacciFor0() throws Exception {
        FibonacciRequest fibonacciRequest = FibonacciRequest.newBuilder().setNumber(0).build();
        StreamRecorder<FibonacciReply> streamRecorder = StreamRecorder.create();

        fibonacciService.calcFibonacci(fibonacciRequest, streamRecorder);
        assertNull(streamRecorder.getError());
        streamRecorder.awaitCompletion();

        List<FibonacciReply> results = streamRecorder.getValues();

        assertEquals(1, results.size());
        assertEquals(0,results.get(0).getMessage());
    }

    @Test
    void calcFibonacciUpTo4() throws Exception {
        FibonacciRequest fibonacciRequest = FibonacciRequest.newBuilder().setNumber(3).build();
        StreamRecorder<FibonacciReply> streamRecorder = StreamRecorder.create();

        fibonacciService.calcFibonacci(fibonacciRequest, streamRecorder);
        assertNull(streamRecorder.getError());
        streamRecorder.awaitCompletion();

        List<FibonacciReply> results = streamRecorder.getValues();

        assertEquals(5, results.size());
        assertEquals(0,results.get(0).getMessage());
        assertEquals(1,results.get(1).getMessage());
        assertEquals(1,results.get(2).getMessage());
        assertEquals(2,results.get(3).getMessage());
        assertEquals(3,results.get(4).getMessage());
    }
}