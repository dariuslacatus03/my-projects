package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.types.*;
import model.values.*;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

public class VariableDeclarationStmt implements IStmt{
    String variableName;
    Type type;

    public VariableDeclarationStmt(String variableName, Type type) {
        this.variableName = variableName;
        this.type = type;
    }

    @Override
    public String toString(){
        return type.toString() + " " + variableName + ";";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, Exception {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        if (symbolTable.isDefined(variableName))
        {
            throw new StatementExecutionException("variable \"" + variableName + "\" is already declared");
        }
        else
        {
            Value varDefaultValue = type.defaultValue();
            symbolTable.put(variableName, varDefaultValue);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException, Exception {
        typeEnv.put(variableName, type);
        return typeEnv;
    }
}
