package com.cgreene.jsonrpc.server;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamplePaymentResponseDTO {
    @JsonProperty
    public String TransactionNumber;

    @JsonProperty
    public boolean SuccessfulPayment;
}
