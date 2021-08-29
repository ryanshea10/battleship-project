public abstract class ScoutBoat extends Boat {
    
    public ScoutBoat(int team, Coordinates location, int direction, int health, int vision) {

        super(team, location, direction, health, 1, vision);
    }

    public String takeHit(int numAttacks) {

        int attacksAvoided = 0;

        for (int i = 0; i < numAttacks; i++) {
            if (Math.random() > .25) {attacksAvoided++;}
        }

        numAttacks -= attacksAvoided;

        if (numAttacks== 0) {
            return getID() + " has avoided the attack!";
        } else {
            return super.takeHit(numAttacks);
        }
    }
}
