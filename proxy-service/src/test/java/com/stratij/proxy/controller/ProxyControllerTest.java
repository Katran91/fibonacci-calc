package com.stratij.proxy.controller;

import com.stratij.fibonacci.grpc.FibonacciReply;
import com.stratij.proxy.TestConfig;
import com.stratij.proxy.service.FibonacciService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProxyController.class)
@Import(TestConfig.class)
class ProxyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FibonacciService fibonacciService;

    @Test
    void fibonacci() throws Exception {
        List<FibonacciReply> fibonacciRow = Arrays.asList(
                convertToFibonacciReplay(0),
                convertToFibonacciReplay(1),
                convertToFibonacciReplay(1),
                convertToFibonacciReplay(2));

        Mockito.when(fibonacciService.calcFibonacci(any()))
                .thenReturn(fibonacciRow.iterator());

        MvcResult mvcResult = mockMvc.perform(get("/api/fibonacci/2"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andExpect(content().string("0112"));
    }

    @Test
    void fibonacciNegativeInput() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/fibonacci/-23"))
                .andExpect(request().asyncNotStarted())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input isn't valid due to validation error: " +
                        "fibonacci.number: must be greater than or equal to 0 ---> Number shoul be 0 or greater"))
                .andReturn();
    }

    @Test
    void fibonacciWrongInputType() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/fibonacci/wrongInput"))
                .andExpect(request().asyncNotStarted())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input isn't valid due to validation error: " +
                        "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; " +
                        "nested exception is java.lang.NumberFormatException: For input string: \"wrongInput\" " +
                        "---> Please maximum fibonacci number"))
                .andReturn();
    }

    private FibonacciReply convertToFibonacciReplay(int number) {
        return FibonacciReply.newBuilder().setMessage(number).build();
    }
}