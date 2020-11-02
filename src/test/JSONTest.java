
import cn.error0.EJSON.JSON;
import cn.error0.EJSON.JSONArray;
import cn.error0.EJSON.JSONContainer;
import entity.User;
import org.junit.Test;


public class JSONTest {

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
    public void toJSONStringExample()
    {
        JSONContainer jsonObject= new JSONContainer();
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(jsonObject);
        jsonObject.put("array",jsonArray);
        System.out.println(jsonObject);
    }

    @Test
    public void JSONStringToClassExample()
    {
        User user= (User) JSON.parse("{\"value\":1}",User.class);
        System.out.println(user);
    }
}
