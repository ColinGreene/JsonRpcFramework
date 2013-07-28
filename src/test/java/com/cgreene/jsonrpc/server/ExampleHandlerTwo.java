package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.annotation.JsonRpcMethod;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExampleHandlerTwo {
    @JsonRpcMethod
    public ExamplePaymentResponseDTO makePayment(ExamplePaymentRequestDTO requestDTO){
        String timestamp = Long.toString(new Date().getTime());
        boolean successful = false;
        if(requestDTO.Amount!=null && requestDTO.AccountNumber!=null){
            successful = true;
        }
        ExamplePaymentResponseDTO responseDTO = new ExamplePaymentResponseDTO();
        responseDTO.SuccessfulPayment = successful;
        responseDTO.TransactionNumber = timestamp;
        return responseDTO;
    }
}
