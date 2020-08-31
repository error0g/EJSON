package Parser.List;

import Parser.Lexer;
import Parser.Parser;
import Parser.Token;
import Parser.Lexer.LexerType;

import static Parser.Lexer.LexerType.*;

/**
 *        文法规则:
 *        stat     :list |array
 *        list     : '{' elements '}'
 *        array    :  '[' Value (',' 'Value') *
 *        elements :  element  (',' 'element')*
 *        element  :  NAME  : NAME | list
 *        Key      : " NAME "
 *        Value    : NAME|list|{}|array|Flase|True|Null
 *        NAME     :  Number | String
 * */

public class ListParser extends Parser {
    /**
     *  @Description:  Token数组索引
     */
   private int index=0;


    public ListParser(Lexer lexer) {
        super(lexer);
        tokens=new Token[5];
        for(int i=1;i<=5;i++)
        {
            consume();
        }
    }

    public void stat()
    {
        switch (LT(1))
        {
            case LBRACES:{
              System.out.println( List());
                break;
            }
            case LBRACKET:{
                System.out.println( Array());
                break;
            }
        }

        /**
         * @Description:排除JSON是否有多余的字符串
         * */
        while (LT(1)!=EOFTYPE)
        {
            match(EOFTYPE);
        }
    }

    private String List() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(LBRACES);
        stringBuilder.append(elements());
        stringBuilder.append(LA(1).getValue());
        match(RBRACES);
        return stringBuilder.toString();
    }
    private String EmptyList()
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(LBRACES);
        stringBuilder.append(LA(1).getValue());
        match(RBRACES);
        return stringBuilder.toString();
    }
    private String Array()
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        match(LBRACKET);
        stringBuilder.append(Value());
        while (LT(1)==COMMA){
            consume();
            stringBuilder.append(",");
            stringBuilder.append(Value());
        }
        stringBuilder.append("]");
        match(RBRACKET);
        return stringBuilder.toString();
    }

    private String elements() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(element());
        while (LT(1)==COMMA){
            consume();
            stringBuilder.append(",");
            stringBuilder.append(element());
        }
    return stringBuilder.toString();
    }

    private String element() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(Key());
        stringBuilder.append(LA(1).getValue());
        match(EQUATION);
        stringBuilder.append(Value());
        return stringBuilder.toString();
    }
    private String Key()
    {
        return String();
    }
    private String String() {

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(STRING);
       while (LT(1)!=STRING)
       {
           stringBuilder.append(LA(1).getValue());
           if(LT(1)==EQUATION)
           {
               match(EQUATION);
           }
           else {
               match(NAME);
           }
       }
        stringBuilder.append(LA(1).getValue());
        match(STRING);
        return stringBuilder.toString();
    }
    private String Integer() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(NAME);
        return stringBuilder.toString();
    }
    private String Value()
    {
        switch (LT(1))
        {
            case STRING:{
                return String();
            }
            case NAME:{
               if( isNumber(LA(1).getValue()))
               {
                  return Integer();
               }
               else {
                   throw new Error("type error:"+LT(1));
               }

            }
            case LBRACES:{
                switch (LT(2))
                {
                    case RBRACES:
                    {
                        return EmptyList();

                    }
                    case STRING:
                    {
                        return List();

                    }
                }
                break;
            }
            case LBRACKET:{
              return  Array();
            }
            case NULL:{
                String NullV=LA(1).getValue();
                match(NULL);
                return NullV;
            }
            case FALSE:{
                String FalseV=LA(1).getValue();
                match(FALSE);
                return FalseV;
            }
            case TRUE:{
                String TrueV=LA(1).getValue();
                match(TRUE);
                return TrueV;

            }
        }
        return new String();
    }


    @Override
    public void match(LexerType type) {
        if(LT(1)==type) {
            consume();
        }
        else {
            throw  new Error("expecting:"+type+" found:"+LT(1));
        }
    }

    @Override
    public void consume() {
        tokens[index]=lexer.NextToken();
        index=(index+1)%tokens.length;
    }

    /**
     * @Description:查看第i个Token
     * */
    public Token LA(int i)
    {
        return tokens[(index+i-1)%tokens.length];
    }
    public LexerType LT(int i)
    {
        return LA(i).getType();
    }

    public boolean isNumber(String value)
    {
        return value.matches("^[+-]?\\d+(\\.\\d+)?$");
    }
}
