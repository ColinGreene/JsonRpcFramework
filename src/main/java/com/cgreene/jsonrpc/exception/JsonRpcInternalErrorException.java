package com.cgreene.jsonrpc.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcInternalErrorException extends JsonRpcException{

    /**
     * Creates a new JSON-RPC 2.0 Internal Error Exception. The optional data is omitted.
     *
     * @param msg The error message
     */
    public JsonRpcInternalErrorException(final String msg){
        this(msg, null, null);
    }

    /**
     * Creates a new JSON-RPC 2.0 Internal Error Exception. The optional data is omitted.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     */
    public JsonRpcInternalErrorException(final String msg, final Throwable cause){
        this(msg, null, cause);
    }

    /**
     * Creates a new JSON-RPC 2.0 Internal Error Exception.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     * @param data Optional error data, must map to a valid json type
     */
    public JsonRpcInternalErrorException(final String msg, final Object data, final Throwable cause){
        super(msg, JsonRpcErrorCode.INTERNAL_ERROR, data, cause);
    }
}
