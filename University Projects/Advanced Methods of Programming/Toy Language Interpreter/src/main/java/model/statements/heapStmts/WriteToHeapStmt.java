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

public class WriteToHeapStmt implements IStmt {

    String variableName;
    Expression expression;

    public WriteToHeapStmt(String variableName, Expression expression)
    {
         this.variableName = variableName;
         this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "writeHeap("+variableName+ ", " + expression.toString()+");";
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, StatementExecutionException, IOException {
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        if (!symTable.isDefined(variableName))
        {
            throw new StatementExecutionException("variable is not defined");
        }
        if (!(symTable.lookUp(variableName).getType() instanceof RefType))
        {
            throw new StatementExecutionException("variable not instance of RefType");
        }
        MyIHeapTable<Integer, Value> heapTable = state.getHeapTable();
        if (!heapTable.isDefined((((RefValue)symTable.lookUp(variableName)).getAddress())))
        {
            throw new StatementExecutionException("variable not defined on the heap");
        }
        Value evaluatedExpression = expression.evaluate(symTable, heapTable);
        if (!evaluatedExpression.getType().equals(((RefType) symTable.lookUp(variableName).getType()).getInner()))
        {
            throw new StatementExecutionException("types do not match");
        }
        heapTable.update((((RefValue)symTable.lookUp(variableName)).getAddress()), evaluatedExpression);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type typeVariable = typeEnv.lookUp(this.variableName);
        Type typeExpression = this.expression.typeCheck(typeEnv);
        if (typeVariable.equals(new RefType(typeExpression))){
            return typeEnv;
        }
        else
            throw new TypeCheckerException("HeapWritingStatement: Expression cannot be evaluated to: " + typeExpression);
    }
}
