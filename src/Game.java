import figures.*;

import java.util.Random;

public class Game {

   private Field field;
   private Figure figure;
   private boolean isOver;
   private boolean figureStands;
   private int downLevel;
   private int leftLevel;
   private int rightLevel;

    public Game(Field field) {
        this.field = field;
    }

    public void createNewFigure() {
        int x = new Random().nextInt(4);
        int y = new Random().nextInt(2);
        switch (x) {
            case 0:
                figure = GFigure.values()[y];
                break;
            case 1:
                figure = IFigure.values()[y];
                break;
            case 2:
                figure = QFigure.values()[y];
                break;
            case 3:
                figure = TFigure.values()[y];
                break;
        }
        mergeFieldAndFigure();
    }

    private void mergeFieldAndFigure() {
        int figureLength = figure.getLength();
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 6; j < 6 + figureLength; j++) {
                    field.getField()[i][j] = figure.getBody()[i][j - 6];
                }
            }
        } catch (Exception ignored) {}
        downLevel = figure.getHigh();
        leftLevel = 6;
        rightLevel = leftLevel + figure.getLength();

        while (downLevel < 3) {
            stepDown();
        }
    }

    private boolean canStepBeDone() {
        boolean result = true;

        String[] figureLowestLevel = field.getField()[downLevel];
        String[] nextLine = field.getField()[downLevel + 1];

        for (int i = leftLevel ; i < leftLevel + figure.getLength(); i++) {
            if (figureLowestLevel[i].equals("X") && nextLine[i].equals("X")) {
                result = false;
                break;
            }
        }
        return result && (downLevel + 1) < field.getHeight() -1;   //try <=
    }

    private void stepDown() {
        //Doing step
        if (canStepBeDone()) {

            for (int i = 0; i < figure.getHigh()+1; i++) {
                String[] nextLevel = field.getField()[downLevel+1-i];
                String[] figureLowestLevel = field.getField()[downLevel-i];
                for (int j = leftLevel; j < figure.getLength() + leftLevel; j++) {
                    if (nextLevel[j].equals(" ") && figureLowestLevel[j].equals("X")) {
                        nextLevel[j] = "X";
                        figureLowestLevel[j] = " ";
                    }
                }
            }
            downLevel++;
        }
        else if (!canStepBeDone() && downLevel == 3 ) {
            isOver = true;
            System.out.println("Game Over");
            System.exit(0);
        }
        else if (!canStepBeDone() && downLevel > 3) {
            //some logic with checking full lines and score
            createNewFigure();
        }
    }

    public void start() {
        createNewFigure();
        field.printField();

        for (int i = 0; i < 100; i++) {
            stepDown();
            field.printField();
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Field field = new Field();
        Game game = new Game(field);
        game.start();
    }
}
