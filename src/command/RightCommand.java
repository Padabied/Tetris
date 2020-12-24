package command;

import game.Game;

public class RightCommand implements Command {
    @Override
    public void execute() {
        Game.stepRight();
    }
}
