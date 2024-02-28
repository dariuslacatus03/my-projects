package model.adts;

import java.util.List;

public interface MyIList<Type> {
    void add(Type e);

    void clear();

    List<Type> getList();
}
