# battleship-project
Final project from Purdue University's AP CS A course on edx.org.

Coordinates.java:
File to store the x and y coordinates for game objects.

World.java:
Handles the game map.

Boat.java:
Abstract class used by all subsequent boat classes.

Attacker.java:
Interface for attacking boats.

ScoutBoat.java:
Abstract class used by Cruiser.java and Submarine.java.

Cruiser.java:
Extends ScoutBoat.java, non-attacking boat that can move twice.

Submarine.java:
Extends ScoutBoat.java and implements Attacker.java, can submerge to appear at a 
random spot on the map and fire torpedoes which deal damge based on the target's max health.

AircraftCarrier.java:
Extends Boat.java and implements Attacker.java, launches planes which attack adjacent squares.
Planes have a random chance of being destroyed.

Battleship.java:
Extends Boat.java and implements Attacker.java, can attack twice.

Destroyer.java:
Extends Boat.java and implements Attacker.java, has a high chance to avoid attacks.
