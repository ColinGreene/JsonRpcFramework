package com.cgreene.jsonrpc.server;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExampleCreditCardDTO {
    @JsonProperty
    public String CardHolder;

    // TODO: Find out what format Jackson needs java.util.Date to be in
    @JsonProperty
    public String ExpirationDate;

    @JsonProperty
    public long CardNumber;

    @JsonProperty
    public int SecurityNumber;

}
