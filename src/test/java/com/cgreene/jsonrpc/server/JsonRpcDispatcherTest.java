package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.exception.JsonRpcInternalErrorException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonRpcDispatcherTest {
    @Test
    public void thisCanRegisterHandler(){
        ExampleHandlerOne handler = new ExampleHandlerOne();
        JsonRpcDispatcher dispatcher = new JsonRpcDispatcher();
        try {
            dispatcher.register(new ExampleHandlerOne());
        } catch (JsonRpcInternalErrorException e) {
            e.printStackTrace();
        }
        // check that the handler is indeed registered
        assertEquals("Dispatcher has registered the class",dispatcher.getRequestHandler("toUpperCase").getClass(), handler.getClass());
    }

}
