package model.expressions;

import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.types.Type;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.TypeCheckerException;

public interface Expression {
    Value evaluate(MyIDictionary<String, Value> symTable, MyIHeapTable<Integer, Value> heapTable) throws ExpressionEvaluationException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException;
}
