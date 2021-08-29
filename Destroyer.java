public class Destroyer extends Boat implements Attacker {
    
    public Destroyer(int team, Coordinates location, int direction) {

        super(team, location, direction, 2, 2, 1);
    }

    public String getID() {return "D" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Destroyer:\n";
        str += "1. Move\n2. Turn left\n3. Turn right\n4. Attack";
        return str;
    }

    public String act(int[] actions, World map) {

        String str = "";

        for (int action : actions) {
            if (action == 1) {
                str += move(map) + "\n";
            } else if (action == 2) {
                str += turn(-1) + "\n";
            } else if (action == 3) {
                str += turn(1) + "\n";
            } else {
                str += attack(map) + "\n";
            }
        }

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
                return "" + occupant.takeHit(getStrength());
            } else {
                attackSquare = map.getAdjacentLocation(attackSquare, getDirectionVal());
            }
        }
    }

    public String takeHit(int numAttacks) {

        int attacksAvoided = 0;

        for (int i = 0; i < numAttacks; i++) {
            if (Math.random() > .5) {attacksAvoided++;}
        }

        numAttacks -= attacksAvoided;

        if (numAttacks== 0) {
            return getID() + " has avoided the attack!";
        } else {
            return super.takeHit(numAttacks);
        }
    }
}
