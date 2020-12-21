package game;

import command.CommandExecutor;
import command.Operation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

    @Override
    public void keyTyped(KeyEvent e) {
        Operation operation = Operation.getOperation(e.getKeyCode());
        if (operation != null) {
            CommandExecutor.execute(operation);
        }
    }
}
