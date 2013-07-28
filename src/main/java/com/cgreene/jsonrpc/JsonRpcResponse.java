package com.cgreene.jsonrpc;

import com.cgreene.jsonrpc.exception.JsonRpcException;
import com.cgreene.jsonrpc.exception.JsonRpcInvalidParamsException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcResponse {
    /**
     * The result
     */
    @JsonProperty("result")
    private Object result;

    /**
     * The error
     */
    @JsonProperty("error")
    private JsonRpcException error;

    /**
     * The id
     */
    @JsonProperty("id")
    private Object id;

    /**
     * The version
     */
    @JsonProperty("jsonrpc")
    public String jsonrpc = "2.0";

    /**
     * Construct a new JSON-RPC 2.0 Response object
     *
     * @param id The request identifier echoed back to the caller. May not be null
     * @throws JsonRpcInvalidParamsException If there was an issue setting the parameters
     */
    public JsonRpcResponse(final Object id) throws JsonRpcInvalidParamsException{
        setResult(null);
        setId(id);
    }

    /**
     * Construct a new JSON-RPC 2.0 Response object
     *
     * @param id The request identifier echoed back to the caller. May not be null
     * @param result The result that will be sent back to caller
     * @throws JsonRpcInvalidParamsException
     */
    public JsonRpcResponse(final Object id, final Object result) throws JsonRpcInvalidParamsException{
        setResult(result);
        setId(id);
    }

    /**
     * Construct a new JSON-RPC 2.0 Response object
     *
     * @param id The request identifier echoed back to the caller. May not be null
     * @param error The error that will be sent back to caller
     * @throws JsonRpcInvalidParamsException
     */
    public JsonRpcResponse(final Object id, final JsonRpcException error) throws JsonRpcInvalidParamsException{
        setError(error);
        setId(id);
    }


    /**
     * Set the result to the argument passed in and error to null
     *
     * @param result The value to set the result to
     */
    private void setResult(final Object result){
        this.result = result;
        this.error = null;
    }

    /**
     * Set the error to the argument passed in and the result to null
     *
     * @param error The value to set the error to
     */
    private void setError(final JsonRpcException error){
        this.error = error;
        this.result = null;
    }

    /**
     * Sets the request identifier echoed back to the caller
     *
     * @param id The request identifier echoed back to the caller. Must not be null
     * @throws JsonRpcInvalidParamsException If the id is null and if its anything other than a Boolean/Number/String
     */
    private void setId(final Object id) throws JsonRpcInvalidParamsException{
        if(id != null && !(id instanceof Boolean) && !(id instanceof Number) && !(id instanceof String)){
            throw new JsonRpcInvalidParamsException("The request identifier must be non null and map to Boolean/Number/String");
        }
        this.id = id;
    }

    /**
     * A convenience method to check if the response indicates success or failure of the request
     *
     * @return True if the request succeeded, false if there was an error
     */
    public boolean indicatesSuccess(){
        if(error == null){
            return true;
        }else{
            return false;
        }
    }
}
