package com.cgreene.jsonrpc.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class JsonRpcException extends Exception{
    /**
     * Enum specifying the type of error that occurred. They have standardised error numbers.
     */
    @JsonProperty("code")
    private JsonRpcErrorCode code;

    /**
     * Optional error data
     */
    @JsonProperty("data")
    private Object data;

    /**
     * Message from java.lang.Exception
     */
    @JsonProperty
    private String message;

    /**
     * Init code and data and specify the message
     *
     * @param msg Message to be sent to the super class
     * @param code The JSON-RPC 2.0 standard code identifying the issue
     * @param data Extra data that helps give contents to the issue
     */
    protected JsonRpcException(final String msg, final JsonRpcErrorCode code, final Object data){
        super(msg);
        this.code = code;
        this.data = data;
    }

    /**
     * Init code and data and specify the message and the cause
     *
     * @param msg Message to be sent to the super class
     * @param code The JSON-RPC 2.0 standard code identifying the issue
     * @param data Extra data that helps give contents to the issue
     * @param cause The exception that caused this exception to be created
     */
    protected JsonRpcException(final String msg, final JsonRpcErrorCode code, final Object data, final Throwable cause){
        super(msg, cause);
        this.code = code;
        this.data = data;
    }

    /**
     * Returns error code as enum JsonRpcErrorCode
     *
     * @return Error code
     */
    public JsonRpcErrorCode getCode(){
        return code;
    }

    /**
     * Gets the extra data that has been passed
     *
     * @return Extra data as Object
     */
    public Object getData(){
        return data;
    }

    /**
     * Gets the Message from java.lang.Exception
     *
     * @return Exception as String
     */
    public String getMessage(){
        return super.getMessage();
    }
}
