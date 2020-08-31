# EJSON

![](https://img.shields.io/badge/language-Java-orange.svg) ![](https://img.shields.io/cocoapods/l/EJSON.svg) 



<div align=center>
	<img src="https://s1.ax1x.com/2020/08/28/doKs5d.png" >
</div>


**此项目用于分享学习，切勿用于生产环境**。

EJSON是一个用Java语言开发的JSON库。

如果你现在有学习相关的JSON知识想动手实现操作欢迎你提交代码。

## 目前

- [x] JSON格式字符串解析器 

## Future

- 把解析的数据进行封装到JSON对象，并实现些操作功能。
- 把文档、代码注释写得更加详细。
- ......

## Examples

```java
EJSON\src\cn\error0\test#Test()
/**
* JSON格式字符串解析测试，输出格式和原来输入字符串基本没差别因为没有做特殊的处理
* 如果格式错误会抛出错误
*/
Import
       String json="[1,2,\":\"]";
        Lexer lexer=new ListLexer(json);
		ListParser ListParser=new ListParser(lexer);
        ListParser.stat();
    
Output   
    [1,2,":"] 
 
Import
        String json="{\"name\":\"error0\",\"Age\":20.0,\"Hobby\":[\"Codeing\",\"skateboard\",\"guitar\"]}";
        Lexer lexer=new ListLexer(json);
		ListParser ListParser=new ListParser(lexer);
        ListParser.stat();
    
Output   
      {"name":"error0","Age":20.0,"Hobby":["Codeing","skateboard","guitar"]}


```



## License

[MIT](https://github.com/RichardLitt/standard-readme/blob/master/LICENSE) © Richard Littauer
