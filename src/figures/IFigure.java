package figures;

public enum IFigure implements Figure {

    ONE(new String[][] {
                new String[]      {"X"},
                new String[]      {"X"},
                new String[]      {"X"},
                new String[]      {"X"}}),

    TWO(new String[][] {
                new String[]      {"X", "X", "X", "X"}});


    public Figure turnRight() {
        switch (this.name()) {
            case "ONE":
                return IFigure.TWO;
            case "TWO":
                return IFigure.ONE;
        }
        return this;
    }

    public Figure turnLeft() {
      return turnRight();
    }

    private String[][] body;

    IFigure(String[][] body) {
        this.body = body;
    }

    public String[][] getBody() {
        return body;
    }

    @Override
    public int getHigh() {
        return body.length;
    }

    @Override
    public int getLength() {
        return body[0].length;
    }
}
