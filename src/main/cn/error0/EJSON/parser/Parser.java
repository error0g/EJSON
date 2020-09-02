package cn.error0.EJSON.parser;
import cn.error0.EJSON.parser.Token.TokenType;
public abstract class Parser {

    /**
     *  @Description:需要分析完词法才能进行语法分析
     * */
   public Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }


    public  Token[] tokens;

    /**
     * @Description:与当前Token进行类型匹配，如果匹配成功指针向前移动并且忽略已被匹配的Token.
     * @Description:指针向前移动方法 {@link #consume()}
     * */
    public abstract void match(TokenType type);

    /**
     * @Description:指针向前移动
     * */
    public abstract void consume();


}
