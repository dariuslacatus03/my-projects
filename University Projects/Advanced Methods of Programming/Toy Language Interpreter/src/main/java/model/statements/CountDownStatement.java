package model.statements;

import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;
import model.adts.MyIDictionary;
import model.programState.ProgramState;
import model.values.IntValue;
import model.values.Value;
import model.types.*;
public class CountDownStatement implements IStmt{
    private String variableName;

    public CountDownStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, Exception {
        Value variableValue = state.getSymbolTable().lookUp(variableName);
        // if var is not in symbol table, or it has not the type int then print an error message and terminate the execution.
        if (variableValue == null)
            throw new StatementExecutionException("variable not declared");
        if (!variableValue.getType().equals(new IntType()))
            throw new StatementExecutionException("variable not of int type");

        ProgramState.lock.lock();

        // latchLocation = lookup(symbolTable,var)
        int latchLocation = (int)((IntValue) variableValue).getValue();
        Integer latchValue = state.getLatchTable().get(latchLocation);

        // if latchLocation is not an index in the LatchTable then
        if (latchValue == null) {
            ProgramState.lock.unlock();
            throw new StatementExecutionException("Invalid latch table location!");
        }
        // if LatchTable[latchLocation] > 0 then
        if (latchValue > 0)
            // LatchTable[latchLocation]=LatchTable[latchLocation]-1;
            state.getLatchTable().update(latchLocation, latchValue-1);

        // write into out table the current programState id
        state.getOutputList().add(new IntValue(state.getId()));

        ProgramState.lock.unlock();

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeTable) throws TypeCheckerException {
        try {
            Type variableType = typeTable.lookUp(variableName);
            if (variableType == null)
                throw new TypeCheckerException("variable not declared");
            if (!variableType.equals(new IntType()))
                throw new TypeCheckerException("variable not integer");

        } catch (TypeCheckerException e) {
            throw new TypeCheckerException(e.getMessage());
        }

        return typeTable;
    }

    public IStmt deepCopy() {
        return new CountDownStatement(variableName);
    }

    @Override
    public String toString() {
        return "countDown(" + variableName + ")";
    }

}
