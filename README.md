# EJSON
 ![](https://img.shields.io/badge/language-Java-orange.svg)  ![](https://img.shields.io/badge/license-MIT-000000.svg)



<div align=center>
	<img src="https://s1.ax1x.com/2020/08/28/doKs5d.png" >
</div>


**此项目用于分享学习，切勿用于生产环境**。

EJSON是一个用Java语言开发的JSON库。

如果你现在有学习相关JSON的知识想动手实现操作欢迎你提交代码。

## 目前

- [x] JSON格式字符串解析器 
- [x] JSON与JSONArray对象
- [x] JSON转字符串

## Examples

```java
src/test/ParserTest.java


import cn.error0.EJSON.JSON;
import cn.error0.EJSON.JSONArray;
import cn.error0.EJSON.JSONContainer;
import cn.error0.EJSON.parser.Token;
import org.junit.Test;
import cn.error0.EJSON.parser.JSONLexer;


public class ParserTest {

    @Test
    public void Example1()
    {
        String text="{\"Number\":[-1.1,2,3,100000000]}";
        JSONContainer json= (JSONContainer) JSON.parse(text);
        JSONArray array= (JSONArray) json.get("Number");
        array.forEach(System.out::println);

        System.out.println(json);
    }

    @Test
   public void Example2()
    {
        String text="{\"boolean\":[null,false,true]}";

        JSONContainer json= (JSONContainer) JSON.parse(text);
        JSONArray array= (JSONArray) json.get("boolean");
        array.forEach((item)->{
            System.out.println(item);
        });
        System.out.println(json);
    }

    @Test
    public void LexerExample()
    {
        String jsonstr="{\"n\":1.1}";
        JSONLexer lexer=new JSONLexer(jsonstr);
        Token token=lexer.NextToken();
        while (token.getType()!=Token.TokenType.EOFTYPE)
        {
            System.out.println(token);
            token=lexer.NextToken();
        }
    }

    @Test
    public void toJSONStringExample()
    {
        JSONContainer jsonObject= new JSONContainer();
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(jsonObject);
        jsonObject.put("array",jsonArray);
        System.out.println(jsonObject);
    }
}

```



## License

[MIT](https://github.com/RichardLitt/standard-readme/blob/master/LICENSE) © Richard Littauer
