package Parser;

/**
 *  @Description:  词法解析器
 */
public abstract class Lexer {



    /**
     *  @Description:  词法单元类型
     */
    public enum LexerType
    {
        EOF(-1),
        EOFTYPE(1),
        COMMA(2),
        LBRACES(3),
        RBRACES(4),
        EQUATION(5),
        NAME(6),
        STRING(7),
        NULL(8),
        TRUE(9),
        FALSE(10),
        LBRACKET(11),
        RBRACKET(12);
        int type;
        LexerType(int type) {
            this.type=type;
        }

        public int getType() {
            return type;
        }
    }
//    public static final  char EOF= (char) -1;
//    public static final  int EOFTYPE=1;
//    public static final  int COMMA=2;
//    public static final  int LBRACES=3;
//    public static final  int RBRACES=4;
//    public static final  int EQUATION=5;
//    public static final  int NAME=6;
//    public static final int  STRING=7;
//    public static final int  NULL=8;
//    public static final int  TRUE=9;
//    public static final int  FALSE=10;
//    public static final int  LBRACKET=11;
//    public static final int  RBRACKET=12;
//    private static String Names[]={"n/a","<EOF>","COMMA","LBRACK","RBRACK","EQUATION","NAME","STRING","NULL","TRUE","FALSE","LBRACKET","RBRACKET"};
    /**
     * @Description:输入流
     * */
    public String input;

    public Lexer(String input) {
        this.input = input;
    }


    /**
     *  @Description:  匹配词法 LL(1)词法规则匹配
     */
    public abstract void match(char c);
    /**
     *  @Description:  向前移动指针
     */
    public abstract void consume();
    /**
     *  @Description:  每次调用返回一个NextToken
     */
    public abstract Token NextToken();


}
