package model.values;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{
    int val;
    public IntValue(int v){
        val = v;
    }

    @Override
    public Object getValue(){
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof IntValue)
        {
            return this.val==(int)((IntValue) another).getValue();
        }
        return false;
    }

}
