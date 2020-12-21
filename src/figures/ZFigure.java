package figures;

public enum ZFigure implements Figure {

    ONE(new String[][] {
            new String[]     {"X", "X", " "},
            new String[]     {" ", "X", "X"}}
            ),
    TWO(new String[][] {
            new String[]     {" ", "X"},
            new String[]     {"X", "X"},
            new String[]     {"X", " "}});


    @Override
    public Figure turn() {
        switch (this.name()) {
            case "ONE":
                return ZFigure.TWO;
            case "TWO":
                return ZFigure.ONE;
        }
        return this;
    }

    @Override
    public String[][] getBody() {
        return this.body;
    }

    private String[][] body;

    ZFigure(String[][] body) {
        this.body = body;
    }
}
