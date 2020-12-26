package command;

import game.Game;

public class ExitCommand implements Command  {
    @Override
    public void execute() {
        Game.end();
    }
}
