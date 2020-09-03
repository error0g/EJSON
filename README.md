# EJSON
 ![](https://img.shields.io/badge/language-Java-orange.svg)  ![](https://img.shields.io/badge/license-MIT-000000.svg)



<div align=center>
	<img src="https://s1.ax1x.com/2020/08/28/doKs5d.png" >
</div>


**此项目用于分享学习，切勿用于生产环境**。

EJSON是一个用Java语言开发的JSON库。

如果你现在有学习相关的JSON知识想动手实现操作欢迎你提交代码。

## 目前

- [x] JSON格式字符串解析器 
- [x] 封装JSON与JSONArray对象

## Future

- 把解析的数据进行封装到JSON对象，并实现些操作功能。
- 把文档、代码注释写得更加详细。
- ......

## Examples

```java
EJSON\src\java.cn\error0\ParserTest

  @Test
    public void Example1()
    {
        String text="{\"Number\":[-1.1,2,3,100000000]}";
        JSONContainer json= (JSONContainer) JSON.parse(text);
        JSONArray array= (JSONArray) json.get("Number");
        array.forEach(System.out::println);
    }
   
Output:   
        -1.1
        2
        3
        100000000
   ============================================================================================================
   @Test
   public void Example2()
    {
        String text="{\"boolean\":[null,false,true]}";

        JSONContainer json= (JSONContainer) JSON.parse(text);
        JSONArray array= (JSONArray) json.get("boolean");
        array.forEach((item)->{
            System.out.println(item);
        });

    }
    
Output:   
        null
        false
        true
============================================================================================================
 @Test
    public void LexerTest()
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
Output:      
        <LBRACES,{>
        <STRING,">
        <NAME,n>
        <STRING,">
        <EQUATION,:>
        <NAME,1.1>
        <RBRACES,}>
```



## License

[MIT](https://github.com/RichardLitt/standard-readme/blob/master/LICENSE) © Richard Littauer
