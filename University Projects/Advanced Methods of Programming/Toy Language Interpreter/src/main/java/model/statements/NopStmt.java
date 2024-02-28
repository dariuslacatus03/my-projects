package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.types.Type;
import controller.exceptions.TypeCheckerException;

public class NopStmt implements IStmt{
    public ProgramState execute(ProgramState state)
    {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException {
        return typeEnv;
    }
}
