package com.cgreene.jsonrpc.server;

import com.cgreene.jsonrpc.annotation.JsonRpcMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: cgreene
 * Date: 7/28/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
final public class JsonRpcAnnotationScanner {
    /**
     * Logger
     */
    private final static Logger LOGGER = Logger.getLogger(JsonRpcAnnotationScanner.class.getName());

    /**
     * Map that holds the annotated methods
     */
    private Map<String, Method> _annotatedMethods;

    /**
     * Constructor that takes the type of the class to scan
     */
    public JsonRpcAnnotationScanner(Class<?> clazz){
       _annotatedMethods = findAnnotatedMethods(clazz);
    }

    /**
     * Find annotated methods on the class
     *
     * @param clazz The Class to scan to find what methods have been annotated
     * @return Map<String/Method> Contains the names of the functions that have been annotated
     */
    private Map<String, Method> findAnnotatedMethods(final Class<?> clazz){
       LOGGER.log(Level.INFO, "#findAnnotatedMethods(Class<?>) - Entering");
       Map<String, Method> annotatedMethods = new HashMap<String, Method>();
       for(Method method : clazz.getMethods()){
            if(hasAnnotation(method)){
                if(!annotatedMethods.containsKey(method.getName())){
                    annotatedMethods.put(method.getName(), method);
                }
            }
       }
       LOGGER.log(Level.INFO, "#findAnnotatedMethods(Class<?>) - Exiting");
       return annotatedMethods;
    }

    /**
     * Gets the Method from the map
     *
     * @param methodName The name of the method
     * @throws
     * @return The method as a Method object
     */
    public Method getMethod(final String methodName){
        if(!_annotatedMethods.containsKey(methodName)){
            //TODO: throw new JsonRpcMethodNotFoundException
        }
        return _annotatedMethods.get(methodName);
    }

    /**
     * Gets a list of the annotated method names
     *
     * @return String array of method names
     */
    public String[] getMethodsAsArray(){
      return _annotatedMethods.keySet().toArray(new String[0]);
    }

    /**
     * Has the method passed in got the JsonRpcMethod annotation
     *
     * @param method The method to check
     * @return True if it has the annotation, false otherwise
     */
    private static boolean hasAnnotation(Method method){
       boolean hasAnnotation = false;
       for(Annotation annotation : method.getAnnotations()){
           if(annotation instanceof JsonRpcMethod){
                hasAnnotation = true;
                break;
           }
       }
       return hasAnnotation;
   }
}
