package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.JsonRpcResponse;
import com.cgreene.jsonrpc.exception.JsonRpcException;
import com.cgreene.jsonrpc.exception.JsonRpcInternalErrorException;
import com.cgreene.jsonrpc.exception.JsonRpcInvalidParamsException;
import com.cgreene.jsonrpc.exception.JsonRpcInvalidRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcMethodInvoker {
    /**
     * Logger
     */
    private final static Logger LOGGER = Logger.getLogger(JsonRpcMethodInvoker.class.getName());

    /**
     * Builds a list of concrete parameters
     *
     * @param jsonParams List of parameters as String from the payload
     * @param method The method object
     * @return The parameters that have been initialised by Jackson
     * @throws JsonRpcInvalidParamsException If the wrong number of parameters for the method are passed in
     * @throws JsonRpcInternalErrorException If Jackson has an issue reading the json
     */
    public Object[] buildParameters(List<String> jsonParams, Method method) throws JsonRpcInvalidParamsException, JsonRpcInternalErrorException{
        LOGGER.log(Level.INFO,"#buildParameters(List,Method) - Entering");
        ObjectMapper mapper = new ObjectMapper();
        Object[] methodArgs = new Object[jsonParams.size()];
        Class<?>[] paramsTypes = method.getParameterTypes();
        if(jsonParams.size() < paramsTypes.length || jsonParams.size() > paramsTypes.length){
            throw new JsonRpcInvalidParamsException("Expected " + paramsTypes.length + " argument(s), got " + jsonParams.size());
        }
        int count = 0;
        for(Class<?> paramType : paramsTypes){
            String jsonParam = jsonParams.get(count);
            try {
                methodArgs[count] = mapper.readValue(jsonParam, paramType);
            } catch (IOException e) {
                throw new JsonRpcInternalErrorException("An issue occurred while Jackson was attempting to read the json", e);
            }
            count++;
        }
        LOGGER.log(Level.INFO,"#buildParameters(List,Method) - Exiting");
        return methodArgs;
    }
    /**
     * Invokes the method on the handler
     *
     * @param handler The handler object that has the method to be invoked
     * @param method The method to be called on the handler
     * @param id The request id, passed back in the response
     * @param params The parameters for the function call
     * @return A JSON-RPC 2.0 Response object
     * @throws JsonRpcException If there was an issue invoking the function
     */
    public JsonRpcResponse invokeRequest(final Object handler, final Method method, final Object id, Object... params) throws JsonRpcException {
        LOGGER.log(Level.INFO,"#invokeRequest(Object,Method,Object,Object...) - Entering");
        Object response = null;
        try{
            response = method.invoke(handler, params);
        }catch (InvocationTargetException e) {
            throw new JsonRpcInternalErrorException("Underlying method has thrown an exception", method.getName(), e);
        }catch (IllegalAccessException e) {
            throw new JsonRpcInvalidRequestException("Cannot access method", method.getName(), e);
        }catch (Exception e){
            throw new JsonRpcInternalErrorException("Exception occurred while processing request", e);
        }
        JsonRpcResponse jsonRpcResponse = new JsonRpcResponse(id, response);
        LOGGER.log(Level.INFO,"#invokeRequest(Object,Method,Object,Object...) - Exiting");
        return jsonRpcResponse;
    }
}
