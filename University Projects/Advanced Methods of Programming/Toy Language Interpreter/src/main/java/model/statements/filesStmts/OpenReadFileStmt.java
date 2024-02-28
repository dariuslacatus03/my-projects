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
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class OpenReadFileStmt implements IStmt {

    Expression expression;

    public OpenReadFileStmt(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, IOException, Exception {

        Value value = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(value.getType().equals(new StringType()))
        {
            MyIDictionary<StringValue, BufferedReader> fileTable =state.getFileTable();
            StringValue filename = (StringValue) value;
            if(fileTable.isDefined(filename))
                throw new StatementExecutionException("File already open");
            else{
                try {
                    Reader reader =  new FileReader(filename.getValue());
                    BufferedReader fileDesc = new BufferedReader(reader);
                    fileTable.put(filename, fileDesc);
                }
                catch(IOException ex){
                    throw new StatementExecutionException("Couldn't open the file");

                }
            }
        }
        else{
            throw new StatementExecutionException("Expression must be String");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type typeExpression = this.expression.typeCheck(typeEnv);
        if (!typeExpression.equals(new StringType())){
            throw new TypeCheckerException("OpenFileStatement: file path should be a string value!\n");
        }
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "OpenFile(" + this.expression.toString() + ")";
    }


}