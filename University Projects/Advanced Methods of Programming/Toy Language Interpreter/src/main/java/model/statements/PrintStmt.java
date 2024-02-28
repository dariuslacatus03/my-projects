package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.adts.MyIList;
import model.expressions.Expression;
import model.types.Type;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.TypeCheckerException;

public class PrintStmt implements IStmt{
    Expression expression;

    public PrintStmt(Expression expression) {
        this.expression = expression;
    }

    @Override
   public String toString()
   {
       return "print(" + expression.toString() + ");";
   }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException {
       MyIList<Value> output = state.getOutputList();
       MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
       output.add(expression.evaluate(symbolTable, state.getHeapTable()));
       return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
