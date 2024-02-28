package model.adts;

import java.util.HashMap;
import java.util.Map;

public class MyHeapTable<Value> implements MyIHeapTable<Integer, Value>{
    private Map<Integer, Value> heapTable;
    private int address;

    public MyHeapTable()
    {
        heapTable = new HashMap<Integer, Value>();
        address = 0;
    }

    public Integer getAddress()
    {
        return this.address;
    }

    public void setAddress(int newAddress){
        this.address = newAddress;
    }

    @Override
    public void put(Integer key, Value value) {
        heapTable.put(key, value);
    }

    @Override
    public void remove(Integer key)
    {
        heapTable.remove(key);
    }

    @Override
    public boolean isDefined(Integer key) {
        if(heapTable.containsKey(key)){
            return true;
        }
        return false;
    }

    @Override
    public Value lookUp(Integer key) {
        if(heapTable.containsKey(key)){
            return heapTable.get(key);
        }
        return null;
    }

    @Override
    public void update(Integer key, Value v) {
        if(heapTable.containsKey(key)){
            heapTable.put(key, v);
        }
    }

    @Override
    public String toString() {
        return heapTable.toString();
    }

    @Override
    public Map<Integer, Value> getContent() {
        return heapTable;
    }

    @Override
    public void setContent(Map<Integer, Value> map) {
        this.heapTable = map;
    }

}