package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.annotation.JsonRpcMethod;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExampleAnnotationScannerClass {

    @JsonRpcMethod
    public String toUpperCase(String s){
        return s.toUpperCase();
    }

    @JsonRpcMethod
    public String toLowerCase(String s){
        return s.toLowerCase();
    }

    @JsonRpcMethod
    private String privateMethod(String s){
        return "privateMethod";
    }
}
