package cn.error0.EJSON;

import cn.error0.EJSON.parser.JSONLexer;
import cn.error0.EJSON.parser.JSONParser;

public abstract class JSON {

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
