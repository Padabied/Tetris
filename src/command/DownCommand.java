package command;

import game.Field;
import game.Game;

public class DownCommand implements Command {
    @Override
    public void execute() {
        Game.stepDown();
    }
}
