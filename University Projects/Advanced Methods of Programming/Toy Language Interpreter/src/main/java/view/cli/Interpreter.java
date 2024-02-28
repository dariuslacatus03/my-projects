package view.cli;


import controller.exceptions.TypeCheckerException;
import model.statements.*;
import model.statements.filesStmts.*;
import model.statements.heapStmts.*;
import model.adts.*;
import model.programState.ProgramState;
import model.types.*;
import model.values.*;
import model.expressions.*;
import controller.*;
import repository.*;
import view.cli.commands.ExitCommand;
import view.cli.commands.RunExample;
import view.gui.ProgramExecutorController;

public class Interpreter {

    public static void main(String[] args) {
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

        IStmt example12 = new CompoundStmt(new VariableDeclarationStmt("v1", new RefType(new IntType())), new CompoundStmt(new VariableDeclarationStmt("v2", new RefType(new IntType())), new CompoundStmt(new VariableDeclarationStmt("v3", new RefType(new IntType())), new CompoundStmt(new VariableDeclarationStmt("cnt", new IntType()), new CompoundStmt(new NewStmt("v1", new ValueExpression(new IntValue(2))), new CompoundStmt(new NewStmt("v2", new ValueExpression(new IntValue(3))), new CompoundStmt(new NewStmt("v3", new ValueExpression(new IntValue(4))), new CompoundStmt(new LatchStatement("cnt", new ReadHeapExpression(new VariableExpression("v2"))), new CompoundStmt(new ForkStmt(new WriteToHeapStmt("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10))))), new CompoundStmt(new PrintStmt(new ReadHeapExpression(new VariableExpression("v1"))), new CompoundStmt(new CountDownStatement("cnt"), new CompoundStmt(new ForkStmt(new WriteToHeapStmt("v2", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10))))), new CompoundStmt(new PrintStmt(new ReadHeapExpression(new VariableExpression("v2"))), new CompoundStmt(new CountDownStatement("cnt"), new CompoundStmt(new ForkStmt(new WriteToHeapStmt("v3", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10))))), new CompoundStmt(new PrintStmt(new ReadHeapExpression(new VariableExpression("v3"))), new CompoundStmt(new CountDownStatement("cnt"), new CompoundStmt(new AwaitStatement("cnt"), new CompoundStmt(new PrintStmt(new ValueExpression(new IntValue(100))), new CompoundStmt(new CountDownStatement("cnt"), new PrintStmt(new ValueExpression(new IntValue(100)))))))))))))))))))))));


        MyIDictionary<String, Type> typeEnv1 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv2 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv3 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv4 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv5 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv6 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv7 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv8 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv9 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv10 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv11 = new MyDictionary<String, Type>();
        MyIDictionary<String, Type> typeEnv12 = new MyDictionary<String, Type>();

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

//        //------------------------------------------------------------------------------------
//        try {
//            example1.typeCheck(typeEnv1);
//            ProgramState st1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), new MyLatchTable(),  example1);
//            Repository repo1 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c1 = new Controller(repo1);
//            c1.addProgramState(st1);
//            menu.addCommand(new RunExample("1", example1.toString(), c1));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example2.typeCheck(typeEnv2);
//            ProgramState st2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example2);
//            Repository repo2 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c2 = new Controller(repo2);
//            c2.addProgramState(st2);
//            menu.addCommand(new RunExample("2", example2.toString(), c2));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example3.typeCheck(typeEnv3);
//            ProgramState st3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example3);
//            Repository repo3 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c3 = new Controller(repo3);
//            c3.addProgramState(st3);
//            menu.addCommand(new RunExample("3", example3.toString(), c3));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example4.typeCheck(typeEnv4);
//            ProgramState st4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example4);
//            Repository repo4 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c4 = new Controller(repo4);
//            c4.addProgramState(st4);
//            menu.addCommand(new RunExample("4", example4.toString(), c4));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example5.typeCheck(typeEnv5);
//            ProgramState st5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example5);
//            Repository repo5 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c5 = new Controller(repo5);
//            c5.addProgramState(st5);
//            menu.addCommand(new RunExample("5", example5.toString(), c5));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example6.typeCheck(typeEnv1);
//            ProgramState st6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example6);
//            Repository repo6 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c6 = new Controller(repo6);
//            c6.addProgramState(st6);
//            menu.addCommand(new RunExample("6", example6.toString(), c6));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example7.typeCheck(typeEnv7);
//            ProgramState st7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example7);
//            Repository repo7 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c7 = new Controller(repo7);
//            c7.addProgramState(st7);
//            menu.addCommand(new RunExample("7", example7.toString(), c7));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example8.typeCheck(typeEnv8);
//            ProgramState st8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example8);
//            Repository repo8 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c8 = new Controller(repo8);
//            c8.addProgramState(st8);
//            menu.addCommand(new RunExample("8", example8.toString(), c8));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example9.typeCheck(typeEnv9);
//            ProgramState st9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example9);
//            Repository repo9 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c9 = new Controller(repo9);
//            c9.addProgramState(st9);
//            menu.addCommand(new RunExample("9", example9.toString(), c9));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
//        try {
//            example10.typeCheck(typeEnv10);
//            ProgramState st10 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
//                    new MyDictionary<>(), new MyHeapTable<>(), example10);
//            Repository repo10 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
//            Controller c10 = new Controller(repo10);
//            c10.addProgramState(st10);
//            menu.addCommand(new RunExample("10", example10.toString(), c10));
//
//        }
//        catch (TypeCheckerException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        //------------------------------------------------------------------------------------
        try {
            example11.typeCheck(typeEnv11);
            ProgramState st11 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeapTable<>(), new MyLatchTable(), example11);
            Repository repo11 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
            Controller c11 = new Controller(repo11);
            c11.addProgramState(st11);
            menu.addCommand(new RunExample("11", example11.toString(), c11));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //------------------------------------------------------------------------------------
        try {
            example12.typeCheck(typeEnv12);
            ProgramState st12 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeapTable<>(), new MyLatchTable(), example12);
            Repository repo12 = new Repository("C:\\Users\\dariu\\Desktop\\a\\Facultate\\An_2\\Metode_Avansate_de_Programare\\Labs\\A7\\src\\logFile.txt");
            Controller c12 = new Controller(repo12);
            c12.addProgramState(st12);
            menu.addCommand(new RunExample("12", example12.toString(), c12));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //------------------------------------------------------------------------------------

        menu.show();
    }
}
