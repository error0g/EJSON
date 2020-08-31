import Parser.List.ListLexer;
import Parser.List.ListParser;
import Parser.Lexer;
import org.junit.Test;

public class ParserTest {

    @Test
    public void Example1()
    {
        String json="[1,2,\":\"]";
        Lexer lexer=new ListLexer(json);
        ListParser ListParser=new ListParser(lexer);
        ListParser.stat();

    }

    @Test
   public void Example2()
    {
        String json="{\"name\":\"error0\",\"Age\":20.0,\"Hobby\":[\"Codeing\",\"skateboard\",\"guitar\"]}";
        Lexer lexer=new ListLexer(json);
        ListParser ListParser=new ListParser(lexer);
        ListParser.stat();

    }

}
