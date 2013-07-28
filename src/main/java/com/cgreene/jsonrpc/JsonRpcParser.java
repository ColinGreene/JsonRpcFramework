package com.cgreene.jsonrpc;

import com.cgreene.jsonrpc.exception.JsonRpcInvalidParamsException;
import com.cgreene.jsonrpc.exception.JsonRpcParseCause;
import com.cgreene.jsonrpc.exception.JsonRpcParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcParser {
    /**
     * Logger
     */
    private final static Logger LOGGER = Logger.getLogger(JsonRpcParser.class.getName());

    /**
     * Converts a json string to a Map<String/Object>
     *
     * @param jsonString The json to convert
     * @return The json as as a Map<String/Object>
     * @throws JsonRpcParseException If there is an exception thrown from Jackson
     */
    public JsonNode jsonToJsonNode(final String jsonString) throws JsonRpcParseException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode result = null;
        try {
            result = mapper.readValue(jsonString, JsonNode.class);
        } catch (IOException e) {
            throw new JsonRpcParseException("Exception occurred while trying to read data using Jackson", jsonString, e);
        }
        return result;
    }
    /**
     * Parse json string to JSON-RPC 2.0 Request object
     *
     * @param jsonString The string containing the json
     * @return JsonRpcRequest object
     */
    public JsonRpcRequest parseJsonRpcRequest(final String jsonString) throws JsonRpcParseException,JsonRpcInvalidParamsException {
        LOGGER.log(Level.INFO, "#parseJsonRpcRequest(String) - Entering");
        JsonNode jsonNode = jsonToJsonNode(jsonString);
        // extract version identifier
        if(!jsonNode.has("jsonrpc")){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: JSON payload must contain jsonrpc attribute.", JsonRpcParseCause.PROTOCOL, jsonString, null);
        }
        String version = jsonNode.get("jsonrpc").textValue();
        if(version == null){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: jsonrpc version missing.", JsonRpcParseCause.JSON, jsonString, null);
        }else if(version.length() == 0){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: jsonrpc version is an empty string.", JsonRpcParseCause.JSON, jsonString, null);
        }else if(!(version.equals("2.0"))){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: jsonrpc version must be \"2.0\".", JsonRpcParseCause.JSON, jsonString, null);
        }
        // extract method name
        if(!jsonNode.has("method")){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: JSON payload must contain method attribute", JsonRpcParseCause.PROTOCOL, jsonString, null);
        }
        String methodName = jsonNode.get("method").textValue();
        if(methodName == null){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: Method name missing", JsonRpcParseCause.JSON, jsonString, null);
        }else if(methodName.length() == 0){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: Method name is an empty string", JsonRpcParseCause.JSON, jsonString, null);
        }
        // extract id
        if(!jsonNode.has("id")){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: Missing identifier", JsonRpcParseCause.PROTOCOL, jsonString, null);
        }
        String id = jsonNode.get("id").textValue();
        if(id == null){
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: Identifier not a String", JsonRpcParseCause.JSON, jsonString, null);
        }
        // extract params
        JsonRpcRequest jsonRpcRequest = null;
        String paramString = jsonNode.get("params").toString();
        if(paramString == null){
            jsonRpcRequest = new JsonRpcRequest(methodName, id);
        }else if(!paramString.isEmpty()){
            jsonRpcRequest = new JsonRpcRequest(methodName, id, getParamsAsList(paramString));
        }else{
            throw new JsonRpcParseException("Invalid JSON-RPC 2.0: Method paramters have unexpected JSON type", JsonRpcParseCause.JSON, jsonString, null);
        }
        LOGGER.log(Level.INFO, "#parseJsonRpcRequest(String) - Exiting");
        return jsonRpcRequest;
    }

    /**
     * Taking the node params as a string, get all the sub nodes as string and add them to a List
     *
     * @param paramString Param node as string
     * @return Parameters as a List of String
     * @throws JsonRpcParseException If there is an issue converting string to json node
     */
    private List<String> getParamsAsList(String paramString) throws JsonRpcParseException {
        Iterator<JsonNode> it = jsonToJsonNode(paramString).iterator();
        ArrayList<String> params = new ArrayList<String>();
        while(it.hasNext()){
            JsonNode param = it.next();
            params.add(param.toString());
        }
        return params;
    }
}
