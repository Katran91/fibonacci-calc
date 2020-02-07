package com.stratij.proxy.controller;

import com.stratij.FibonacciReply;
import com.stratij.FibonacciRequest;
import com.stratij.FibonacciServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.PreDestroy;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
public class ProxyController {
    Logger logger = LoggerFactory.getLogger(ProxyController.class);

    @GrpcClient("fibonacci-service")
    private FibonacciServiceGrpc.FibonacciServiceBlockingStub fibonacciStub;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @GetMapping("/fibonacci")
    public ResponseEntity<ResponseBodyEmitter> fibonacci(
            @RequestParam(value = "fibonacciNumber", required = true) @Min(0) Integer number){
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        FibonacciRequest fibonacciRequest = FibonacciRequest.newBuilder().setNumber(number).build();

        executor.execute(() -> {
            Iterator<FibonacciReply> iterator = fibonacciStub.calcFibonacci(fibonacciRequest);
            logger.info("Proceed fabonacci call with number " + number);

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

    @PreDestroy
    public void destroy() {
        executor.shutdown();
    }
}
