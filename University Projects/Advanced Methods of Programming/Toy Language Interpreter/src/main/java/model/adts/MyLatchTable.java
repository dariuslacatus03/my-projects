package model.adts;

import java.util.Map;

public class MyLatchTable extends MyDictionary<Integer, Integer> implements MyILatchTable{

    int nextFree;

    public MyLatchTable() {
        super();
        this.nextFree = 1;
    }

    @Override
    public void put(Integer k, Integer v) throws Exception {
        if (!k.equals(nextFree))
            throw new Exception("Invalid lock table location!");
        super.put(k, v);
        synchronized (this) {
            nextFree++;
        }
    }

    @Override
    public int put(Integer value) throws Exception{
        super.put(nextFree, value);
        synchronized (this) {
            nextFree++;
        }
        return nextFree-1;
    }

    @Override
    public int get(int position) throws Exception{
        synchronized (this) {
            if (!this.map.containsKey(position))
                throw new Exception("Key not presend in table!\n");
            return this.map.get(position);
        }
    }

    @Override
    public int getFirstFreeLocation() {
        int locationAddress = 1;
        while (this.map.get(locationAddress) != null)
            locationAddress++;
        return locationAddress;
    }
}
