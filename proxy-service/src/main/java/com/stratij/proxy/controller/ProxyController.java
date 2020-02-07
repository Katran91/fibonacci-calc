package com.stratij.proxy.controller;

import com.stratij.fibonacci.grpc.FibonacciReply;
import com.stratij.fibonacci.grpc.FibonacciRequest;
import com.stratij.fibonacci.grpc.FibonacciServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.PreDestroy;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
@Validated
public class ProxyController {
    Logger logger = LoggerFactory.getLogger(ProxyController.class);

    @GrpcClient("fibonacci-service")
    private FibonacciServiceGrpc.FibonacciServiceBlockingStub fibonacciStub;

    @Autowired
    @Qualifier("fixedThreadPool")
    private ExecutorService executor;

    @GetMapping("/fibonacci/{fibonacciNumber}")
    public ResponseEntity<ResponseBodyEmitter> fibonacci(
            @PathVariable(value = "fibonacciNumber")  @PositiveOrZero Integer number){
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        FibonacciRequest fibonacciRequest = FibonacciRequest.newBuilder().setNumber(number).build();

        executor.execute(() -> {
            Iterator<FibonacciReply> iterator = fibonacciStub.calcFibonacci(fibonacciRequest);
            logger.info("Proceed fibonacci call with number " + number);

            try {
                while (iterator.hasNext()) {
                    emitter.send(iterator.next().getMessage());
                }

                emitter.complete();
            } catch (IOException e){
                logger.error(e.getMessage());
                emitter.completeWithError(e);
            }
        });

        return new ResponseEntity(emitter, HttpStatus.OK);
    }
}
