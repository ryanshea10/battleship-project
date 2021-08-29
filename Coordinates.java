public class Coordinates {
    
    private int x;
    private int y;

    public Coordinates() {this(0, 0);}

    public Coordinates(int x, int y) {
        
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public void setCoordinates(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public String toString() {

        char row = (char)(65 + y);
        int col = x + 1;
        return "" + row + col;
    }
}
