package cn.error0.EJSON;

import cn.error0.EJSON.parser.JSONLexer;
import cn.error0.EJSON.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public abstract class JSON {

    public static final Map<Integer,String> res =new HashMap<>();

    public static Object parse(String text) {
        if(text==null) {
            return null;
        }
        Object e=null;
        JSONLexer lexer=new JSONLexer(text);
        JSONParser JSONParser =new JSONParser(lexer);
        e=JSONParser.stat();
        return e;
    }

}
