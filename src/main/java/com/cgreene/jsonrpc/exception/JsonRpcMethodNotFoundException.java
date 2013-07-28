package com.cgreene.jsonrpc.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcMethodNotFoundException extends JsonRpcException {
    /**
     * Creates a new JSON-RPC 2.0 Method Not Found Exception. The optional data is omitted.
     *
     * @param msg The error message
     */
    public JsonRpcMethodNotFoundException(final String msg, final Object data){
        this(msg, data, null);
    }

    /**
     * Creates a new JSON-RPC 2.0 Method Not Found Exception. The optional data is omitted.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     */
    public JsonRpcMethodNotFoundException(final String msg, final Throwable cause){
        this(msg, null, cause);
    }

    /**
     * Creates a new JSON-RPC 2.0 Method Not Found Exception.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     * @param data Optional error data, must map to a valid json type
     */
    public JsonRpcMethodNotFoundException(final String msg, final Object data, final Throwable cause){
        super(msg, JsonRpcErrorCode.METHOD_NOT_FOUND, data, cause);
    }
}
