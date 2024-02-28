package model.statements;

import model.programState.ProgramState;
import model.adts.MyIDictionary;
import model.types.Type;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import controller.exceptions.TypeCheckerException;

import java.io.IOException;

public interface IStmt {
    ProgramState execute(ProgramState state) throws ExpressionEvaluationException, StatementExecutionException, IOException, Exception;
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckerException, Exception;
}
