public class World {
    
    private Boat[][] map;
    public static final int NORTH     = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST      = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH     = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST      = 6;
    public static final int NORTHWEST = 7;

    public World(int width, int height) {

        width  = (width >= 4) ? width : 4;
        width  = (width <= 10) ? width : 10;
        height = (height >= 4) ? height : 4;
        height = (height <= 10) ? height : 10;

        map = new Boat[height][width];
    }

    public int getWidth() {return map[0].length;}

    public int getHeight() {return map.length;}

    public Boat getOccupant(Coordinates coord) {return map[coord.getY()][coord.getX()];}

    public boolean isLocationValid(Coordinates coord) {

        if (coord.getX() >= 0 && coord.getX() < getWidth() && coord.getY() >= 0 && coord.getY() < getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLocationOccupied(Coordinates coord) {

        boolean bool = (getOccupant(coord) != null) ? true : false;
        return bool;
    }

    public boolean setOccupant(Boat b, Coordinates coord) {

        if (!isLocationOccupied(coord)) {
            map[coord.getY()][coord.getX()] = b;
            return true;
        } else {
            return false;
        }
    }

    public boolean forceSetOccupant(Boat b, Coordinates coord) {

        map[coord.getY()][coord.getX()] = b;
        return true;
    }

    public Coordinates getAdjacentLocation(Coordinates coord, int direction) {

        int[] dirArray = new int[2];
        
        switch (direction) {
            case 0:
                dirArray[0] = -1; dirArray[1] = 0;
                break;
            case 1:
                dirArray[0] = -1; dirArray[1] = 1;
                break;
            case 2:
                dirArray[0] = 0; dirArray[1] = 1;
                break;
            case 3:
                dirArray[0] = 1; dirArray[1] = 1;
                break;
            case 4:
                dirArray[0] = 1; dirArray[1] = 0;
                break;
            case 5:
                dirArray[0] = 1; dirArray[1] = -1;
                break;
            case 6:
                dirArray[0] = 0; dirArray[1] = -1;
                break;
            case 7:
                dirArray[0] = -1; dirArray[1] = -1;
                break;
        }

        Coordinates c = new Coordinates(coord.getX()+dirArray[1], coord.getY()+dirArray[0]);
        return (isLocationValid(c)) ? c : null;
    }

    private String drawFirstRow() {

        String str = "";
        for (int i = 1; i <= getWidth(); i++) {str += " " + i + " ";}
        return str;
    }

    // set flag for whether the square is visible
    private boolean getVisibility(Boat[] boats, int view, int row, int col) {
        
        if (view == 1) {
            return false;
        } else {
            for (Boat boat : boats) {
                int y = boat.getLocation().getY();
                int x = boat.getLocation().getX();
                if (Math.abs(row - y) <= boat.getVision() && Math.abs(col - x) <= boat.getVision()) {
                    return true;
                } // else {
                //     return false;
                // }
           }
        }
        return false;
    }

    public String drawTeamMap(Boat[] boats, int view) {

        boolean visible;
        String mapStr = "@ " + drawFirstRow() + "\n";
    
        for (int row = 0; row < getHeight(); row++) {
            mapStr += (char)(65 + row) + " ";
            for (int col = 0; col < getWidth(); col++) {
                Coordinates squareCoord = new Coordinates(col, row);
                visible = getVisibility(boats, view, row, col);
                if (!visible) {
                    mapStr += "###";
                } else if (!isLocationOccupied(squareCoord)) {
                    mapStr += "~~~";
                } else if (view == 2) {
                    mapStr += getOccupant(squareCoord).getDirection() + getOccupant(squareCoord).getID();
                } else if (view == 3) {
                    mapStr += getOccupant(squareCoord).getHealth() + getOccupant(squareCoord).getID();
                }
            }
            mapStr += "\n";
        }

        return mapStr;
    }
}

