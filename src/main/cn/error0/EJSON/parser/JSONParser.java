package cn.error0.EJSON.parser;



import cn.error0.EJSON.JSONArray;
import cn.error0.EJSON.JSONContainer;
import  cn.error0.EJSON.parser.Token.TokenType;
import static cn.error0.EJSON.parser.Token.TokenType.*;


/**
 * LL(1)语法可能会有歧义 所以用了词法缓存池LA(1) 返回当前index索引的词法元素
 *  LA(2) 返回当前index+1词法元素 以此类推
 * */

public class JSONParser   {


    /**
     * @field lexer  词法分析器
     * @field tokens  词法单元缓存池
     * @field index  词法单元缓存池 索引
     * */
    private   JSONLexer lexer;
    private   Token[] tokens;
    private int index=0;


    class Node{
        String key;
        Object value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public JSONParser() { }

    /**
     * 梦的开始，为了区分是JSON数组和JSON 用LT 向前看1个词法单元
     * */
    public Object start(String text)
    {
        this.lexer=new JSONLexer(text);
        tokens=new Token[5];
        for(int i=1;i<=5;i++)
        {
            consume();
        }


        switch (LT(1))
        {
            case BeginObjet:{
              return List();
            }
            case BeginArray:{
                return Array();
            }
        }

       //判断多字符串后面多余的字符
        while (LT(1)!=EOFTYPE)
        {
            match(EOFTYPE);
        }
        return null;
    }
    /**
     * 文法：{ elements }
     * */
    private JSONContainer List() {
        JSONContainer list=null;
        match(BeginObjet);
        list=elements();
        match(EndObjet);
        return list;
    }
    /**
     * 文法：{ }
     * */
    private String EmptyList()
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(BeginObjet);
        stringBuilder.append(LA(1).getValue());
        match(EndObjet);
        return stringBuilder.toString();
    }
    /**
     * 文法：[ value (,value)+]
     * */
    private JSONArray Array()
    {
        JSONArray array=new JSONArray<>();
        match(BeginArray);
        array.add(Value());
        while (LT(1)==COMMA){
            consume();
            array.add(Value());
        }
        match(EndArray);
        return array;
    }
    /**
     * 文法：element (, element)+
     * */
    private JSONContainer elements() {
        JSONContainer elements=new JSONContainer();
        Node element=element();
        elements.put(element.getKey(),element.getValue());
        while (LT(1)==COMMA){
            consume();
            element=element();
            elements.put(element.getKey(),element.getValue());
        }
        return elements;
    }
    /**
     * 文法：key : value
     * */
    private Node element() {
        Node node=new Node();
        node.setKey(Key());
        match(EQUATION);
        node.setValue(Value());
        return node;
    }

    private String Key()
    {
        StringBuilder stringBuilder=new StringBuilder();
        match(QuotationMakr);
        while (LT(1)!=QuotationMakr)
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
        match(QuotationMakr);
        return stringBuilder.toString();
    }


    private Object Value()
    {
        switch (LT(1))
        {
            case QuotationMakr:{
                return string();
            }
            case NAME:{
               if( isInteger(LA(1).getValue()))
               {
                   Integer integer= Integer.valueOf(LA(1).getValue());
                   match(NAME);
                  return integer;
               }
               else if(isFloat(LA(1).getValue()))
               {
                   Double doublev= Double.valueOf(LA(1).getValue());
                   match(NAME);
                   return doublev;
               }
               else {
                   throw new Error("Token type error:"+LT(1));
               }

            }
            case BeginObjet:{
                switch (LT(2))
                {
                    case EndObjet:
                    {
                        return EmptyList();

                    }
                    case QuotationMakr:
                    {
                        return List();

                    }
                }
                break;
            }
            case BeginArray:{
              return  Array();
            }
            case NULL:{

                match(NULL);
                return null;
            }
            case FALSE:{
                match(FALSE);
                return false;
            }
            case TRUE:{
                match(TRUE);
                return true;

            }
        }
        return new String();
    }

    private String string() {

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(LA(1).getValue());
        match(QuotationMakr);
        while (LT(1)!=QuotationMakr)
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
        match(QuotationMakr);
        return stringBuilder.toString();
    }

    private void match(TokenType type) {
        if(LT(1)==type) {
            consume();
        }
        else {
            throw  new Error("expecting:"+type+" found:"+LT(1));
        }
    }


    private void consume() {
        tokens[index]=lexer.NextToken();
        index=(index+1)%tokens.length;
    }


    private Token LA(int i)
    {
        return tokens[(index+i-1)%tokens.length];
    }
    private TokenType LT(int i)
    {
        return LA(i).getType();
    }

    public boolean isInteger(String value)
    {
        return value.matches("^-?[1-9]\\d*$");
    }

    public boolean isFloat(String value)
    {
        return value.matches("^-?[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$");
    }
}
