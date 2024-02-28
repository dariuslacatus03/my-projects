package controller;

import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;
import model.adts.MyIHeapTable;
import model.programState.ProgramState;
import model.values.RefValue;
import model.values.Value;
import repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private final IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }


    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    List<Integer> getAddrFromHeap(Map<Integer, Value> heapTable) {
        return heapTable.values().stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr,
                                             List<Integer> heapTableAddr,
                                             ProgramState program) {
        MyIHeapTable<Integer, Value> heap = program.getHeapTable();
        return heap.getContent().entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heapTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void oneStepForAllPrograms(List<ProgramState> programStates) throws InterruptedException, IOException {
        // Before the execution, print the ProgramStates into the log file
//        for (ProgramState programState : programStates) {
//            try {
//                repository.logProgramStateExecution(programState);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
        // RUN concurrently one step for each of the existing ProgramStates
        // Prepare the list of callables
        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .toList();

        // Start the execution of the callables
        // It returns the list of new created ProgramStates (namely threads)
        List<ProgramState> newProgramStates = executor.invokeAll(callList).stream()
                .map(future ->
                {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        // Add the new created threads to the list of existing threads
        programStates.addAll(newProgramStates);

        Collection<Value> addresses = programStates.stream()
                .flatMap(program -> program.getSymbolTable().getContent().values().stream())
                .toList();

        programStates.get(0).getHeapTable().setContent(
                this.safeGarbageCollector(
                        this.getAddrFromSymTable(addresses),
                        this.getAddrFromHeap(programStates.get(0).getHeapTable().getContent()),
                        programStates.get(0)
                )
        );

        // After the execution, print the ProgramStates into the log file
        for (ProgramState programState : programStates) {
            repository.logProgramStateExecution(programState);
        }

        // Save the current programs in the repository
        repository.setProgramList(programStates);
    }

    public void oneStep() throws  IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedProgram(repository.getProgramList());


        oneStepForAllPrograms(programStates);

        Collection<Value> addresses = programStates.stream()
                .flatMap(program -> program.getSymbolTable().getContent().values().stream())
                .toList();

        programStates.get(0).getHeapTable().setContent(
                this.safeGarbageCollector(
                        this.getAddrFromSymTable(addresses),
                        this.getAddrFromHeap(programStates.get(0).getHeapTable().getContent()),
                        programStates.get(0)
                )
        );
        //programStates = removeCompletedPrg(repository.getProgramList());
        executor.shutdownNow();
        //repository.setProgramStates(programStates);
    }

    public void allSteps(int displayFlag) throws ExpressionEvaluationException, StatementExecutionException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<ProgramState> programStates = removeCompletedProgram(repository.getProgramList());
        while (!programStates.isEmpty()) {

            oneStepForAllPrograms(programStates);
            //remove the completed programs
            programStates = removeCompletedProgram(repository.getProgramList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Program
        //and its List<ProgramState> is not empty. Note that oneStepForAllPrograms call
        //the method setProgramList of repository in order to change the repository

        //update the repository state
        repository.setProgramList(programStates);
    }

    public List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList) {
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void addProgramState(ProgramState newProgramState)
    {
        repository.add(newProgramState);
    }

    public List<ProgramState> getProgramStates(){
        return repository.getProgramList();
    }

    public void setProgramStates(List<ProgramState> programStates){
        this.repository.setProgramList(programStates);
    }
}


