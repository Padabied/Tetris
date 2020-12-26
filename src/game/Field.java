package game;
import java.util.Arrays;

public class Field {

    private  String[][] field = new String[24][15];

    public Field() {
        for (String[] line : field) {
            Arrays.fill(line, " ");
        }
    }

    public String[][] getField() {
        return field;
    }

    public int getLength() {
        return field[0].length;
    }

    public int getHeight() {
        return field.length;
    }
}
