
import cn.error0.EJSON.JSON;
import cn.error0.EJSON.JSONArray;
import cn.error0.EJSON.JSONContainer;
import cn.error0.EJSON.parser.Token;
import org.junit.Test;
import cn.error0.EJSON.parser.JSONLexer;


public class ParserTest {

    @Test
    public void Example1()
    {
        String text="{\"Number\":[-1.1,2,3,100000000]}";
        JSONContainer json= (JSONContainer) JSON.parse(text);
        JSONArray array= (JSONArray) json.get("Number");
        array.forEach(System.out::println);

        System.out.println(json);
    }

    @Test
   public void Example2()
    {
        String text="{\"boolean\":[null,false,true]}";

        JSONContainer json= (JSONContainer) JSON.parse(text);
        JSONArray array= (JSONArray) json.get("boolean");
        array.forEach((item)->{
            System.out.println(item);
        });
        System.out.println(json);
    }

    @Test
    public void LexerExample()
    {
        String jsonstr="{\"n\":1.1}";
        JSONLexer lexer=new JSONLexer(jsonstr);
        Token token=lexer.NextToken();
        while (token.getType()!=Token.TokenType.EOFTYPE)
        {
            System.out.println(token);
            token=lexer.NextToken();
        }
    }

    @Test
    public void toJSONStringExample()
    {
        JSONContainer jsonObject= new JSONContainer();
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(jsonObject);
        jsonObject.put("array",jsonArray);
        System.out.println(jsonObject);
    }
}
