package Parser;


public class Token {
    /**
     * type 单元类型
     * value 单元值
     * */
    int type;
    String value;

    public Token(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
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
        String name= Lexer.getNameByType(type);
        sb.append("<");
        sb.append(name);
        sb.append(",");
        sb.append(value);
        sb.append(">");
        return sb.toString();
    }
}
