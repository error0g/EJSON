package cn.error0.EJSON;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSONContainer<K,V> implements Map<K, V> {

   Map<K, V> Container;

    public JSONContainer() {
        Container=new HashMap<>();
    }


    @Override
    public int size() {
        return Container.size();
    }

    @Override
    public boolean isEmpty() {
        return Container.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return Container.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return Container.get(key);
    }

    @Override
    public V put(K key, V value) {

        return Container.put(key,value);
    }

    @Override
    public V remove(Object key) {
        return Container.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Container.putAll(m);
    }

    @Override
    public void clear() {
        Container.clear();
    }

    @Override
    public Set<K> keySet() {
        return Container.keySet();
    }

    @Override
    public Collection<V> values() {
        return Container.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Container.entrySet();
    }


}
