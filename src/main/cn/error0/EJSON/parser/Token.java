package cn.error0.EJSON.parser;

public class Token {

    /*
    *   标记      说明
    *   EOF:        词法匹配结束标记
    *   EOFTYPE:
    *
    *   COMMA:      逗号
    *   BeginObjet: 左大括号
    *   EndObjet:   右大括号
    *   EQUATION:   冒号
    *   NAME: 元素
    *   QuotationMakr: 引号
    *   NULL:        空值
    *   TRUE:        Boolean 类型
    *   FALSE:       Boolean 类型
    *   BeginArray:  数组的左括号
    *   EndArray:    数组的右括号
    * */

    public  enum TokenType
    {
        EOF(-1),
        EOFTYPE(1),
        COMMA(2),
        BeginObjet(3),
        EndObjet(4),
        EQUATION(5),
        NAME(6),
        QuotationMakr(7),
        NULL(8),
        TRUE(9),
        FALSE(10),
        BeginArray(11),
        EndArray(12);
        int type;
        TokenType(int type) {
            this.type=type;
        }

        public int getType() {
            return type;
        }
    }
    /**
     * type 单元类型
     * value 单元值
     * */
    TokenType type;
    String  value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }


    /**
     * @Description:为了方便Debug调用了getNameByType方法转对于的字符toString返回格式为：<type,value>
     * */
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        sb.append(type);
        sb.append(",");
        sb.append(value);
        sb.append("]");
        return sb.toString();
    }
}
