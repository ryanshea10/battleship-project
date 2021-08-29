public class Battleship extends Boat implements Attacker {
    
    public Battleship(int team, Coordinates location, int direction) {

        super(team, location, direction, 4, 3, 1);
    }

    public String getID() {return "B" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Battleship:\n";
        str += "1. Move\n2. Turn left\n3. Turn right\n4. Attack";
        return str;
    }

    public String act(int[] actions, World map) {

        int action = actions[0];
        String str = "";

        if (action == 1) {str += move(map);}
        else if (action == 2) {str += turn(-1);}
        else if (action == 3) {str += turn(1);}
        else if (action == 4) {str += attack(map);}

        return str;
    }

    public String attack(World map) {

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
                Boat occupant = map.getOccupant(attackSquare);
                return "Fire cannons!" + occupant.takeHit(getStrength()) + occupant.takeHit(getStrength());
            } else {
                attackSquare = map.getAdjacentLocation(attackSquare, getDirectionVal());
            }
        }
    }

}
