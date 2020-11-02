package cn.error0.EJSON;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class JSONContainer<K,V> extends JSON  implements Map<K, V>  {

    private final Map<K,V> map;
    private StringBuilder stringBuilder;
    public JSONContainer() {
        this.map = new HashMap();
        this.stringBuilder=new StringBuilder();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key,value);
    }


    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
         map.putAll(m);
    }

    @Override
    public void clear() {
         map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }


    @Override
    public String toString() {
        //链接JSON对象
        res.put(this.hashCode(),JSON_CONTAINER);

        int index = 0;
        stringBuilder.append(LEFT_CURLY_BRACES);
        for (Map.Entry<K,V>  entry : map.entrySet()) {
            String key= (String) entry.getKey();
            Object value= entry.getValue();
            stringBuilder.append(QUOTATIO);
            stringBuilder.append(key);
            stringBuilder.append(QUOTATIO);
            stringBuilder.append(EQUATION);
            //value 有三种可能 JSONContainer、JSONArray、普通值，要做相对于的处理
            if(value instanceof JSONContainer)
            {
                JSONContainer jsonContainer= (JSONContainer) value;
                if(res.get(value.hashCode())!=null)
                {
                    res.put(value.hashCode(),JSON_CONTAINER);
                    stringBuilder.append(jsonContainer);
                }
                else {
                    //重复引用
                    JSONContainer json=new JSONContainer();
                    json.put(REF,OMIT);
                    stringBuilder.append(json);
                }
            }

            else if(value instanceof JSONArray)
            {

                JSONArray jsonArray= (JSONArray) value;
                if(res.get(value.hashCode())==null)
                {
                    res.put(value.hashCode(),JSON_ARRAY);
                    stringBuilder.append(jsonArray);
                }
                else {
                    //重复引用
                    JSONContainer jsonContainer=new JSONContainer();
                    jsonContainer.put(REF,OMIT);
                    stringBuilder.append(jsonContainer);
                }
            }
            else
                {

                if(value instanceof String)
                {
                    stringBuilder.append(QUOTATIO);
                    stringBuilder.append(value);
                    stringBuilder.append(QUOTATIO);
                }
                else {
                    stringBuilder.append(value);
                }
            }

            if(index<map.size()-1)
            {
                stringBuilder.append(COMMA);
            }
            ++index;
        }
        stringBuilder.append(RIGHT_CURLY_BRACES);
        return stringBuilder.toString();
    }

}
