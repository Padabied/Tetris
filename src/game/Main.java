package game;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        Field field = Game.getField();
        field.printField();
        Game.createNewFigure();

        while (true) {
            Game.getField().printField();
            Thread.sleep(1000);
            Game.stepDown();
        }
    }

}
