package fibonacci.service;

import com.stratij.FibonacciReply;
import com.stratij.FibonacciRequest;
import com.stratij.FibonacciServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=test",
        "grpc.server.port=-1",
        "grpc.client.inProcess.address=in-process:test"
})
@SpringJUnitConfig(classes = { FibonacciServiceTestConfigurator.class })
@DirtiesContext
class FibonacciServiceTestInt {
    @GrpcClient("inProcess")
    private FibonacciServiceGrpc.FibonacciServiceBlockingStub fibonacciStub;

    @Test
    @DirtiesContext
    void calcFibonacci() {
        FibonacciRequest fibonacciRequest = FibonacciRequest.newBuilder().setNumber(3).build();

        Iterator<FibonacciReply> iterator = fibonacciStub.calcFibonacci(fibonacciRequest);
        List<Integer> results = new ArrayList<>();
        while (iterator.hasNext()){
            results.add(iterator.next().getMessage());
         }

        assertEquals(5, results.size());
        assertEquals(0,results.get(0).intValue());
        assertEquals(1,results.get(1).intValue());
        assertEquals(1,results.get(2).intValue());
        assertEquals(2,results.get(3).intValue());
        assertEquals(3,results.get(4).intValue());
    }
}