import Parser.List.ListLexer;
import Parser.List.ListParser;
import Parser.Lexer;
import org.junit.Test;

public class ParserTest {
    @Test
   public void Test()
    {
        String json="{\"n\":1}";
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
//    enum LexerType
//    {
//        EOF(-1),
//        EOFTYPE(1),
//        COMMA(2),
//        LBRACES(3),
//        RBRACES(4),
//        EQUATION(5),
//        NAME(6),
//        STRING(7),
//        NULL(8),
//        TRUE(9),
//        FALSE(10),
//        LBRACKET(11),
//        RBRACKET(12);
//        int type;
//        LexerType(int type) {
//            this.type=type;
//        }
//
//        public int getType() {
//            return type;
//        }
//    }
//
//    @Test
//    public void EnumTest()
//    {
//       System.out.println( LexerType.NAME.getType());
//    }
}
