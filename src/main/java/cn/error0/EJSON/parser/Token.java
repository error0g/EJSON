package java.cn.error0.EJSON.parser;

import java.cn.error0.EJSON.parser.Lexer.LexerType;
public class Token {
    /**
     * type 单元类型
     * value 单元值
     * */
    LexerType type;
    String  value;

    public Token(LexerType type, String value) {
        this.type = type;
        this.value = value;
    }

    public LexerType getType() {
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
