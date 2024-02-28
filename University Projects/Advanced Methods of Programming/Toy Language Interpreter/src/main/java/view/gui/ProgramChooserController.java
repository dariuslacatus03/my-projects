package view.gui;

import controller.Controller;
import controller.exceptions.*;
//import model.statements.SemaphoreStatements.AcquireSempahoreStatement;
//import model.statements.SemaphoreStatements.NewSemaphoreStatement;
//import model.statements.SemaphoreStatements.ReleaseSemaphoreStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.adts.*;
import model.expressions.*;
import model.programState.ProgramState;
import model.statements.*;
import model.statements.filesStmts.CloseFileStmt;
import model.statements.filesStmts.OpenReadFileStmt;
import model.statements.filesStmts.ReadFileStmt;
import model.statements.heapStmts.NewStmt;
import model.statements.heapStmts.WriteToHeapStmt;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.IRepository;
import repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramChooserController {
    private ProgramExecutorController programExecutorController;

    public void setProgramExecutorController(ProgramExecutorController programExecutorController) {
        this.programExecutorController = programExecutorController;
    }
    @FXML
    private ListView<IStmt> programsListView;

    @FXML
    private Button displayButton;

    @FXML
    public void initialize() {
        programsListView.setItems(getAllStatements());
        programsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void displayProgram(ActionEvent actionEvent) {
        IStmt selectedStatement = programsListView.getSelectionModel().getSelectedItem();
        if (selectedStatement == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error encountered!");
            alert.setContentText("No Statements selected!");
            alert.showAndWait();
        } else {
            int id = programsListView.getSelectionModel().getSelectedIndex();
            try {
                selectedStatement.typeCheck(new MyDictionary<>());
                ProgramState programState = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeapTable<>(), new MyLatchTable(), selectedStatement);
                IRepository repository = new Repository("src/logFile.txt");
                Controller controller = new Controller(repository);
                controller.addProgramState(programState);
                programExecutorController.setController(controller);
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error encountered!");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private ObservableList<IStmt> getAllStatements() {
        List<IStmt> allStatements = new ArrayList<>();

        IStmt example1 = new CompoundStmt(new VariableDeclarationStmt("v", new IntType()),
                new CompoundStmt(new AssignmentStmt("v", new ValueExpression(new IntValue(2))),
                        new PrintStmt(new VariableExpression("v"))));


        IStmt example2 = new CompoundStmt(new VariableDeclarationStmt("a", new IntType()),
                new CompoundStmt(new VariableDeclarationStmt("b", new IntType()),
                        new CompoundStmt(new AssignmentStmt("a",
                                new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression('*', new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5))))),
                                new CompoundStmt(new AssignmentStmt("b", new ArithmeticExpression('+',
                                        new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStmt(new VariableExpression("b"))))));
        IStmt example3 = new CompoundStmt(new VariableDeclarationStmt("a", new BoolType()),
                new CompoundStmt(new VariableDeclarationStmt("v", new IntType()),
                        new CompoundStmt(new AssignmentStmt("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStmt(new IfStmt(new RelationalExpression(new ValueExpression(new IntValue(5)),
                                        new ValueExpression(new IntValue(5)), "=="),
                                        new AssignmentStmt("v", new ValueExpression(new IntValue(2))),
                                        new AssignmentStmt("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStmt(new VariableExpression("v"))))));

        IStmt example4 = new CompoundStmt(new VariableDeclarationStmt("varf", new StringType()),
                new CompoundStmt(new AssignmentStmt("varf", new ValueExpression(new StringValue
                        ("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\Lab5\\test.in"))),
                        new CompoundStmt(new OpenReadFileStmt(new VariableExpression("varf")), new CompoundStmt
                                (new VariableDeclarationStmt("varc", new IntType()), new CompoundStmt
                                        (new ReadFileStmt(new VariableExpression("varf"), "varc"), new CompoundStmt
                                                (new PrintStmt(new VariableExpression("varc")), new CompoundStmt
                                                        (new ReadFileStmt(new VariableExpression("varf"), "varc"),
                                                                new CompoundStmt(new PrintStmt(new VariableExpression("varc")),
                                                                        new CloseFileStmt(new VariableExpression("varf"))))))))));


        IStmt example5 = new CompoundStmt(new VariableDeclarationStmt("v", new RefType(new IntType())),
                new CompoundStmt(new NewStmt("v", new ValueExpression(new IntValue(20))),
                        new CompoundStmt(new VariableDeclarationStmt("a",
                                new RefType(new RefType(new IntType()))), new CompoundStmt(new NewStmt("a",
                                new VariableExpression("v")), new CompoundStmt(new PrintStmt(new VariableExpression("v")),
                                new PrintStmt(new VariableExpression("a")))))));

        IStmt example6 = new CompoundStmt(new VariableDeclarationStmt("v", new RefType(new IntType())),
                new CompoundStmt(new NewStmt("v", new ValueExpression(new IntValue(20))),
                        new CompoundStmt(new VariableDeclarationStmt("a",
                                new RefType(new RefType(new IntType()))), new CompoundStmt(new NewStmt("a",
                                new VariableExpression("v")), new CompoundStmt(new PrintStmt(new ReadHeapExpression
                                (new VariableExpression("v"))), new PrintStmt(new ArithmeticExpression('+',
                                new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                                new ValueExpression(new IntValue(5)))))))));

        IStmt example7 = new CompoundStmt(new VariableDeclarationStmt("v", new RefType(new IntType())),
                new CompoundStmt(new NewStmt("v", new ValueExpression(new IntValue(20))),
                        new CompoundStmt(new PrintStmt(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStmt(new WriteToHeapStmt("v", new
                                        ValueExpression(new IntValue(30))), new PrintStmt(new
                                        ArithmeticExpression('+', new ReadHeapExpression(new
                                        VariableExpression("v")), new ValueExpression(new IntValue(5))))))));

        IStmt example8 = new CompoundStmt(new VariableDeclarationStmt("v", new RefType(new IntType())),
                new CompoundStmt(new NewStmt("v", new ValueExpression(new IntValue(20))),
                        new CompoundStmt(new VariableDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompoundStmt(new NewStmt("v", new ValueExpression(new IntValue(25))),
                                        new CompoundStmt(new NewStmt("v", new ValueExpression(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExpression(new
                                                        ReadHeapExpression(new VariableExpression("a")))))))));

        IStmt example9 = new CompoundStmt(new VariableDeclarationStmt("v", new IntType()),
                new CompoundStmt(new AssignmentStmt("v", new ValueExpression(new StringValue("4"))),
                        new CompoundStmt(new WhileStmt(new RelationalExpression(new VariableExpression("v"),
                                new ValueExpression(new IntValue(0)),">"),
                                new CompoundStmt(new PrintStmt(new VariableExpression("v")), new AssignmentStmt("v",
                                        new ArithmeticExpression('-', new VariableExpression("v"),
                                                new ValueExpression(new IntValue(1)))))), new PrintStmt(new VariableExpression("v")))));

        IStmt example10 = new CompoundStmt(new VariableDeclarationStmt("v", new IntType()),
                new CompoundStmt(new VariableDeclarationStmt("a", new RefType(new IntType())),
                        new CompoundStmt(new AssignmentStmt("v", new ValueExpression(new IntValue(10))),
                                new CompoundStmt(new NewStmt("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStmt(new ForkStmt(new CompoundStmt(new WriteToHeapStmt("a",
                                                new ValueExpression(new IntValue(30))), new CompoundStmt
                                                (new AssignmentStmt("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStmt(new PrintStmt(new VariableExpression("v")),
                                                                new PrintStmt(new ReadHeapExpression(new VariableExpression("a"))))))),
                                                new CompoundStmt(new PrintStmt(new VariableExpression("v")), new PrintStmt(new ReadHeapExpression
                                                        (new VariableExpression("a")))))))));

        IStmt example11 = new CompoundStmt(new VariableDeclarationStmt("a", new RefType(new IntType())), new CompoundStmt(new VariableDeclarationStmt("b", new RefType(new IntType())), new CompoundStmt(new VariableDeclarationStmt("v", new IntType()),
                new CompoundStmt(new NewStmt("a", new ValueExpression(new IntValue(0))), new CompoundStmt(new NewStmt("b", new ValueExpression(new IntValue(0))), new CompoundStmt(new WriteToHeapStmt("a", new ValueExpression(new IntValue(1))), new CompoundStmt(new WriteToHeapStmt("b", new ValueExpression(new IntValue(2))), new CompoundStmt(new ConditionalAssignmentStatement("v", new RelationalExpression(new ReadHeapExpression(new VariableExpression("a")), new ReadHeapExpression(new VariableExpression("b")), "<"), new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200))),
                        new CompoundStmt(new PrintStmt(new VariableExpression("v")), new CompoundStmt(new ConditionalAssignmentStatement("v", new RelationalExpression(new ArithmeticExpression('-', new ReadHeapExpression(new VariableExpression("b")), new ValueExpression(new IntValue(2))),new ReadHeapExpression(new VariableExpression("a")), ">" ), new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200))), new PrintStmt(new VariableExpression("v"))))))))))));


        IStmt example12 = new CompoundStmt(
                new VariableDeclarationStmt("v1", new RefType(new IntType())),
                new CompoundStmt(
                        new VariableDeclarationStmt("v2", new RefType(new IntType())),
                        new CompoundStmt(
                                new VariableDeclarationStmt("v3", new RefType(new IntType())),
                                new CompoundStmt(
                                        new VariableDeclarationStmt("cnt", new IntType()),
                                        new CompoundStmt(
                                                new NewStmt("v1", new ValueExpression(new IntValue(2))),
                                                new CompoundStmt(
                                                        new NewStmt("v2", new ValueExpression(new IntValue(3))),
                                                        new CompoundStmt(
                                                                new NewStmt("v3", new ValueExpression(new IntValue(4))),
                                                                new CompoundStmt(
                                                                        new LatchStatement("cnt", new ReadHeapExpression(new VariableExpression("v2"))),
                                                                        new CompoundStmt(
                                                                                new ForkStmt(
                                                                                        new CompoundStmt(
                                                                                                new WriteToHeapStmt("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                                                                                                new CompoundStmt(
                                                                                                        new PrintStmt(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                                        new CountDownStatement("cnt")
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompoundStmt(
                                                                                        new ForkStmt(
                                                                                                new CompoundStmt(
                                                                                                        new WriteToHeapStmt("v2", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)))),
                                                                                                        new CompoundStmt(
                                                                                                                new PrintStmt(new ReadHeapExpression(new VariableExpression("v2"))),
                                                                                                                new CountDownStatement("cnt")
                                                                                                        )
                                                                                                )
                                                                                        ),
                                                                                        new CompoundStmt(
                                                                                                new ForkStmt(
                                                                                                        new CompoundStmt(
                                                                                                                new WriteToHeapStmt("v3", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)))),
                                                                                                                new CompoundStmt(
                                                                                                                        new PrintStmt(new ReadHeapExpression(new VariableExpression("v3"))),
                                                                                                                        new CountDownStatement("cnt")
                                                                                                                )
                                                                                                        )
                                                                                                ),
                                                                                                new CompoundStmt(
                                                                                                        new AwaitStatement("cnt"),
                                                                                                        new CompoundStmt(
                                                                                                                new PrintStmt(new ValueExpression(new IntValue(100))),
                                                                                                                new CompoundStmt(
                                                                                                                        new CountDownStatement("cnt"),
                                                                                                                        new PrintStmt(new ValueExpression(new IntValue(100)))
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )

                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        allStatements.add(example1);
        allStatements.add(example2);
        allStatements.add(example3);
        allStatements.add(example4);
        allStatements.add(example5);
        allStatements.add(example6);
        allStatements.add(example7);
        allStatements.add(example8);
        allStatements.add(example9);
        allStatements.add(example10);
        allStatements.add(example11);
        allStatements.add(example12);

//        Statement ex12 =
//                new CompoundStatement(
//                        new DeclarationStatement("a", new IntType()),
//                        new CompoundStatement(
//                                new DeclarationStatement("b", new IntType()),
//                                new CompoundStatement(
//                                        new DeclarationStatement("c", new IntType()),
//                                        new CompoundStatement(
//                                                new AssignmentStatement("a", new ValueExpression(new IntValue(1))),
//                                                new CompoundStatement(
//                                                        new AssignmentStatement("b", new ValueExpression(new IntValue(2))),
//                                                        new CompoundStatement(
//                                                                new AssignmentStatement("c", new ValueExpression(new IntValue(5))),
//                                                                new CompoundStatement(
//                                                                        new SwitchStatement(
//                                                                                new ArithmeticExpression('*', new VariableExpression("a"), new ValueExpression(new IntValue(10))),
//                                                                                new ArithmeticExpression('*', new VariableExpression("b"), new VariableExpression("c")),
//                                                                                new ValueExpression(new IntValue(10)),
//                                                                                new CompoundStatement(
//                                                                                        new PrintStatement(new VariableExpression("a")),
//                                                                                        new PrintStatement(new VariableExpression("b"))
//                                                                                ),
//                                                                                new CompoundStatement(
//                                                                                        new PrintStatement(new ValueExpression(new IntValue(100))),
//                                                                                        new PrintStatement(new ValueExpression(new IntValue(200)))
//                                                                                ),
//                                                                                new PrintStatement(new ValueExpression(new IntValue(300)))
//                                                                        ),
//                                                                        new PrintStatement(new ValueExpression(new IntValue(300)))
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                );
//
//        allStatements.add(ex12);
//
//        Statement ex13 =
//                new CompoundStatement(
//                        new DeclarationStatement("v1", new RefType(new IntType())),
//                        new CompoundStatement(
//                                new DeclarationStatement("cnt", new IntType()),
//                                new CompoundStatement(
//                                        new NewStatement("v1", new ValueExpression(new IntValue(2))),
//                                        new CompoundStatement(
//                                                new NewSemaphoreStatement("cnt", new ReadHeapExpression(new VariableExpression("v1"))),
//                                                new CompoundStatement(
//                                                        new ForkStatement(
//                                                                new CompoundStatement(
//                                                                        new AcquireSempahoreStatement("cnt"),
//                                                                        new CompoundStatement(
//                                                                                new WriteHeapStatement("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
//                                                                                new CompoundStatement(
//                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
//                                                                                        new ReleaseSemaphoreStatement("cnt")
//                                                                                )
//                                                                        )
//
//                                                                )
//                                                        ),
//                                                        new CompoundStatement(
//                                                                new ForkStatement(
//                                                                        new CompoundStatement(
//                                                                                new AcquireSempahoreStatement("cnt"),
//                                                                                new CompoundStatement(
//                                                                                        new WriteHeapStatement("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
//                                                                                        new CompoundStatement(
//                                                                                                new WriteHeapStatement("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(2)))),
//                                                                                                new CompoundStatement(
//                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
//                                                                                                        new ReleaseSemaphoreStatement("cnt")
//                                                                                                )
//                                                                                        )
//                                                                                )
//                                                                        )
//
//                                                                ),
//                                                                new CompoundStatement(
//                                                                        new AcquireSempahoreStatement("cnt"),
//                                                                        new CompoundStatement(
//                                                                                new PrintStatement(new ArithmeticExpression('-', new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1)))),
//                                                                                new ReleaseSemaphoreStatement("cnt")
//                                                                        )
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                );
//        allStatements.add(ex13);


        return FXCollections.observableArrayList(allStatements);
    }
}
