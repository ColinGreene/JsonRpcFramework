package com.cgreene.jsonrpc.server;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamplePaymentRequestDTO {
    @JsonProperty
    public String AccountNumber;

    @JsonProperty
    public ExampleCreditCardDTO CreditCardDetails;

    @JsonProperty
    public BigDecimal Amount;
}
