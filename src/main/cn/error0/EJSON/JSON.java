package cn.error0.EJSON;


import cn.error0.EJSON.parser.JSONParser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public  class JSON {

    protected final static String REF="$ref";
    protected final static String JSON_ARRAY="JSONArray";
    protected final static String JSON_CONTAINER="JSONContainer";
    protected final static String LEFT_EBRACKET="[";
    protected final static String RIGHT_BRACKET="]";
    protected final static String QUOTATIO="\"";
    protected final static String OMIT="...";
    protected final static String COMMA=",";
    protected final static String EQUATION=":";
    protected final static String LEFT_CURLY_BRACES="{";
    protected final static String RIGHT_CURLY_BRACES="}";

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


    public static Object parse(String text, Class c) {
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


    public static String toJSONString(Object o)
    {
        JSONContainer jsonContainer=new JSONContainer();
        Class c=o.getClass();
        Field[] Fields = c.getDeclaredFields();
        for(Field field:Fields)
        {
            field.setAccessible(true);
            String key=field.getName();
            Object value=ComparisonType(field,o);
            jsonContainer.put(key,value);
        }

        return jsonContainer.toString();
    }

    private static Object ComparisonType(Field field,Object o) {
        try {
            if (Integer.class.equals(field.getType())) {
                return field.getInt(o);
            }
            else  if (Character.class.equals(field.getType())) {
                return field.getChar(o);
            }
            else  if (Double.class.equals(field.getType())) {
                return field.getDouble(o);
            }
            else  if (Float.class.equals(field.getType())) {
                return field.getFloat(o);
            }
            else  if (Boolean.class.equals(field.getType())) {
                return field.getBoolean(o);
            }
            else  if (Short.class.equals(field.getType())) {
                return field.getShort(o);
            }
            else  if (Byte.class.equals(field.getType())) {
                return field.getByte(o);
            }
            else  {  return field.get(o); }
        }catch (IllegalAccessException  e) {
            e.printStackTrace();
        }
        return  null;
    }

}
