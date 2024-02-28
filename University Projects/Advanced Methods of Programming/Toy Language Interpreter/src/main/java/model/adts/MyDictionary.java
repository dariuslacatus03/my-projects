package model.adts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<Key, Value> implements MyIDictionary<Key, Value>{
    Map<Key, Value> map;

    public MyDictionary() {
        map = new HashMap<>();
    }

    @Override
    public void put(Key key, Value value) throws Exception {
        map.put(key, value);
    }

    @Override
    public void remove(Key key)
    {
        map.remove(key);
    }

    @Override
    public boolean isDefined(Key key) {
        Set<Key> keys = map.keySet();
        for (Key k : keys)
        {
            if (k.equals(key))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Value lookUp(Key key) {
        Set<Key> keys = map.keySet();
        for (Key k : keys)
        {
            if (k.equals(key))
            {
                return map.get(k);
            }
        }
        return null;
    }

    @Override
    public void update(Key key, Value v) {
        Set<Key> keys = map.keySet();
        for (Key k : keys)
        {
            if (k.equals(key))
            {
                map.put(k, v);
            }
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public Map<Key, Value> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Key, Value> map) {
        this.map = map;
    }

    @Override
    public MyIDictionary<Key, Value> copy() throws Exception{
        MyIDictionary<Key, Value> newDictionary = new MyDictionary<>();

        for(Key key : this.map.keySet()){
            newDictionary.put(key, this.map.get(key));
        }

        return newDictionary;
    }
}
