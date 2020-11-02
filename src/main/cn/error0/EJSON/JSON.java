package cn.error0.EJSON;


import cn.error0.EJSON.parser.JSONParser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public  class JSON {

    final static String REF="$ref";
    final static String JSON_ARRAY="JSONArray";
    final static String JSON_CONTAINER="JSONContainer";
    final static String LEFT_EBRACKET="[";
    final static String RIGHT_BRACKET="]";
    final static String QUOTATIO="\"";
    final static String OMIT="...";
    final static String COMMA=",";
    final static String EQUATION=":";
    final static String LEFT_CURLY_BRACES="{";
    final static String RIGHT_CURLY_BRACES="}";

    public static final Map<Integer,String> res =new HashMap<>();
    final static JSONParser parser=new JSONParser();

    public static Object parse(String text) {
        Object object=null;
        if(text==null||text.length()==0) {
            return object;
        }
        object=parser.start(text);
        return object;
    }

    public static Object parse(String text,Class c) {
        Object object=null;
        if(text==null||text.length()==0) {
            return object;
        }

        Object e= parser.start(text);
        if(e instanceof JSONContainer)
        {
            try {
                object=c.newInstance();
                Field[] fields = c.getDeclaredFields();
                for(Field field:fields) {
                    field.setAccessible(true);
                    field.set(object,((JSONContainer) e).get(field.getName()));
                }

            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return object;
    }

}
