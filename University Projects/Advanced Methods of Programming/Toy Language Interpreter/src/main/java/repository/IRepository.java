package repository;

import model.programState.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    void add(ProgramState e);

    void logProgramStateExecution(ProgramState state) throws IOException;

    List<ProgramState> getProgramList();

    void setProgramList(List<ProgramState> programStates);
}
