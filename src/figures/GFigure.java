package figures;

public enum GFigure implements Figure {


    ONE(new String[][] {
             new String[]      {"X", " "},
             new String[]      {"X", " "},
             new String[]      {"X", "X"}}),

    TWO(new String[][] {
            new String[]      {"X", "X", "X"},
            new String[]      {"X", " ", " "}}),

    THREE(new String[][] {
            new String[]      {"X", "X"},
            new String[]      {" ", "X"},
            new String[]      {" ", "X"}}),

    FOUR(new String[][] {
            new String[]      {" ", " ", "X"},
            new String[]      {"X", "X", "X"}});


    public Figure turn() {
        switch (this.name()) {
            case "ONE":
                return GFigure.TWO;
            case "TWO":
                return GFigure.THREE;
            case "THREE":
                return GFigure.FOUR;
            case "FOUR":
                return GFigure.ONE;
        }
        return this;
    }

    private String[][] body;

    @Override
    public String[][] getBody() {
        return body;
    }

    GFigure(String[][] body) {
        this.body = body;
    }
}
