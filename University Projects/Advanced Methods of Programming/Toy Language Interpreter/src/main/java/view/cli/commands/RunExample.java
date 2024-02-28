package view.cli.commands;

import controller.Controller;
import controller.exceptions.ExpressionEvaluationException;
import controller.exceptions.StatementExecutionException;

import java.io.IOException;

public class RunExample extends Command{
    private Controller ctr;
    public RunExample(String key, String description, Controller ctr)
    {
        super(key, description);
        this.ctr = ctr;
    }
    @Override
    public void execute()  {
        try {
            ctr.allSteps(0);
        }
        catch(StatementExecutionException | ExpressionEvaluationException | IOException | InterruptedException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

}
