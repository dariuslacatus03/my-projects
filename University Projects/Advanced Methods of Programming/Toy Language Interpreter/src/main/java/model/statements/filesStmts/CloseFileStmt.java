package model.statements.filesStmts;
import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.expressions.Expression;
import model.statements.IStmt;
import model.types.StringType;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStmt implements IStmt {
    private Expression expression;

    public CloseFileStmt(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(value.getType().equals(new StringType()))
        {
            MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
            if(fileTable.isDefined((StringValue)value))
            {
                BufferedReader fileDescr = fileTable.lookUp((StringValue)value);
                try {
                    fileTable.remove((StringValue)value);
                    fileDescr.close();
                    return null;
                }
                catch(IOException exception) {
                    throw new StatementExecutionException("Couldn't close file");
                }
            }
            else
                throw new StatementExecutionException("File not found");

        }
        else
            throw new StatementExecutionException("Filename must be StringValue");

    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type typeExpression = this.expression.typeCheck(typeEnv);
        if (!typeExpression.equals(new StringType())){
            throw new TypeCheckerException("CloseFileStatement: file path should be a string value!\n");
        }
        return typeEnv;
    }

    @Override
    public String toString() {
        return "CloseFile(" +
                expression.toString() +
                ')';
    }
}