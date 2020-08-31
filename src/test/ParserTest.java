
import org.junit.Test;

import java.cn.error0.EJSON.parser.JSONLexer;
import java.cn.error0.EJSON.parser.JSONParser;
import java.cn.error0.EJSON.parser.Lexer;

public class ParserTest {

    @Test
    public void Example1()
    {
        String json="[1,2,\":\"]";
        Lexer lexer=new JSONLexer(json);
        JSONParser JSONParser =new JSONParser(lexer);
        JSONParser.stat();

    }

    @Test
   public void Example2()
    {
        String json="{\"n\":1}";
        Lexer lexer=new JSONLexer(json);
        JSONParser JSONParser =new JSONParser(lexer);
        JSONParser.stat();

    }


}
