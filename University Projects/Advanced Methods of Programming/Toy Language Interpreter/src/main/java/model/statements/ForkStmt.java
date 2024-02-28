package model.statements;

import model.adts.MyLatchTable;
import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.adts.MyStack;
import model.types.Type;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

import java.io.IOException;

public class ForkStmt implements IStmt {
    IStmt statement;

    public ForkStmt(IStmt stmt){
        this.statement = stmt;
    }

    @Override
    public String toString(){
        return "fork(" + statement.toString()+")endFork";
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, StatementExecutionException, IOException , Exception{
        MyIStack<IStmt> newStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = state.getSymbolTable();
        MyIDictionary<String, Value> newDictionary = symTable.copy();

        return new ProgramState(newStack, newDictionary, state.getOutputList(), state.getFileTable(),
                state.getHeapTable(), (MyLatchTable) state.getLatchTable(), this.statement);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException, Exception {
        // When we make a fork, the child process will use everything we have in the memory until that moment, but when
        // It changes something, it won't affect the parent process, because these two can communicate only through the HEAP
        this.statement.typeCheck(typeEnv.copy());
        return typeEnv;
    }
}
