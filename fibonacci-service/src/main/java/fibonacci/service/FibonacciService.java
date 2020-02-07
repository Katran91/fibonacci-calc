package fibonacci.service;

import com.stratij.FibonacciReply;
import com.stratij.FibonacciRequest;
import com.stratij.FibonacciServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FibonacciService extends FibonacciServiceGrpc.FibonacciServiceImplBase {

    @Override
    public void calcFibonacci(FibonacciRequest request, StreamObserver<FibonacciReply> responseObserver) {
        int limit = request.getNumber();
        int previousNumber = 0;
        int currentNumber = 1;

        responseObserver.onNext(packResponse(0));

        if(limit != 0) {
            do {
                responseObserver.onNext(packResponse(currentNumber));

                int sum = previousNumber + currentNumber;
                previousNumber = currentNumber;
                currentNumber = sum;
            } while (currentNumber <= limit);
        }

        responseObserver.onCompleted();
    }

    private FibonacciReply packResponse(int number) {
        return FibonacciReply.newBuilder().setMessage(number).build();
    }
}
