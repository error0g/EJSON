package List;

import Parser.Lexer;
import Parser.Parser;
import Parser.Token;


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
            case Lexer.LBRACES:{
              System.out.println( List());
                break;
            }
            case Lexer.LBRACKET:{
                System.out.println( Array());
                break;
            }
        }

        /**
         * @Description:排除JSON是否有多余的字符串
         * */
        while (LT(1)!=Lexer.EOFTYPE)
        {
            match(Lexer.EOFTYPE);
        }
    }

    private String List() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(Lexer.LBRACES);
        stringBuilder.append(elements());
        stringBuilder.append(LA(1).getValue());
        match(Lexer.RBRACES);
        return stringBuilder.toString();
    }
    private String EmptyList()
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(Lexer.LBRACES);
        stringBuilder.append(LA(1).getValue());
        match(Lexer.RBRACES);
        return stringBuilder.toString();
    }
    private String Array()
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        match(Lexer.LBRACKET);
        stringBuilder.append(Value());
        while (LT(1)==Lexer.COMMA){
            consume();
            stringBuilder.append(",");
            stringBuilder.append(Value());
        }
        stringBuilder.append("]");
        match(Lexer.RBRACKET);
        return stringBuilder.toString();
    }

    private String elements() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(element());
        while (LT(1)==Lexer.COMMA){
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
        match(Lexer.EQUATION);
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
        match(Lexer.STRING);
       while (LT(1)!=Lexer.STRING)
       {
           stringBuilder.append(LA(1).getValue());
           if(LT(1)==Lexer.EQUATION)
           {
               match(Lexer.EQUATION);
           }
           else {
               match(Lexer.NAME);
           }
       }
        stringBuilder.append(LA(1).getValue());
        match(Lexer.STRING);
        return stringBuilder.toString();
    }
    private String Integer() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(Lexer.NAME);
        return stringBuilder.toString();
    }
    private String Value()
    {
        switch (LT(1))
        {
            case Lexer.STRING:{
                return String();
            }
            case Lexer.NAME:{
               if( isNumber(LA(1).getValue()))
               {
                  return Integer();
               }
               else {
                   throw new Error("type error:"+Lexer.getNameByType(LT(1)));
               }

            }
            case Lexer.LBRACES:{
                switch (LT(2))
                {
                    case Lexer.RBRACES:
                    {
                        return EmptyList();

                    }
                    case Lexer.STRING:
                    {
                        return List();

                    }
                }
                break;
            }
            case Lexer.LBRACKET:{
              return  Array();
            }
            case Lexer.NULL:{
                String NULL=LA(1).getValue();
                match(Lexer.NULL);
                return NULL;
            }
            case Lexer.FALSE:{
                String FALSE=LA(1).getValue();
                match(Lexer.FALSE);
                return FALSE;
            }
            case Lexer.TRUE:{
                String TRUE=LA(1).getValue();
                match(Lexer.TRUE);
                return TRUE;

            }
        }
        return new String();
    }


    @Override
    public void match(int type) {
        if(LT(1)==type) {
            consume();
        }
        else {
            throw  new Error("expecting:"+Lexer.getNameByType(type)+" found:"+Lexer.getNameByType(LT(1)));
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
    public int LT(int i)
    {
        return LA(i).getType();
    }

    public boolean isNumber(String value)
    {
        return value.matches("^[+-]?\\d+(\\.\\d+)?$");
    }
}
