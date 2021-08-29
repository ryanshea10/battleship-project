public class Cruiser extends ScoutBoat {
    
    public Cruiser(int team, Coordinates location, int direction) {

        super(team, location, direction, 3, 3);
    }

    public String getID() {return "C" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Cruiser:\n";
        str += "1. Move\n2. Turn left\n3. Turn right";
        return str;
    }

    public String act(int[] actions, World map) {

        String str = "";

        for (int action : actions) {
            if (action == 1) {
                str += move(map) + "\n";
            } else if (action == 2) {
                str += turn(-1) + "\n";
            } else {
                str += turn(1) + "\n";
            }
        }

        return str;
    }
}
