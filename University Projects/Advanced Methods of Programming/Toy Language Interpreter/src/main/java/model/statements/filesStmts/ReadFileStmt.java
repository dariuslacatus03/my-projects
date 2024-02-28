package model.statements.filesStmts;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.expressions.Expression;
import model.statements.IStmt;
import model.types.IntType;
import model.types.StringType;
import model.types.Type;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    Expression expression;
    String varName;
    public ReadFileStmt(Expression expression, String varName)
    {
        this.expression = expression;
        this.varName = varName;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, IOException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        Value value = symbolTable.lookUp(varName);
        if(value.getType().equals(new IntType()))
        {
            Value fileName = expression.evaluate(symbolTable, state.getHeapTable());
            if(fileName.getType().equals(new StringType()) )
            {
                MyIDictionary<StringValue, BufferedReader>  fileTable = state.getFileTable();
                if(fileTable.isDefined((StringValue)fileName))
                {
                    try
                    {
                        BufferedReader descr =  fileTable.lookUp((StringValue)fileName);
                        String readValue = descr.readLine();
                        int readValInt = 0;
                        if(readValue != null)
                            readValInt = Integer.parseInt(readValue);
                        symbolTable.update(varName, new IntValue(readValInt));
                        return null;
                    }
                    catch (IOException exception){
                        throw new StatementExecutionException("Couldn't read line");
                    }
                }
            }
            else {
                throw new StatementExecutionException("Filename must be string");
            }
        }
        throw new StatementExecutionException("Variable must be of IntType");
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        Type typeVariable = typeEnv.lookUp(this.varName);
        Type typeExpression = this.expression.typeCheck(typeEnv);
        if (typeVariable.equals(new IntType())){
            if (typeExpression.equals(new StringType())){
                return typeEnv;
            }
            else
                throw new TypeCheckerException("ReadFileStatement: file path should be a string value!\n");
        }
        throw new TypeCheckerException("ReadFileStatement: " + this.varName + " is not an integer!\n");
    }

    @Override
    public String toString() {
        return "ReadFile(" +
                expression.toString() +
                ", " + varName.toString() +')';
    }
}
