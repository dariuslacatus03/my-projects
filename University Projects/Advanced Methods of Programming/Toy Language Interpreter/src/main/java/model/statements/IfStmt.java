package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.Expression;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

public class IfStmt implements IStmt {
    Expression expression;
    IStmt thenStmt;
    IStmt elseStmt;
    public IfStmt(Expression expression, IStmt tStmt, IStmt eStmt)
    {
        this.expression = expression;
        thenStmt = tStmt;
        elseStmt = eStmt;
    }

    @Override
    public String toString(){
        return "if ("+ expression.toString()+") \n{\n " + thenStmt.toString() +"\n}\nelse\n{\n "+ elseStmt.toString()+"\n}";
    }

    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        Value expectedBool = expression.evaluate(symTable, state.getHeapTable());
        if(expectedBool.getType().equals(new BoolType()))
        {
            MyIStack<IStmt> stack = state.getExecutionStack();
            BoolValue boolExpressionValue = (BoolValue) expectedBool;
            if((Boolean) boolExpressionValue.getValue())
                stack.push(thenStmt);
            else
                stack.push(elseStmt);
            return null;
        }
        throw new StatementExecutionException("expression is not of boolean type");
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException, Exception {
        Type typeExpression = this.expression.typeCheck(typeEnv);
        if (typeExpression.equals(new BoolType())){
            this.thenStmt.typeCheck(typeEnv.copy());
            this.elseStmt.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new TypeCheckerException("The condition of IF doesn't have the type bool!\n");
    }
}
