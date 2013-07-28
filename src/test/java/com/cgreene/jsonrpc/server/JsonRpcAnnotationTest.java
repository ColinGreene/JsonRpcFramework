package com.cgreene.jsonrpc.server;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonRpcAnnotationTest {
    @Test
    public void thisScanClassWithoutException(){
        Exception exception = null;
        Class<?> clazz = ExampleAnnotationScannerClass.class;
        try{
            JsonRpcAnnotationScanner scanner = new JsonRpcAnnotationScanner(clazz);
        }catch(Exception e){
            exception = e;
        }
        assertEquals("No exception was thrown from scanner the class",exception, null);
    }

    @Test
    public void thisCanCorrectlyScanClasses(){
        Exception exception = null;
        Class<?> clazz = ExampleAnnotationScannerClass.class;
        JsonRpcAnnotationScanner scanner = new JsonRpcAnnotationScanner(clazz);
        String[] methodNames = new String[]{"toUpperCase","toLowerCase"};
        Assert.assertArrayEquals(methodNames, scanner.getMethodsAsArray());
    }

    @Test
    public void thisCanPullMethodsFromScanner(){
        Exception exception = null;
        Class<?> clazz = ExampleAnnotationScannerClass.class;
        JsonRpcAnnotationScanner scanner = new JsonRpcAnnotationScanner(clazz);
        Method method = scanner.getMethod("toUpperCase");
        assertEquals("You can get method from scanner", method.getName(), "toUpperCase");
    }
}
