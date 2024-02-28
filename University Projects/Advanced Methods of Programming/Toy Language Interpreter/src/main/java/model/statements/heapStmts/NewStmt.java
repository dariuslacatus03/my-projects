package model.statements.heapStmts;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.adts.MyIHeapTable;
import model.expressions.Expression;
import model.statements.IStmt;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

import java.io.IOException;

public class NewStmt implements IStmt {
    String variableName;
    Expression expression;


    public NewStmt(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "new(" + variableName + ", " + expression.toString() + ");";
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, StatementExecutionException, IOException {
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        MyIHeapTable<Integer, Value> heapTable = state.getHeapTable();
        if (!symTable.isDefined(variableName))
        {
            throw new StatementExecutionException("the used variable " + variableName + " was not declared before");
        }
        Value value = symTable.lookUp(variableName);
        if (!(value.getType() instanceof RefType))
        {
            throw new StatementExecutionException("variable is not a reference");
        }
        Value expressionValue = expression.evaluate(symTable, heapTable);
        if (!expressionValue.getType().equals(((RefType) value.getType()).getInner()))
        {
            throw new StatementExecutionException("types do not match");
        }
        int newAddress = heapTable.getAddress() + 1;
        heapTable.setAddress(newAddress);
        heapTable.put(newAddress, expressionValue);
        RefValue refValue = new RefValue(newAddress, expressionValue.getType());
        symTable.update(variableName, refValue);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type typeVariable = typeEnv.lookUp(this.variableName);
        Type typeExpression = expression.typeCheck(typeEnv);
        if(typeVariable.equals(new RefType(typeExpression))){
            return typeEnv;
        }
        else
            throw new TypeCheckerException("HealAllocationStatement: right hand side and left hand side have different types!\n");
    }


}
