package figures;

public enum IFigure implements Figure {

    ONE(new String[][] {
                new String[]      {"X"},
                new String[]      {"X"},
                new String[]      {"X"},
                new String[]      {"X"}}),

    TWO(new String[][] {
            new String[]      {"X", "X", "X", "X"}});


    public Figure turn() {
        switch (this.name()) {
            case "ONE":
                return IFigure.TWO;
            case "TWO":
                return IFigure.ONE;
        }
        return this;
    }

    private String[][] body;

    IFigure(String[][] body) {
        this.body = body;
    }

    @Override
    public String[][] getBody() {
        return body;
    }

}
