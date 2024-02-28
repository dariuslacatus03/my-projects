package model.adts;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeapTable<Key, Value> {

    public Integer getAddress();

    public void setAddress(int newAddress);

    void put(Key key, Value v);

    void remove(Key key);

    boolean isDefined(Key key);

    Value lookUp(Key key);

    void update(Key key, Value v);

    public void setContent(Map<Key, Value> newHeap);

    public Map<Key, Value> getContent();



}
