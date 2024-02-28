package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
    Boolean val;
    public BoolValue(boolean v){
        val = v;
    }

    public Object getValue(){
        return val;
    }

    @Override
    public String toString() {
        return Boolean.toString(val);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

}
