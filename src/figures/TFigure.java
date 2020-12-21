package figures;

public enum TFigure implements Figure {

    ONE(new String[][] {
                new String[]      {"X", "X", "X"},
                new String[]      {" ", "X", " "}}),

    TWO(new String[][] {
                new String[]      {" ", "X"},
                new String[]      {"X", "X"},
                new String[]      {" ", "X"}}),

    THREE(new String[][] {
                new String[]      {" ", "X", " "},
                new String[]      {"X", "X", "X"}}),

    FOUR(new String[][] {
                new String[]      {"X", " "},
                new String[]      {"X", "X"},
                new String[]      {"X", " "}});


    public Figure turn() {
        switch (this.name()) {
            case "ONE":
                return TFigure.TWO;
            case "TWO":
                return TFigure.THREE;
            case "THREE":
                return TFigure.FOUR;
            case "FOUR":
                return TFigure.ONE;
        }
        return this;
    }

    private String[][] body;

    TFigure(String[][] body) {
        this.body = body;
    }

    @Override
    public String[][] getBody() {
        return body;
    }

}
