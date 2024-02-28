package model.statements;

import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;
import model.adts.MyIDictionary;
import model.expressions.Expression;
import model.programState.ProgramState;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;
import java.io.IOException;

public class LatchStatement implements IStmt{
    private String variableName;
    private Expression expression;

    public LatchStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, Exception{
        ProgramState.lock.lock();

        Value expressionValue = expression.evaluate(state.getSymbolTable(), state.getHeapTable());

        if (!expressionValue.getType().equals(new IntType())) {
            ProgramState.lock.unlock();
            throw new StatementExecutionException("Expression " + expression.toString() + " should have evaluate to an integer");
        }

        int latch = (int)((IntValue) expressionValue).getValue();
        int latchLocation = state.getLatchTable().put(latch);
        Value variableValue = state.getSymbolTable().lookUp(variableName);

        if (variableValue == null) {
            ProgramState.lock.unlock();
            throw new StatementExecutionException("variable not declared");
        }

        if (!variableValue.getType().equals(new IntType())) {
            ProgramState.lock.unlock();
            throw new StatementExecutionException("variable should be of integer type");
        }
        state.getSymbolTable().update(variableName, new IntValue(latchLocation));
        ProgramState.lock.unlock();
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type variableType = typeEnv.lookUp(variableName);
        if (variableName == null)
            throw new TypeCheckerException("variable not declared");
        if (!variableType.equals(new IntType()))
            throw new TypeCheckerException("variable should be of integer type");

        Type expressionType = expression.typeCheck(typeEnv);
        if (!expressionType.equals(new IntType()))
            throw new TypeCheckerException("expression should be of integer type");

        return typeEnv;
    }


    public IStmt deepCopy() {
        return new LatchStatement(variableName, expression);
    }

    @Override
    public String toString() {
        return "newLatch(" + variableName + ", " + expression.toString() + ")";
    }

}
