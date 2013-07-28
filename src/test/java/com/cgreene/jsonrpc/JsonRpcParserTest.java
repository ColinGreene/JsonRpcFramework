package com.cgreene.jsonrpc;

import com.cgreene.jsonrpc.exception.JsonRpcException;
import com.cgreene.jsonrpc.exception.JsonRpcInvalidParamsException;
import com.cgreene.jsonrpc.exception.JsonRpcParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonRpcParserTest {

    @Test
    public void testParserCanReadID(){
        String jsonString = "{\"id\":\"001\", \"method\":\"getAccountStatus\", \"params\":{\"arg1\":{\"param1\":\"v1\",\"param2\":\"v2\"}, \"arg2\":{\"param3\":\"v3\"}}, \"jsonrpc\":\"2.0\"}";
        JsonRpcParser parser = new JsonRpcParser();
        JsonRpcRequest request = null;
        try {
            request = parser.parseJsonRpcRequest(jsonString);
        } catch (JsonRpcException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        assertEquals("The id can be pulled from the json node", request.getId(), "001");
    }

    @Test
    public void testCanReadMethod(){
        String jsonString = "{\"id\":\"001\", \"method\":\"getAccountStatus\", \"params\":{\"arg1\":{\"param1\":\"v1\",\"param2\":\"v2\"}, \"arg2\":{\"param3\":\"v3\"}}, \"jsonrpc\":\"2.0\"}";
        JsonRpcParser parser = new JsonRpcParser();
        JsonRpcRequest request = null;
        try {
            request = parser.parseJsonRpcRequest(jsonString);
        } catch (JsonRpcException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        assertEquals("The id can be pulled from the json node", request.getMethod(), "getAccountStatus");
    }

    @Test
    public void testCanReadParams(){
        String jsonString = "{\"id\":\"001\", \"method\":\"getAccountStatus\", \"params\":{\"arg1\":{\"param1\":\"v1\",\"param2\":\"v2\"}, \"arg2\":{\"param3\":\"v3\"}}, \"jsonrpc\":\"2.0\"}";
        JsonRpcParser parser = new JsonRpcParser();
        JsonRpcRequest request = null;
        try {
            request = parser.parseJsonRpcRequest(jsonString);
        } catch (JsonRpcException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String param1 = "{\"param1\":\"v1\",\"param2\":\"v2\"}";
        String param2 =  "{\"param3\":\"v3\"}";
        List<String> params = new ArrayList<String>();
        params.add(param1);
        params.add(param2);
        assertEquals("The id can be pulled from the json node", request.getParams(), params);
    }
}
