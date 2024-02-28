package model.expressions;

import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.types.Type;
import model.values.Value;
import controller.exceptions.TypeCheckerException;

public class ValueExpression implements Expression {
    Value expression;

    public ValueExpression(Value expression) {
        this.expression = expression;
    }

    @Override
    public String toString(){
        return expression.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeapTable<Integer, Value> heapTable) {
        return expression;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        return this.expression.getType();
    }
}
