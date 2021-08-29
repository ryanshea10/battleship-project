public abstract class Boat {
    
    private int team;
    private Coordinates location;
    private int direction;
    private int health;
    private int strength;
    private int vision;

    public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) {

        this.team = team;
        this.location = location;
        this.direction = direction;
        this.health = health;
        this.strength = strength;
        this.vision = vision;
    }

    public int getTeam() {return team;}

    protected void setLocation(Coordinates location) {this.location = location;}

    public Coordinates getLocation() {return location;}

    protected int getDirectionVal() {return this.direction;}

    public String getDirection() {

        switch (direction) {
            case 0: return "\u2191";
            case 1: return "\u2197";
            case 2: return "\u2192";    
            case 3: return "\u2198";    
            case 4: return "\u2193";    
            case 5: return "\u2199";
            case 6: return "\u2190";
            case 7: return "\u2196";    
        }
        
        return null;       
    }

    public int getHealth() {return health;}

    public int getStrength() {return strength;}

    public int getVision() {return vision;}

    public abstract String getID();

    public abstract String act(int[] actions, World map);

    public abstract String getActions();

    public String move(World map) {

        Coordinates adjSquare = map.getAdjacentLocation(this.location, this.direction);

        if (adjSquare == null) {
            return toString() + " cannot move off the map";
        } else if (map.isLocationOccupied(adjSquare)) {
            return toString() + " cannot move to " + adjSquare.toString() + "as it is occupied";
        } else {
            String str = toString() + " moves from " + this.location.toString() + " to " + adjSquare.toString();
            map.forceSetOccupant(this, adjSquare);
            map.forceSetOccupant(null, this.location);
            this.location = adjSquare;
            return str;
        }
    }

    public String turn(int turnDir) {

        String dirString = null;
        if (turnDir == -1) {dirString = "left";}
        if (turnDir == 1) {dirString = "right";}
        this.direction += turnDir;
        if (this.direction < 0) {this.direction = 7;}
        if (this.direction > 7) {this.direction = 0;}

        switch (direction) {
            case 0: return toString() + " turned " + dirString + " now facing " + "N";
            case 1: return toString() + " turned " + dirString + " now facing " + "NE";
            case 2: return toString() + " turned " + dirString + " now facing " + "E";    
            case 3: return toString() + " turned " + dirString + " now facing " + "SE";    
            case 4: return toString() + " turned " + dirString + " now facing " + "S";    
            case 5: return toString() + " turned " + dirString + " now facing " + "SW";
            case 6: return toString() + " turned " + dirString + " now facing " + "W";
            case 7: return toString() + " turned " + dirString + " now facing " + "NW";    
        }
        
        return null;  
    }

    public String takeHit(int strength) {

        this.health -= strength;
        
        if (this.health <= 0) {
            this.health = 0;
            return toString() + " has been sunk!";
        } else {
            return toString() + " takes " + strength + " damage.";
        }
    }

    public String toString() {return "" + getID();}
}
