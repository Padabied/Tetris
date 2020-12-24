package command;

import game.Game;

public class TurnCommand implements Command {
    @Override
    public void execute() {
        Game.turnFigure();
    }
}
