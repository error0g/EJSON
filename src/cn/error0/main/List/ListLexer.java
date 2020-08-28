package List;

import Parser.Lexer;
import Parser.Token;

public class ListLexer extends Lexer {


    /**
     * index 当前字符串指针
     * next  当前需要匹配的字符串
     * */
    int index;
    char next;

    public ListLexer(String input) {
        super(input);
        this.next=0;
        this.next=input.charAt(index);
    }

    @Override
    public void match(char  c) {
        if(next==c)
        {
            consume();
        }
        else {
            throw new Error("grammar error :("+c+","+next+")");
        }
    }
    @Override
    public void consume() {
        index++;
        if(index>=super.input.length())
        {
            next= Lexer.EOF;
        }
        else {
            next=super.input.charAt(index);
        }
    }

    @Override
    public Token NextToken() {
        while (next!=Lexer.EOF)
        {
            switch (next)
            {
                case ' ': case '\n': case '\t': case '\r':WS();continue;
                case '"':match('"');return new Token(Lexer.STRING, "\"");
                case ',':match(',');return new Token(Lexer.COMMA,",");
                case ':':match(':'); return new Token(Lexer.EQUATION,":");
                case '{':match('{'); return new Token(Lexer.LBRACES,"{");
                case '}':match('}'); return new Token(Lexer.RBRACES,"}");
                case '[':match('['); return new Token(Lexer.LBRACKET,"[");
                case ']':match(']'); return new Token(Lexer.RBRACKET,"]");
                default:
                    if(isNAME())
                    {
                        return NAME();
                    }
                    else {
                        throw new Error("invalid character:"+next+index);
                    }
            }
        }
        return new Token(Lexer.EOFTYPE,"EOF");
    }



    public Boolean isNAME() {
        return isSymbol();
    }

    public Boolean isSymbol()
    {
        /**
         * @Description:过滤关键字
         * */
        switch (next)
        {
            case '"':return false;
            case ',':return false;
            case '[':return false;
            case ']':return false;
            case '{':return false;
            case '}':return false;
        }

        return (next>=0x4e00&&next<=0x9fa5)||(next>=0xFF00&&next<=0xFFEF)||(next>=32&&next<=126);
    }


    public Token NAME()
    {

        StringBuilder word=new StringBuilder();
        int type;
        do {
            word.append(next);
            consume();
        }while (isNAME());

        switch (word.toString())
        {
            case "false":type=Lexer.FALSE;break;
            case "null":type=Lexer.NULL;break;
            case "true":type=Lexer.TRUE;break;
            default:type=Lexer.NAME;break;
    }
        return new Token(type,word.toString());
    }

    /**
     * @Description:过滤换行空格
     * */
    public void WS()
    {
        while (next==' '||next=='\n'||next=='\t'||next=='\r')
        {
            consume();
        }
    }

}
