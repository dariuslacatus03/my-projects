package model.expressions;

import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.types.Type;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.TypeCheckerException;

public class VariableExpression implements Expression {
    String variable;

    public VariableExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public String toString(){
        return variable;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeapTable<Integer, Value> heapTable) throws ExpressionEvaluationException {
        if ( symTable.isDefined(variable))
        {
            return  symTable.lookUp(variable);
        }

        throw new ExpressionEvaluationException("variable \"" + variable + "\" not defined");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        return typeEnv.lookUp(this.variable);
    }
}
