package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.expressions.Expression;
import model.types.Type;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

public class AssignmentStmt implements IStmt{
    String variableName;
    Expression expression;

    public AssignmentStmt(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return variableName+" = "+ expression.toString() + ";";
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, StatementExecutionException{
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        if (symTable.isDefined(variableName))
        {
            Value expressionValue = expression.evaluate(symTable, state.getHeapTable());
            Type variableType = (symTable.lookUp(variableName)).getType();
            if (expressionValue.getType().equals(variableType))
            {
                symTable.update(variableName, expressionValue);
            }
            else throw new StatementExecutionException("declared type of variable "+variableType+" and type of " +
                    "the assigned expression do not match");
        }
        else throw new StatementExecutionException("the used variable " + variableName + " was not declared before");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type typeVariable = typeEnv.lookUp(this.variableName);
        Type typeExpression = expression.typeCheck(typeEnv);

        if (typeVariable.equals(typeExpression)){
            return typeEnv;
        }
        else throw new TypeCheckerException("Assignment: right hand side and left hand side have different types!\n");
    }
}
