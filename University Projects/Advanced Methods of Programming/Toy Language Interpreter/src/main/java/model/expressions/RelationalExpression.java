package model.expressions;

import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.TypeCheckerException;

public class RelationalExpression implements Expression{

    private Expression expression1;
    private Expression expression2;
    private String compareOperator;

    public RelationalExpression(Expression ex1, Expression ex2, String compareOperator)
    {
        expression1 = ex1;
        expression2 = ex2;
        this.compareOperator = compareOperator;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable, MyIHeapTable<Integer, Value> heapTable) throws ExpressionEvaluationException {
        Value value1 = expression1.evaluate(symbolTable, heapTable);
        Value value2 = expression2.evaluate(symbolTable, heapTable);
        if(value1.getType().equals(new IntType()))
        {
            if(value2.getType().equals(new IntType())){
                IntValue intVal1 = (IntValue) value1;
                IntValue intVal2 = (IntValue) value2;
                if(compareOperator.equals("<")){
                    boolean val;
                    if ((int) intVal1.getValue() < (int) intVal2.getValue()) val = true;
                    else val = false;
                    return new BoolValue(val);
                }
                else if(compareOperator.equals("<="))
                {
                    boolean val;
                    if ((int)intVal1.getValue() <= (int)intVal2.getValue()) val = true;
                    else val = false;
                    return new BoolValue(val);
                }
                else if(compareOperator.equals("=="))
                {
                    boolean val;
                    if ((int)intVal1.getValue() == (int)intVal2.getValue()) val = true;
                    else val = false;
                    return new BoolValue(val);
                }
                else if(compareOperator.equals("!="))
                {
                    boolean val;
                    if ((int)intVal1.getValue() != (int)intVal2.getValue()) val = true;
                    else val = false;
                    return new BoolValue(val);
                }
                else if(compareOperator.equals(">"))
                {
                    boolean val;
                    if ((int)intVal1.getValue() > (int)intVal2.getValue()) val = true;
                    else val = false;
                    return new BoolValue(val);
                }
                else if(compareOperator.equals(">="))
                {
                    boolean val;
                    if ((int)intVal1.getValue() >= (int)intVal2.getValue()) val = true;
                    else val = false;
                    return new BoolValue(val);
                }
                throw new ExpressionEvaluationException("Unsupported operand");

            }
            else throw new ExpressionEvaluationException("Second expression is not of IntType");
        }
        throw new ExpressionEvaluationException("First expression is not of IntType");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type type1, type2;
        type1 = this.expression1.typeCheck(typeEnv);
        type2 = this.expression2.typeCheck(typeEnv);

        if (!type1.equals(new IntType())){
            throw new TypeCheckerException("First expression is not an integer");
        }

        if (!type2.equals(new IntType())){
            throw new TypeCheckerException("Second expression is not an integer");
        }

        return new BoolType();
    }

    public String toString()
    {
        return expression1.toString() +" "+ compareOperator +" "+ expression2.toString();
    }

}
