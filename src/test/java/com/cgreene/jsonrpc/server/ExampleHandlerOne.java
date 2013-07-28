package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.annotation.JsonRpcMethod;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 7:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExampleHandlerOne {

    @JsonRpcMethod
    public String toUpperCase(String s){
        return s.toUpperCase();
    }
}
