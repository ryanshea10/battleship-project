import java.util.Random;

public class Submarine extends ScoutBoat implements Attacker {

    private int numOfTorpedoes;

    public Submarine(int team, Coordinates location, int direction, int numOfTorpedoes) {

        super(team, location, direction, 3, 2);
        this.numOfTorpedoes = numOfTorpedoes;
    }

    public String getID() {return "S" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Submarine:\n";
        str += "1. Move\n2. Turn left\n3. Turn right\n4. Submerge";
        str += (numOfTorpedoes > 0) ? "\n5. Fire torpedoes" : "";
        return str;
    }

    public String act(int[] actions, World map) {

        int action = actions[0];
        String str = "";

        if (action == 1) {str += move(map);}
        else if (action == 2) {str += turn(-1);}
        else if (action == 3) {str += turn(1);}
        else if (action == 4) {str += submerge(map);}
        else if (action == 5) {str += attack(map);}

        return str;
    }

    public Coordinates generateSquare(World map) {

        int currentRow = getLocation().getY();
        int currentCol = getLocation().getX();

        Random rand = new Random();
        
        int newRow = rand.nextInt(map.getHeight());
        int newCol = rand.nextInt(map.getWidth());

        Coordinates coord = new Coordinates(newCol, newRow);

        if (Math.abs(newRow - currentRow) > 2 && Math.abs(newCol - currentCol) > 2 && !map.isLocationOccupied(coord)) {
            return coord;
        } else {
            return generateSquare(map);
        }
    }

    public String submerge(World map) {

        String str = getID() + " moves from " + getLocation().toString() + " to ";
        Coordinates newSquare = generateSquare(map);
        map.forceSetOccupant(this, newSquare);
        map.forceSetOccupant(null, getLocation());
        setLocation(newSquare);

        return str += newSquare.toString();
    }

    public String attack(World map) {
        
        if (this.numOfTorpedoes <= 0) {return getID() + " has no torpedoes remaining.";}

        Coordinates attackSquare = map.getAdjacentLocation(getLocation(), getDirectionVal());

        int boatX   = getLocation().getX();
        int boatY   = getLocation().getY();
        
        while (true) {
            int attackX = attackSquare.getX();
            int attackY = attackSquare.getY();

            if  (!map.isLocationValid(attackSquare) ||  Math.abs(attackX - boatX) > getVision() || 
            Math.abs(attackY - boatY) > getVision()) {
                return "There are no boats in range currently.";
            } else if (map.getOccupant(attackSquare) != null) {
                Random rand = new Random();
                Boat occupant = map.getOccupant(attackSquare);
                return "Fire torpedoes!" + occupant.takeHit(rand.nextInt(occupant.getHealth()) + 1);
            } else {
                attackSquare = map.getAdjacentLocation(attackSquare, getDirectionVal());
            }
        }
    }
}
