package com.cgreene.jsonrpc.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcParseException extends JsonRpcException{
    /**
     * The parse exception error cause
     */
    @JsonProperty("parseError")
    private JsonRpcParseCause parseError;

    /**
     * String that cannot be parsed
     */
    @JsonProperty("unparsableString")
    private String unparsableString;

    /**
     * Creates a new JSON-RPC 2.0 Parse Exception. The optional data is omitted.
     *
     * @param msg The error message
     */
    public JsonRpcParseException(final String msg){
        this(msg, null, null);
    }

    /**
     * Creates a new JSON-RPC 2.0 Parse Exception. The optional data is omitted.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     */
    public JsonRpcParseException(final String msg, final Throwable cause){
        this(msg, null, cause);
    }

    /**
     * Creates a new JSON-RPC 2.0 Parse Exception.
     *
     * @param msg The error message
     * @param cause The exception that was caught
     * @param data Optional error data, must map to a valid json type
     */
    public JsonRpcParseException(final String msg, final Object data, final Throwable cause){
        super(msg, JsonRpcErrorCode.PARSE_ERROR, data, cause);
        this.parseError = JsonRpcParseCause.PROTOCOL;
    }

    /**
     * Creates a new JSON-RPC 2.0 Parse Exception that can accept the problematic string
     *
     * @param msg The exception message
     * @param data Optional error data, must map to a valid json type
     * @param cause The exception that was caught
     * @param unparsableString The unparsable string
     */
    public JsonRpcParseException(final String msg, final Object data, final Throwable cause, String unparsableString){
        super(msg, JsonRpcErrorCode.PARSE_ERROR, data, cause);
        this.unparsableString = unparsableString;
    }

    /**
     * Creates a new JSON-RPC 2.0 Parse Exception that can accept the problematic string and parse error.
     *
     * @param msg The exception message
     * @param data Optional error data, must map to a valid json type
     * @param unparsableString The unparsable string
     * @param parseError The reason why parsing failed
     */
    public JsonRpcParseException(final String msg, final Object data, String unparsableString, final JsonRpcParseCause parseError){
        super(msg, JsonRpcErrorCode.PARSE_ERROR, data, null);
        this.parseError = parseError;
        this.unparsableString = unparsableString;
    }

    /**
     * Creates a new JSON-RPC 2.0 Parse Exception that can accept the problematic string and parse error.
     *
     * @param msg The exception message
     * @param data Optional error data, must map to a valid json type
     * @param unparsableString The unparsable string
     * @param parseError The reason why parsing failed
     * @param cause The exception that was caught
     */
    public JsonRpcParseException(final String msg, final Object data, String unparsableString, final JsonRpcParseCause parseError, final Throwable cause){
        super(msg, JsonRpcErrorCode.PARSE_ERROR, data, cause);
        this.parseError = parseError;
        this.unparsableString = unparsableString;
    }

    /**
     * Reason for parsing failed defined as enum
     *
     * @return The reason parsing failed
     */
    public JsonRpcParseCause getParseError(){
        return parseError;
    }

    /**
     * The string passed in to be parsed
     *
     * @return The string that was passed in to be parsed
     */
    public String getUnparsableString(){
        return unparsableString;
    }
}
