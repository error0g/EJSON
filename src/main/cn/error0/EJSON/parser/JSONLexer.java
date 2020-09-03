package cn.error0.EJSON.parser;

import  cn.error0.EJSON.parser.Token.TokenType;
import static cn.error0.EJSON.parser.Token.TokenType.*;


public class JSONLexer  {

    private int index;
    private char next;
    private String input;

    public JSONLexer(String input) {
       this.input=input;
        this.next=0;
        this.next=input.charAt(index);
    }

    public void match(char  c) {
        if(next==c)
        {
            consume();
        }
        else {
            throw new Error("type match error :("+c+","+next+")");
        }
    }

    public void consume() {
        index++;
        if(index>=input.length())
        {
            next= (char) EOF.getType();
        }
        else {
            next=input.charAt(index);
        }
    }


    public Token NextToken() {
        while (next!=((char) EOF.getType()))

        {

            switch (next)
            {
                case ' ': case '\n': case '\t': case '\r':WS();continue;
                case '"':match('"');return new Token(STRING, "\"");
                case ',':match(',');return new Token(COMMA,",");
                case ':':match(':'); return new Token(EQUATION,":");
                case '{':match('{'); return new Token(LBRACES,"{");
                case '}':match('}'); return new Token(RBRACES,"}");
                case '[':match('['); return new Token(LBRACKET,"[");
                case ']':match(']'); return new Token(RBRACKET,"]");
                default:
                    if(isNAME())
                    {
                        return NAME();
                    }
                    else {
                        throw new Error("invalid character:"+next+"-"+index);
                    }
            }
        }
        return new Token(EOFTYPE,"EOF");
    }



    public Boolean isNAME() {
        return isSymbol();
    }


    public Boolean isSymbol()
    {

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
         TokenType type;
        do {
            word.append(next);
            consume();
        }while (isNAME());

        switch (word.toString())
        {
            case "false":type=FALSE;break;
            case "null":type=NULL;break;
            case "true":type=TRUE;break;
            default:type=NAME;break;
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
