#Fibonacci service
--------------------------------------
###Overview

This application consists from three parts:
*  **fibonacci-service** - a spring-boot application which calculates fibonacci number up to specified value. 
   For communication it uses gRPC technology 
*  **proxy-service** - a spring-boot application which provides http endpoint (/api/fibonacci/{number}) 
   and makes a call to **fibonacci-service** for calculation numbers. Response is asynchronous with ResponseBodyEmitter
*  **grpc-contract** - module which contains protobuf settings file to provide contract between  **proxy-service** 
   and **fibonacci-service**
   
###Build and start application
To build both services simply, run build task on parent project 
```groovy
./gradlew clean build.
```
 
After that run applications: 
* fibonacci-service with command:
```bash
java -jar ./fibonacci-service/build/libs/fibonacci-service-1.0.jar
```
 
* proxy-service with command:
```bash
 java -jar ./proxy-service/build/libs/proxy-service-1.0.jar
```
   
Now you can make http calls to proxy: 
http://localhost:8080/api/fibonacci/5

###Implementation details
For communication between services was used gRPC, because it is more simple to start and there are a lot integrations 
with Gradle and Spring. This task doesn't have requirements for performance, concurrency and so on. 
That's why I din't make deep research, but for real project I would choose communication technology more carefully.  

Also this projects depends on [grpc-spring-boot-starter](https://github.com/yidongnan/grpc-spring-boot-starter) and 
on [protobuf-gradle-plugin](https://github.com/google/protobuf-gradle-plugin).

**grpc-spring-boot-starter** provides integration with spring and simple configuration for gRPC communication. Also it's
very useful for testing. There is configuration for "inProcess service" which decrease time and memory consumption 
for integration test.
```spel
grpc.server.inProcessName=test
grpc.server.port=-1
grpc.client.inProcess.address=in-process:test
```

**protobuf-gradle-plugin** was used to simplify Protobuf compilation and to provide compile time dependency for gRPC client 
and server.

Gradle was used because its provides better way to handle multiprojects builds, 
has an integration with protobuf.