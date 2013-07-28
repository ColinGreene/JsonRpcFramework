package com.cgreene.jsonrpc;

import com.cgreene.jsonrpc.exception.JsonRpcInvalidParamsException;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcRequest {
    /**
     * Method to call
     */
    @JsonProperty("method")
    private String method;

    /**
     * Parameters
     */
    @JsonProperty("params")
    private List<String> params;

    /**
     * ID
     */
    @JsonProperty("id")
    private Object id;

    /**
     * Construct a new JSON-RPC 2.0 request object
     *
     * @param method The name of the method to be called
     * @param id The id to be returned in the response
     * @throws JsonRpcInvalidParamsException If there was an issue setting the parameters
     */
    public JsonRpcRequest(final String method, final Object id) throws JsonRpcInvalidParamsException{
        setMethod(method);
        setId(id);
    }

    /**
     * Construct a new JSON-RPC 2.0 request object
     *
     * @param method The name of the method to be called
     * @param id The id to be returned in the response
     * @param params The parameters to pass the method
     * @throws JsonRpcInvalidParamsException If there was an issue setting the parameters
     */
    public JsonRpcRequest(final String method, final Object id, final List<String> params) throws JsonRpcInvalidParamsException{
        setMethod(method);
        setId(id);
        setParams(params);
    }

    /**
     * The method to be called
     *
     * @return The method name as a String
     */
    public String getMethod(){
        return method;
    }

    /**
     * The id to be returned in the response
     *
     * @return The Id as an Object
     */
    public Object getId(){
        return id;
    }

    /**
     * The parameters to be passed into the function
     *
     * @return The parameters as a List of Objects
     */
    public List<String> getParams(){
        return params;
    }

    /**
     * Set the request identifier
     *
     * @param method The method to call
     * @throws JsonRpcInvalidParamsException If the method name is null
     */
    private void setMethod(final String method)throws JsonRpcInvalidParamsException{
       if(method == null){
           throw new JsonRpcInvalidParamsException("The method name must not be null");
       }
       this.method = method;
    }

    /**
     * Set the request identifier
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
     * Sets the request parameters
     *
     * @param params The request parameters
     */
    private void setParams(final List<String> params){
        if(params == null){
            this.params = new ArrayList<String>(){};
            return;
        }
        this.params = params;
    }
}
