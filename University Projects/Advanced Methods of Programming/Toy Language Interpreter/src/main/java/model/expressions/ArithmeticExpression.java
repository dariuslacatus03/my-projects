package model.expressions;

import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.TypeCheckerException;

public class ArithmeticExpression implements Expression {
    Expression expression1;
    Expression expression2;
    char operator;

    public ArithmeticExpression(char operator, Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }


    @Override
    public String toString(){
        return expression1.toString() + " " + operator + " " + expression2.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeapTable<Integer, Value> heapTable) throws ExpressionEvaluationException {
        Value value1, value2;
        value1 = expression1.evaluate(symTable, heapTable);
        if (value1.getType().equals(new IntType())) {
            value2 = expression2.evaluate(symTable, heapTable);
            if (value2.getType().equals(new IntType()))
            {
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int number1, number2;
                number1 = (int) int1.getValue();
                number2 = (int) int2.getValue();
                if (operator == '+')
                    return new IntValue(number1+number2);
                if (operator == '-')
                    return new IntValue(number1-number2);
                if (operator == '*')
                    return new IntValue(number1*number2);
                if (operator == '/')
                    if (number2 == 0) throw new ExpressionEvaluationException("division by zero");
                    else
                        return new IntValue(number1/number2);
            }
            else {
                throw new ExpressionEvaluationException("second operand is not an integer");
            }
        }
        throw new ExpressionEvaluationException("first operand is not an integer");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType()))
                return new IntType();
            else
                throw new TypeCheckerException("second operand is not an integer\n");
        }
        else
            throw new TypeCheckerException("first operand is not an integer\n");
    }
}
