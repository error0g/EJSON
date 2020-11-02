import cn.error0.EJSON.parser.JSONLexer;
import cn.error0.EJSON.parser.Token;
import org.junit.Test;

public class ParserTest {

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

}
