package cn.error0.EJSON;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class JSONContainer<K,V> extends JSON  implements Map<K, V>  {

    private final Map<K,V> map;

    public JSONContainer() {
        this.map = new HashMap();
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
        int index = 0;
        res.put(this.hashCode(),"JSONContainer");
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("{");
        for (Map.Entry<K,V>  entry : map.entrySet()) {
            String key= (String) entry.getKey();
            Object value= entry.getValue();
            stringBuilder.append("\"");
            stringBuilder.append(key);
            stringBuilder.append("\"");
            stringBuilder.append(":");
            if(value instanceof JSONContainer)
            {
                JSONContainer jsonContainer= (JSONContainer) value;
                if(res.get(value.hashCode())!=null)
                {
                    res.put(value.hashCode(),"JSONContainer");
                    stringBuilder.append(jsonContainer);
                }
                else {
                    JSONContainer json=new JSONContainer();
                    json.put("$ref","...");
                    stringBuilder.append(json);
                }
            }
            else if(value instanceof JSONArray)
            {

                JSONArray jsonArray= (JSONArray) value;
                if(res.get(value.hashCode())==null)
                {
                    res.put(value.hashCode(),"JSONArray");
                    stringBuilder.append(jsonArray);
                }
                else {
                    JSONContainer jsonContainer=new JSONContainer();
                    jsonContainer.put("$ref","...");
                    stringBuilder.append(jsonContainer);
                    stringBuilder.append("}");
                }
            }
            else {
                if(value instanceof String)
                {
                    stringBuilder.append("\"");
                    stringBuilder.append(value);
                    stringBuilder.append("\"");
                }
                else {
                    stringBuilder.append(value);
                }
            }
            if(index<map.size()-1)
            {
                stringBuilder.append(",");
            }
            ++index;
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
