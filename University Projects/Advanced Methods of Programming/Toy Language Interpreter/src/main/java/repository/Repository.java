package repository;

import model.programState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<ProgramState> programStates;
    private String logFilePath;

    public Repository(String filename)
    {
        logFilePath = filename;
        programStates = new ArrayList<ProgramState>();
    }

    @Override
    public void add(ProgramState e){
        programStates.add(e);
    }

    @Override
    public void logProgramStateExecution(ProgramState program) throws IOException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            logFile.write(program.toString());
            logFile.write("\n\n\n");
        } catch (IOException e) {
            System.out.println("Error opening the file!\n");
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.programStates;
    }

    @Override
    public void setProgramList(List<ProgramState> programStates) {
        this.programStates = programStates;
    }


//    @Override
//    public String toString() {
//        return "Repository{" +
//                "repo=" +
//                '}';
//    }
}
