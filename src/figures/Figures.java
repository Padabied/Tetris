package figures;

import java.util.ArrayList;
import java.util.List;

//This class is used in turn figure methods
public class Figures {

    //Returns the matrix of the figure with considering of place that figure requires to be turned
    public static String[][] getMatrix(Figure figure) {
        String figureName = figure.getClass().getSimpleName();
        String[][] result = null;
        switch (figureName) {
            case "IFigure" :
                if (figure == IFigure.ONE) {
                    result = new String[][] {
                                              new String[] {"X", " ", " ", " "},
                                              new String[] {"X", " ", " ", " "},
                                              new String[] {"X", " ", " ", " "},
                                              new String[] {"X", " ", " ", " "}};
                    break;
                }
                if (figure == IFigure.TWO) {
                    result = new String[][] {
                                              new String[] {" ", " ", " ", " "},
                                              new String[] {" ", " ", " ", " "},
                                              new String[] {" ", " ", " ", " "},
                                              new String[] {"X", "X", "X", "X"}};
                    break;
                }

            case "GFigure" :
                if (figure == GFigure.ONE) {
                    result = new String[][] {
                                               new String[] {"X", " ", " "},
                                               new String[] {"X", " ", " "},
                                               new String[] {"X", "X", " "}};
                    break;
                }
                if (figure == GFigure.TWO) {
                    result = new String[][] {
                                               new String[] {" ", " ", " "},
                                               new String[] {"X", "X", "X"},
                                               new String[] {"X", " ", " "}};
                    break;
                }
                if (figure == GFigure.THREE) {
                    result = new String[][] {
                                               new String[] {"X", "X", " "},
                                               new String[] {" ", "X", " "},
                                               new String[] {" ", "X", " "}};
                    break;
                }

                if (figure == GFigure.FOUR) {
                    result = new String[][] {
                                               new String[] {" ", " ", " "},
                                               new String[] {" ", " ", "X"},
                                               new String[] {"X", "X", "X"}};
                    break;
                }

            case "TFigure" :
                if (figure == TFigure.ONE) {
                    result = new String[][] {
                                               new String[] {" ", " ", " "},
                                               new String[] {"X", "X", "X"},
                                               new String[] {" ", "X", " "}};
                    break;
                }

                if (figure == TFigure.TWO) {
                    result = new String[][] {
                                               new String[] {" ", "X", " "},
                                               new String[] {"X", "X", " "},
                                               new String[] {" ", "X", " "}};
                    break;
                }

                if (figure == TFigure.THREE) {
                    result = new String[][] {
                                               new String[] {" ", " ", " "},
                                               new String[] {" ", "X", " "},
                                               new String[] {"X", "X", "X"}};
                    break;
                }
                if (figure == TFigure.FOUR) {
                    result = new String[][] {
                                               new String[] {"X", " ", " "},
                                               new String[] {"X", "X", " "},
                                               new String[] {"X", " ", " "}};
                    break;
                }

            case "ZFigure" :
                if (figure == ZFigure.ONE) {
                    result = new String[][] {
                                               new String[] {" ", " ", " "},
                                               new String[] {"X", "X", " "},
                                               new String[] {" ", "X", "X"}};
                    break;
                }
                if (figure == ZFigure.TWO) {
                    result = new String[][] {
                                               new String[] {" ", "X", " "},
                                               new String[] {"X", "X", " "},
                                               new String[] {"X", " ", " "}};
                    break;
                }
        }
        return result;
    }

    //Returns common cells indexes of current and turned figure matrix (taken from getMatrix() method)
    public static List<String> getIndexesOfCommonCellsOfCurrentAndTurnedFigure(Figure currentFigure) {
        List<String> result = new ArrayList<>();
        String[][] currentFigureMatrix = getMatrix(currentFigure);
        String[][] turnedFigureMatrix = getMatrix(currentFigure.turn());

        for (int i = 0; i < currentFigureMatrix.length; i++) {
            for (int j = 0; j < currentFigureMatrix[0].length; j++) {
                if (currentFigureMatrix[i][j].equals("X") && turnedFigureMatrix[i][j].equals("X")) {
                    String index = String.valueOf(i) + j;
                    result.add(index);
                }
            }
        }
        return result;
    }

    //Returns indexes of cells to be converted from " " to "X" while turning the figure
    public static List<String> getIndexesOfCellsToTurn(Figure currentFigure) {
        List<String> result = new ArrayList<>();
        List<String> commonCells = Figures.getIndexesOfCommonCellsOfCurrentAndTurnedFigure(currentFigure);
        String[][] currentFigureMatrix = getMatrix(currentFigure);
        String[][] turnedFigureMatrix = getMatrix(currentFigure.turn());

        for (int i = 0; i < currentFigureMatrix.length; i++) {
            for (int j = 0; j < currentFigureMatrix[0].length; j++) {
                String index = String.valueOf(i) + j;
                if (!commonCells.contains(index)) {
                    if (turnedFigureMatrix[i][j].equals("X")) {
                        result.add(index);
                    }
                }
            }
        }
        return result;
    }

    //Returns indexes of cells to be converted from "X" to " " while turning the figure
    public static List<String> getIndexesOfCellsToNull(Figure currentFigure) {
        List<String> result = new ArrayList<>();
        List<String> commonCells = getIndexesOfCommonCellsOfCurrentAndTurnedFigure(currentFigure);
        String[][] current = getMatrix(currentFigure);
        String[][] turned = getMatrix(currentFigure.turn());

        String index;
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[0].length; j++) {
                index = String.valueOf(i) + j;
                if (!commonCells.contains(index)) {
                    if (current[i][j].equals("X") && turned[i][j].equals(" ")) {
                        result.add(index);
                    }
                }
            }
        }
        return result;
    }
}
