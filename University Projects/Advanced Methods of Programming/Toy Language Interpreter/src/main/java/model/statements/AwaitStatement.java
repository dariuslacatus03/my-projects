package model.statements;

import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;
import model.adts.MyIDictionary;
import model.programState.ProgramState;
import model.types.IntType;
import model.types.Type;
import model.values.*;

public class AwaitStatement implements IStmt{
    private String variableName;

    public AwaitStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, Exception {
        try {
            Value variableValue = state.getSymbolTable().lookUp(variableName);
            // if var is not in symbol table, or it has not the type int then print an error message and terminate the execution.
            if (variableValue == null)
                throw new StatementExecutionException("variable not declared");
            if (!variableValue.getType().equals(new IntType()))
                throw new StatementExecutionException("variable not integer");

            // latchLocation = lookup(symbolTable,var)
            int latchLocation = (int)((IntValue) variableValue).getValue();
            Integer latchValue = state.getLatchTable().get(latchLocation);


            ProgramState.lock.lock();

            // if latchLocation is not an index in the LatchTable then
            if (latchValue == null) {
                ProgramState.lock.unlock();
                throw new StatementExecutionException("Invalid latch table location!");
            }
            // if LatchTable[latchLocation] != 0 then
            if (latchValue != 0)
                // push back the await statement(that means the current program state must wait for the countdownlatch to reach zero)
                state.getExecutionStack().push(this);

            ProgramState.lock.unlock();

            return null;
        } catch (StatementExecutionException e) {
            throw new StatementExecutionException(e.getMessage());
        }
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
        return new AwaitStatement(variableName);
    }

    @Override
    public String toString() {
        return "await(" + variableName + ")";
    }

}
