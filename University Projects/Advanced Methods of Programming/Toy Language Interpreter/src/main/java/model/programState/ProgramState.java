package model.programState;

import model.adts.*;
import model.statements.IStmt;
import model.values.StringValue;
import model.values.Value;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProgramState {
    MyIStack<IStmt> executionStack;
    MyIDictionary<String, Value> symbolTable;
    MyIList<Value> outputList;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeapTable<Integer, Value> heapTable;
    MyILatchTable latchTable;
    static public Lock lock = new ReentrantLock();
    int id;
    static int nextID = 1;

    public static synchronized int generateNewID(){
        return nextID++;
    }

    public ProgramState(MyIStack<IStmt> stack,
                        MyIDictionary<String,Value> table,
                        MyIList<Value> output,
                        MyIDictionary<StringValue, BufferedReader> fileTbl,
                        MyIHeapTable<Integer, Value> heapTbl,
                        MyLatchTable latch,
                        IStmt program) {
        executionStack = stack;
        symbolTable = table;
        outputList = output;
        fileTable = fileTbl;
        heapTable = heapTbl;
        latchTable = latch;
        this.id = generateNewID();
        executionStack.push(program);
    }

    public MyIStack<IStmt> getExecutionStack() {
        return executionStack;
    }

    public MyIDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public MyIList<Value> getOutputList() {
        return outputList;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeapTable<Integer, Value> getHeapTable() { return heapTable; }

    public MyILatchTable getLatchTable() { return latchTable; };
    public int getId() { return this.id; }
    public boolean isNotCompleted(){
        return !this.executionStack.isEmpty();
    }

    public ProgramState oneStep() throws ExpressionEvaluationException, StatementExecutionException, IOException, Exception {
        if(this.executionStack.isEmpty())
            throw new ExpressionEvaluationException("Program state stack is empty");
        IStmt currentStmt = this.executionStack.pop();
        return currentStmt.execute(this);
    }


    @Override
    public String toString() {
        return  "\n-------------------------------------\n" +
                "ProgramState ID " + this.id + ":\n" +
                "executionStack = " + executionStack.getReversed() +
                "\nsymbolTable = " + symbolTable +
                "\noutputList = " + outputList +
                "\nfileTable = " + fileTable +
                "\nheapTable = " + heapTable +
                "\nlatchTable = " + latchTable +
                "\n-------------------------------------\n";
    }
}
