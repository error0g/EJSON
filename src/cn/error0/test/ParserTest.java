import List.ListLexer;
import List.ListParser;
import Parser.Lexer;
import org.junit.Test;

public class ParserTest {
    @Test
   public void Test()
    {
        String json="{\"name\":\"error0\",\"Age\":20.0,\"Hobby\":[\"Codeing\",\"skateboard\",\"guitar\"]}";
        Lexer lexer=new ListLexer(json);
//        Token token=lexer.NextToken();
//        while (token.getType()!=Lexer.EOFTYPE)
//        {
//            System.out.println(token);
//            token=lexer.NextToken();
//        }
        ListParser ListParser=new ListParser(lexer);
        ListParser.stat();

    }
}
