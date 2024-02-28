package model.adts;

public interface MyILatchTable extends MyIDictionary<Integer, Integer>{
    int put(Integer value) throws Exception;
    int get(int position) throws Exception;
    int getFirstFreeLocation();

}
