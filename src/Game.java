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
   private int upLevel;

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
        downLevel = figure.getHigh() - 1;
        leftLevel = 6;
        rightLevel = leftLevel + figure.getLength();
        upLevel = 0;

        while (downLevel < 3) {
            stepDown();
        }
    }

    private boolean canStepBeDone() {
        boolean canStepBeDone = true;

        //Checking next level
        try {
            for (int i = leftLevel; i < leftLevel + figure.getLength(); i++) {
                if (field.getField()[downLevel + 1][i].equals("X") &&
                        figure.getBody()[figure.getHigh()-1][i - leftLevel].equals("X")){
                    canStepBeDone = false;
                    break;

                }
            }
        } catch (Exception e) {
            //some logic with checking full lines
            figureStands = true;
        }
        return canStepBeDone && (downLevel + 1) < field.getHeight() -1;
    }

    private void stepDown() {
        //Doing step
        if (canStepBeDone()) {
            for (int i = 0; i < figure.getHigh(); i++) {
                for (int j = 0; j < figure.getLength(); j++) {
                    if (field.getField()[downLevel + 1][leftLevel + j].equals(" ") &&
                            figure.getBody()[figure.getHigh() - 1 - i][j].equals("X")) {
                        field.getField()[downLevel + 1][leftLevel + j] = "X";
                        field.getField()[downLevel][leftLevel + j] = " ";
                    }
                    else if (field.getField()[downLevel + 1][leftLevel + j].equals("X") &&
                            figure.getBody()[figure.getHigh() - 1 - i][j].equals(" ")) {
                        int up = (downLevel + 1) - figure.getHigh();
                        field.getField()[downLevel][leftLevel + j] = figure.getBody()[figure.getHigh()-1-i][j];
                    }
                    else {
                        field.getField()[(downLevel+1) - i][leftLevel + j] = figure.getBody()[figure.getHigh()-1-i][j];
                    }
                }
            }






//            int heightCount = downLevel + 1;
//            for (int i = 0; i < figure.getHigh(); i++) {
//                for (int j = 0; j < figure.getLength(); j++) {
//                    if (field.getField()[heightCount - i][leftLevel + j].equals(" ") &&
//                            figure.getBody()[figure.getHigh()-1-i][j].equals("X")) {
//                        field.getField()[heightCount - i][leftLevel + j] = figure.getBody()[figure.getHigh()-1-i][j];
//                    }
//                    else {
////                        field.getField()[heightCount][]
//                    }
//                }
//            }
            downLevel++;
            //changing previous line
            for (int i = leftLevel; i < leftLevel + figure.getLength(); i++) {
                field.getField()[upLevel][i] = " ";
            }
            upLevel++;
        }
        else if (!canStepBeDone() && downLevel == 3 ){
            isOver = true;
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
            try {
                Thread.sleep(5);
                System.out.println();
                stepDown();
                field.printField();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Field field = new Field();
        Game game = new Game(field);
        game.start();
    }
}
