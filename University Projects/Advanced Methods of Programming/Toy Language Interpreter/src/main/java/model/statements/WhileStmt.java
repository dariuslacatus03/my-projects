package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.Expression;
import model.types.BoolType;
import model.types.Type;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

public class WhileStmt implements IStmt{
    Expression expression;
    IStmt statement;

    public WhileStmt(Expression expression, IStmt statement)
    {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString()
    {
        return "while("+expression.toString()+") \n" + statement.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException
    {
        MyIStack<IStmt> stack = state.getExecutionStack();
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(!value.getType().equals(new BoolType())){
            throw new ExpressionEvaluationException("Conditional expression is not a boolean");
        }
        if(value.getValue().equals(true)){
            stack.push(this);
            stack.push(statement);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException, Exception {
        Type typeExpression = this.expression.typeCheck(typeEnv);
        if (typeExpression.equals(new BoolType())){
            this.statement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new TypeCheckerException("WhileStatement: Conditional expression is not of boolean type!\n");
    }
}
