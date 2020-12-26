package game;
import command.CommandExecutor;
import command.Operation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

    private CommandExecutor executor;

    public Controller(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Operation operation = Operation.getOperation(e.getKeyCode());
        if (operation != null) {
            executor.execute(operation);
        }
    }
}
