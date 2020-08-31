package cn.error0.EJSON;

public class JSON {

    String Key;
    Object Value;
    public Object get(){
        return null;
    }
    public void add(String key,Object value)
    {

    }



    @Override
    public String toString() {
        return "{" +
                "Key='" + Key + '\'' +
                ", Value=" + Value +
                '}';
    }
}
