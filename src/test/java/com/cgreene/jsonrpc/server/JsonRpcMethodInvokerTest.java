package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.JsonRpcResponse;
import com.cgreene.jsonrpc.exception.JsonRpcException;
import com.cgreene.jsonrpc.exception.JsonRpcInternalErrorException;
import org.junit.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonRpcMethodInvokerTest {
    @Test
    public void testCanInvokeSimpleRequest(){
        JsonRpcDispatcher dispatcher = new JsonRpcDispatcher();
        ExampleHandlerOne handler = new ExampleHandlerOne();
        // register the handler
        try {
            dispatcher.register(handler);
        } catch (JsonRpcInternalErrorException e) {
            e.printStackTrace();
        }
        JsonRpcMethodInvoker invoker = new JsonRpcMethodInvoker();
        JsonRpcAnnotationScanner scanner = new JsonRpcAnnotationScanner(handler.getClass());
        Method method = scanner.getMethod("toUpperCase");
        JsonRpcResponse response = null;
        try {
            response = invoker.invokeRequest(handler, method,"001",new Object[]{"test"});
        } catch (JsonRpcException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String respAsString = (String)response.getResult();
        assertEquals("Response gives the correct result", respAsString, "TEST");
    }

    @Test
    public void thisCanInvokeCustomObjectRequest(){
        JsonRpcDispatcher dispatcher = new JsonRpcDispatcher();
        ExampleHandlerTwo handler = new ExampleHandlerTwo();
        // register the handler
        try {
            dispatcher.register(handler);
        } catch (JsonRpcInternalErrorException e) {
            e.printStackTrace();
        }
        JsonRpcMethodInvoker invoker = new JsonRpcMethodInvoker();
        JsonRpcAnnotationScanner scanner = new JsonRpcAnnotationScanner(handler.getClass());
        Method method = scanner.getMethod("makePayment");

        JsonRpcResponse response = null;
        ExamplePaymentRequestDTO requestDTO= createExamplePaymentRequest();
        try {
            response = invoker.invokeRequest(handler, method,"001", requestDTO);
        } catch (JsonRpcException e) {
            e.printStackTrace();
        }
        ExamplePaymentResponseDTO responseDTO = (ExamplePaymentResponseDTO)response.getResult();
        assertEquals("Response gives the correct result", responseDTO.SuccessfulPayment, true);
    }

    private ExamplePaymentRequestDTO createExamplePaymentRequest(){
        ExampleCreditCardDTO ccDetails = new ExampleCreditCardDTO();
        ccDetails.CardHolder = "Ted Bundy";
        ccDetails.CardNumber = 123456789;
        ccDetails.ExpirationDate = "01/01/2013";
        ccDetails.SecurityNumber = 987;

        ExamplePaymentRequestDTO requestDTO = new ExamplePaymentRequestDTO();
        requestDTO.AccountNumber = "987654321";
        requestDTO.Amount = new BigDecimal(999.00);
        requestDTO.CreditCardDetails = ccDetails;
        return requestDTO;

    }
}
