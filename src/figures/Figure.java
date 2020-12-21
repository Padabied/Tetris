package figures;

public interface Figure {

    Figure turn();
    default int getHigh() {
        return this.getBody().length-1;
    }
    default int getLength() {
        return this.getBody()[0].length;
    }
    String[][] getBody();

}
