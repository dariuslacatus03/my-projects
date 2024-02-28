package model.statements;

import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;
import model.adts.MyIDictionary;
import model.expressions.Expression;
import model.programState.ProgramState;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

import java.io.IOException;

public class ConditionalAssignmentStatement implements IStmt{
    String variableName;
    Expression expression1;
    Expression expression2;
    Expression expression3;

    public ConditionalAssignmentStatement(String varName, Expression e1, Expression e2, Expression e3)
    {
        this.variableName = varName;
        this.expression1 = e1;
        this.expression2 = e2;
        this.expression3 = e3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, StatementExecutionException, IOException {
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        if (symTable.isDefined(variableName))
        {
            Value value1 = expression1.evaluate(symTable, state.getHeapTable());
            Value value2 = expression2.evaluate(symTable, state.getHeapTable());
            Value value3 = expression3.evaluate(symTable, state.getHeapTable());
            Type typeId = (symTable.lookUp(variableName)).getType();
            if (value2.getType().equals(typeId) && value3.getType().equals(typeId))
            {
                if (value1.getType().equals(new BoolType()))
                {
                    boolean condition = (boolean)((BoolValue) value1).getValue();
                    if(condition)
                        symTable.update(variableName, value2);
                    else
                        symTable.update(variableName, value3);
                }
                else
                    throw new StatementExecutionException("The expression to verify must have type bool!\n");
            }
            else
                throw new StatementExecutionException("The variable and the two expressions to assign must have the same type!\n");
        }
        else
            throw new StatementExecutionException("variable not declared\n");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type typeVar = typeEnv.lookUp(variableName);
        Type typeExpr2 = expression2.typeCheck(typeEnv);
        Type typeExpr3 = expression3.typeCheck(typeEnv);
        if (typeVar.equals(typeExpr2) && typeVar.equals(typeExpr3))
        {
            Type typeExpr1 = expression1.typeCheck(typeEnv);
            if (typeExpr1.equals(new BoolType()))
            {
                return typeEnv;
            }
            else
                throw new TypeCheckerException("The expression to verify must have type bool!\n");
        }
        else
            throw new TypeCheckerException("he variable and the two expressions to assign must have the same type!\n");
    }

    @Override
    public String toString() {
        return variableName + " = " + expression1.toString() + "?" + expression2.toString() + ":" + expression3.toString();
    }
}

//    @Override
//    public Statement deepCopy() {
//        return new ConditionalAssignmentStatement(key, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy());
//    }
