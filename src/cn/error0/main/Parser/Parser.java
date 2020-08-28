package Parser;



public abstract class Parser {

    /**
     *  @Description:需要分析完词法才能进行语法分析
     * */
   public Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    /**
     * @Description:为了满足匹配条件 {@link List.ListParser#ListParser }
     *  @Description:初始化了5个Token数组用于缓存,注意此数组是循环数组。LL(1)已经不足匹配了需要用LL(k)语法
     *  {@link List.ListParser#LA}
     * */
    public  Token[] tokens;

    /**
     * @Description:与当前Token进行类型匹配，如果匹配成功指针向前移动并且忽略已被匹配的Token.
     * @Description:指针向前移动方法 {@link #consume()}
     * */
    public abstract void match(int type);

    /**
     * @Description:指针向前移动
     * */
    public abstract void consume();


}
