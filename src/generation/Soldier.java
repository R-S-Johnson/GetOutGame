package generation;


/**
 * The Soldier follows the player (semi-accurately) by breaking down doors
 * The door it plans to move through gets Marked before
 * exploding.
 */

public class Soldier implements EnemyState {

    // The map the enemy needs to access to move
    private Map map;

    // The current position of the Soldier
    private int[] position;

    // The player's position the Soldier uses to track the player
    private int[] playerPosition;

    // Keeps track of if the Soldier has acted yet this turn
    private boolean hasMoved;


    /**
     * Initialize a map and starting posiiton
     */
    Soldier(Map map, int[] position) {
        this.map = map;
        this.position = position;
    }

    @Override
    public Map getMap() {
        return map;
    }

    @Override
    public void setMap(Map map) {
        this.map = map;        
    }

    @Override
    public int[] getPosition() {
        return position;
    }
    @Override
    public void setPosition(int[] pos) {
        position = pos;
    }

    @Override
    public void setPlayerPosition(int[] pos) {
        this.playerPosition = pos;
    }


    /**
     * if player is lined up with the soldier,
     * soldier moved directly towards it.
     * If not, the soldier moves randomly
     * chooses one of two possible doors to go
     * through.
     * Moving if the door is open (or there's an
     * open door of the two)
     * Marking a door if the door is closed
     */

    @Override
    public void act() {
        // horizontally lined up
        if (playerPosition[0] == position[0]) {
            // soldier on the right
            if (playerPosition[1] < position[1]) {
                actOnDoor(Direction.West);
            } else { // soldier on left
                actOnDoor(Direction.East);
            }
        } else if (playerPosition[1] == position[1]) { // vertically lined up
            // soldier below
            if (playerPosition[0] < position[0]) {
                actOnDoor(Direction.North);
            } else {
                actOnDoor(Direction.South);
            }
        }
        // not lined up
        else {
            int choice = Rando.randoRange(0, 2);
            // soldier above
            if (position[0] < playerPosition[0]) {
                // soldier on right
                if (playerPosition[1] < position[1]) {
                    // north or west
                    if (choice == 0 && map.isGood(position[0] + 1, position[1])) {
                        actOnDoor(Direction.South);
                    } else if (map.isGood(position[0], position[1] - 1)) {
                        actOnDoor(Direction.West);
                    }
                } else { // soldier on left
                    // north or east
                    if (choice == 0 && map.isGood(position[0] + 1, position[1])) {
                        actOnDoor(Direction.South);
                    } else if (map.isGood(position[0], position[1] + 1)) {
                        actOnDoor(Direction.East);
                    }
                }
            }
            // soldier below
            else {
                // soldier on right
                if (playerPosition[1] < position[1]) {
                    // south or west
                    if (choice == 0 && map.isGood(position[0] - 1, position[1])) {
                        actOnDoor(Direction.North);
                    } else if (map.isGood(position[0], position[1] - 1)) {
                        actOnDoor(Direction.West);
                    }
                } else { // soldier on left
                    // south or east
                    if (choice == 0 && map.isGood(position[0] - 1, position[1])) {
                        actOnDoor(Direction.North);
                    } else if (map.isGood(position[0], position[1] - 1)) {
                        actOnDoor(Direction.East);
                    }
                }
            }
        }
        hasMoved = true;
    }

    private void actOnDoor(Direction choice) {
        if (map.doorState(position[0], position[1], choice) == Door.Marked) {
            map.changeDoorState(position[0], position[1], choice, Door.Broken);
            moveInDirection(choice);
        } else if (map.doorState(position[0], position[1], choice) == Door.Open) {
            moveInDirection(choice);
        } else {
            map.changeDoorState(position[0], position[1], choice, Door.Marked);
        }
    }

    private void moveInDirection(Direction d) {
        switch (d) {
            case North: {
                map.removeEnemy(position[0], position[1]);
                position[0]--;
                map.newEnemy(position[0], position[1], this);
                break;
            }
            case South: {
                map.removeEnemy(position[0], position[1]);
                position[0]++;
                map.newEnemy(position[0], position[1], this);
                break;
            }
            case East: {
                map.removeEnemy(position[0], position[1]);
                position[1]++;
                map.newEnemy(position[0], position[1], this);
                break;
            }
            case West: {
                map.removeEnemy(position[0], position[1]);
                position[1]--;
                map.newEnemy(position[0], position[1], this);
                break;
            }
        }
    }

    @Override
    public boolean hasActed() {
        return hasMoved;
    }

    @Override
    public void setHasActed(boolean b) {
        hasMoved = b;        
    }
    
}
