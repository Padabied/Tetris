package command;

import game.Game;

public class LeftCommand implements Command {
    @Override
    public void execute() {

        Game.getField().printField();
    }
}
