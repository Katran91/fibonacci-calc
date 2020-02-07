package com.stratij.proxy.controller;

import com.stratij.fibonacci.grpc.FibonacciReply;
import com.stratij.proxy.service.FibonacciService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/api")
@Validated
public class ProxyController {
    private Logger logger = LoggerFactory.getLogger(ProxyController.class);

    @Autowired
    private FibonacciService fibonacciService;

    @Autowired
    @Qualifier("fixedThreadPool")
    private ExecutorService executor;

    @GetMapping("/fibonacci/{fibonacciNumber}")
    public ResponseEntity<ResponseBodyEmitter> fibonacci(
            @PathVariable(value = "fibonacciNumber") @PositiveOrZero Integer number) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        executor.execute(() -> {
            Iterator<FibonacciReply> iterator = fibonacciService.calcFibonacci(number);
            logger.info("Proceed fibonacci call with number " + number);

            try {
                while (iterator.hasNext()) {
                    emitter.send(iterator.next().getMessage());
                }

                emitter.complete();
            } catch (IOException e) {
                logger.error(e.getMessage());
                emitter.completeWithError(e);
            }
        });

        return new ResponseEntity(emitter, HttpStatus.OK);
    }
}
