package model.expressions;

import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.TypeCheckerException;

public class ReadHeapExpression implements Expression{

    Expression expression;

    public ReadHeapExpression(Expression expression)
    {
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "readHeap("+expression.toString()+")";
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeapTable<Integer, Value> heapTable) throws ExpressionEvaluationException {
        Value evaluatedExpression = expression.evaluate(symTable, heapTable);
        if (!(evaluatedExpression.getType() instanceof RefType))
        {
            throw new ExpressionEvaluationException("expression not of type RefType");
        }
        if (!heapTable.isDefined((((RefValue)evaluatedExpression).getAddress())))
        {
            throw new ExpressionEvaluationException("variable not defined on heap");
        }
        else
        {
            return heapTable.lookUp((((RefValue)evaluatedExpression).getAddress()));
        }
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type type = this.expression.typeCheck(typeEnv);

        if(type instanceof RefType refType){
            return refType.getInner();
        }
        else
            throw new TypeCheckerException("the readHeap argument is not a reference type");
    }
}
