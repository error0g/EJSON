# EJSON -[作者博客](https://www.error0.cn/)

------

![logo](https://s1.ax1x.com/2020/08/28/doKs5d.png)





此项目主要用于分享学习，切勿用于生产环境。

作者在学编译原理，为了实践操作所以创建了此项目，目前完成了JSON格式的字符串解析,并为后续功能做准备。

欢迎大家纠正错误或提交代码。

## Future

- 把解析的数据进行封装到JSON对象，并实现些操作功能。
- 把文档、代码注释写得更加详细。
- ......

## Example

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