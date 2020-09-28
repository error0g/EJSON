package cn.error0.EJSON.parser;

public class Token {

    public  enum TokenType
    {
        EOF(-1),
        EOFTYPE(1),
        COMMA(2),
        LBRACES(3),
        RBRACES(4),
        EQUATION(5),
        NAME(6),
        QUOTESMAKES(7),
        NULL(8),
        TRUE(9),
        FALSE(10),
        LBRACKET(11),
        RBRACKET(12);
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
        sb.append("<");
        sb.append(type);
        sb.append(",");
        sb.append(value);
        sb.append(">");
        return sb.toString();
    }
}
