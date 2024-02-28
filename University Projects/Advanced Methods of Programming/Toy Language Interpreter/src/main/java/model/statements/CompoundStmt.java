package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.types.Type;
import controller.exceptions.TypeCheckerException;

public class CompoundStmt implements IStmt{
    IStmt firstStmt;
    IStmt secondStmt;

    public CompoundStmt(IStmt firstStmt, IStmt secondStmt) {
        this.firstStmt = firstStmt;
        this.secondStmt = secondStmt;
    }

    @Override
    public String toString() {
        return "(" + firstStmt.toString() + "\n" + secondStmt.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        MyIStack<IStmt> stack = state.getExecutionStack();
        stack.push(secondStmt);
        stack.push(firstStmt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException, Exception {
        return this.secondStmt.typeCheck(this.firstStmt.typeCheck(typeEnv));
    }
}
