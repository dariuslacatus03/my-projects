package model.adts;

import java.util.Map;

public interface MyIDictionary<Key, Value> {
    void put(Key key, Value v) throws Exception;

    void remove(Key key);

    boolean isDefined(Key key);

    Value lookUp(Key key);

    void update(Key key, Value v);

    public void setContent(Map<Key, Value> newHeap);

    public Map<Key, Value> getContent();

    public MyIDictionary<Key, Value> copy() throws Exception;
}
