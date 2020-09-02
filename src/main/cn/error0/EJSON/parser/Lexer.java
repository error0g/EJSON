package cn.error0.EJSON.parser;

/**
 *  @Description:  词法解析器
 */
public abstract class Lexer {



    /**
     *  @Description:  词法单元类型
     */


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
