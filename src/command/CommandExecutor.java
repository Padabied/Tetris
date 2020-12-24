package command;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private static Map<Operation, Command> commands = new HashMap<>();
    private  JFrame frame;

    public CommandExecutor(JFrame frame) {
        this.frame = frame;
    }

    static {
        commands.put(Operation.DOWN, new DownCommand());
        commands.put(Operation.EXIT, new ExitCommand());
        commands.put(Operation.LEFT, new LeftCommand());
        commands.put(Operation.RIGHT, new RightCommand());
        commands.put(Operation.TURN, new TurnCommand());
    }

    public  void execute(Operation operation) {
        commands.get(operation).execute();
        frame.repaint();
    }
}
