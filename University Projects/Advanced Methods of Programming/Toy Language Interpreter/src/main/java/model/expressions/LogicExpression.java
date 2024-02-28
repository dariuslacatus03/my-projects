package model.expressions;

import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.TypeCheckerException;

public class LogicExpression implements Expression {
    Expression expression1;
    Expression expression2;
    int operator; //1 - &&, 2 - ||

    public LogicExpression(Expression expression1, Expression expression2, int operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public String toString(){
        if (operator == 1)
        {
            return expression1.toString() + " && " + expression2.toString();
        }
        else
        {
            return expression1.toString() + " || " + expression2.toString();
        }
    }
    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeapTable<Integer, Value> heapTable) throws ExpressionEvaluationException
    {
        Value value1, value2;
        value1 = expression1.evaluate(symTable, heapTable);

        if(value1.getType().equals(new BoolType()))
        {
            value2 = expression2.evaluate(symTable, heapTable);
            if(value2.getType().equals(new BoolType()))
            {
                BoolValue bool1 = (BoolValue) value1;
                BoolValue bool2 = (BoolValue) value2;
                boolean number1, number2;
                number1 = (boolean)bool1.getValue();
                number2 = (boolean)bool2.getValue();
                if(operator == 1)
                {
                    return new BoolValue(number1 && number2);
                }
                if(operator == 2)
                {
                    return new BoolValue(number1 || number2);
                }

            }
            else throw new ExpressionEvaluationException("Second operand is not a boolean");
        }
        throw new ExpressionEvaluationException("First operand is not a boolean");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type type1, type2;
        type1 = this.expression1.typeCheck(typeEnv);
        type2 = this.expression2.typeCheck(typeEnv);
        if(!type1.equals(new BoolType())){
            throw new TypeCheckerException("First expression is not a boolean");
        }

        if(!type2.equals(new BoolType())){
            throw new TypeCheckerException("Second expression is not a boolean");
        }

        return new BoolType();
    }
}
