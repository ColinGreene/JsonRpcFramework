package com.cgreene.jsonrpc.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public enum JsonRpcErrorCode {

    PARSE_ERROR(-32700),
    INVALID_REQUEST(-32600),
    METHOD_NOT_FOUND(-32601),
    INVALID_PARAMS(-32602),
    INTERNAL_ERROR(-32603);

    private int _code;

    private JsonRpcErrorCode(int errorCode){
        _code = errorCode;
    }

    public int getCode(){
        return _code;
    }
}
