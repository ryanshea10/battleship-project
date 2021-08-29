public class AircraftCarrier extends Boat implements Attacker{
    
    private boolean hasPlanes;

    public AircraftCarrier(int team, Coordinates location, int direction) {

        super(team, location, direction, 5, 1, 1);
        this.hasPlanes = true;
    }

    public String getID() {return "A" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Aircraft Carrier:\n";
        str += "1. Move\n2. Turn left\n3. Turn right";
        str += (hasPlanes) ? "\n4. Launch planes" : "";
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

        if (!hasPlanes) {return getID() + " has no planes remaining.";}

        double successRate = 1;
        String str = "Air raid! ";

        for (int i = 0; i < 8; i++) {
            Coordinates square = map.getAdjacentLocation(getLocation(), i);
            if (map.getOccupant(square) != null) {
                if (successRate < Math.random()) {
                    hasPlanes = false;
                    return "The planes have been destroyed.";
                }
                str += map.getOccupant(square).takeHit(getStrength()) + " ";
                successRate *= 0.80;
            }
        }

        return (str.length() > 1) ? str : "There are no boats in range currently.";
    }
}
