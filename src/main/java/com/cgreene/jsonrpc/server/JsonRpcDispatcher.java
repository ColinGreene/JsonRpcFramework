package com.cgreene.jsonrpc.server;

import java.util.HashMap;
import java.util.Map;
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
    private Map _requestHandlers =  new HashMap<String, Object>();

    /**
     *  Register a new JSON-RPC 2.0 request handler
     */
    public void register(final Object handler){

    }
}
