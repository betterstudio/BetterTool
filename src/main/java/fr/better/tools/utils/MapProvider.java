package fr.better.tools.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapProvider<K,V> {

    private final Map<K, V> map;

    public MapProvider() {
        this.map  = new HashMap<>();
    }

    public void put(K k, V v){
        map.put(k,v);
    }

    public void remove(K k){
        map.remove(k);
    }

    public void replace(K k, V v){
        map.replace(k,v);
    }

    public Set<K> keys(){
        return map.keySet();
    }

    public Collection<V> values(){
        return map.values();
    }

    public Set<Entry<K, V>> entries(){
        return map.entrySet();
    }
}
