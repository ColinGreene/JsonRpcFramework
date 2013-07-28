package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.JsonRpcRequest;
import com.cgreene.jsonrpc.JsonRpcResponse;
import com.cgreene.jsonrpc.exception.JsonRpcInternalErrorException;
import com.cgreene.jsonrpc.exception.JsonRpcMethodNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcDispatcher {
    /**
     * Logger
     */
    private final static Logger LOGGER = Logger.getLogger(JsonRpcDispatcher.class.getName());

    /**
     * Map of request handlers as method/class pairs
     */
    private Map requestHandlers =  new HashMap<String, Object>();


    /**
     * Register a new JSON-RPC 2.0 request handler
     *
     * @param handler The request handler to register. Must not be null
     * @throws JsonRpcInternalErrorException On attempting to register a handler that duplicates an existing request name
     */
    public void register(final Object handler) throws JsonRpcInternalErrorException{
        LOGGER.log(Level.INFO,"#register(Object) - Entering");
        Class<?> clazz = handler.getClass();
        JsonRpcAnnotationScanner scanner = new JsonRpcAnnotationScanner(clazz);
        for(String methodName : scanner.getMethodsAsArray()){
            if(requestHandlers.containsKey(methodName)){
                throw new JsonRpcInternalErrorException("Cannot register a duplicate JSON-RPC 2.0 handler for method", methodName);
            }
            requestHandlers.put(methodName,handler);
        }
        LOGGER.log(Level.INFO,"#register(Object) - Exiting");
    }

    /**
     * Gets the handler for the specified method name
     *
     * @param methodName The method name that will be used as a key to look up registered handler
     * @return The registered request handler, null otherwise
     */
    public Object getRequestHandler(final String methodName){
        return requestHandlers.get(methodName);
    }

    /**
     * Process the request by invoking the function and returning the result
     *
     * @param jsonRpcRequest The JSON-RPC 2.0 Request object
     * @return The result as a JSON-RPC 2.0 Response object
     * @throws JsonRpcMethodNotFoundException If there is no register handler with method name in request
     */
    public JsonRpcResponse processRequest(final JsonRpcRequest jsonRpcRequest)throws JsonRpcMethodNotFoundException{
        LOGGER.log(Level.INFO,"#processRequest(JsonRpcRequest) - Entering");
        final String methodName = jsonRpcRequest.getMethod();
        final Object registeredHandler = getRequestHandler(methodName);
        if(registeredHandler == null){
            throw new JsonRpcMethodNotFoundException("No registered handler with that method name", methodName);
        }
        // TODO: JsonRpcMethodInvoker
        LOGGER.log(Level.INFO,"#processRequest(JsonRpcRequest) - Exiting");
        return null;
    }
}
