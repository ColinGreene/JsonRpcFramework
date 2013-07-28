package com.cgreene.jsonrpc.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcInvalidParamsException extends JsonRpcException{

    /**
     * Creates a new JSON-RPC 2.0 Invalid Params Exception. The optional data is omitted.
     *
     * @param msg The error message
     */
    public JsonRpcInvalidParamsException(final String msg){
        this(msg, null, null);
    }

    /**
     * Creates a new JSON-RPC 2.0 Invalid Params Exception. The optional data is omitted.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     */
    public JsonRpcInvalidParamsException(final String msg, final Throwable cause){
        this(msg, null, cause);
    }

    /**
     * Creates a new JSON-RPC 2.0 Invalid Params Exception.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     * @param data Optional error data, must map to a valid json type
     */
    public JsonRpcInvalidParamsException(final String msg, final Object data, final Throwable cause){
        super(msg, JsonRpcErrorCode.INVALID_PARAMS, data, cause);
    }

}
