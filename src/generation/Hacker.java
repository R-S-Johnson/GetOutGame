package generation;


/**
 * The hacker opens and closes doors at random
 * to hopefully stop the player from acting.
 * Not deadly, but will move locations if found
 * (so will not open their own doors)
 */

public class Hacker implements EnemyState{

    // The map the enemy needs to access to move
    private Map map;

    // Current position of Hacker
    private int[] position;

    // Keeps track of the number of doors to change
    private int doorsToChange;

    // Has moved already this turn
    private boolean hasMoved;


    /**
     * Initialize a map, starting position, and doors to change
     */
    Hacker(Map map, int[] position, int doorsToChange) {
        this.map = map;
        this.position = position;
        this.doorsToChange = doorsToChange; //TODO this might need to be more complex depending on how this is calculated
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

    public void setDoorsToChange(int doors) {
        doorsToChange = doors;
    }

    
    /**
     * Method to be called during the enemy-turn.
     * The number of doors changed is decided by
     * the size of the map.
     */
    
    @Override
    public void act() {
        if (anyDoorOpen()) {
            actionChoice2();
        } else {
            actionChoice1();
        }
        hasMoved = true;
    }
    
    private boolean anyDoorOpen() {
        if (anyDoorOpenDirect(Direction.North)) {
            if (anyDoorOpenDirect(Direction.South)) {
                if (anyDoorOpenDirect(Direction.East)) {
                    if (anyDoorOpenDirect(Direction.West)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean anyDoorOpenDirect(Direction d) {
        Door doorState = map.doorState(position[0], position[1], d);
        if (doorState == Door.Open || doorState == Door.Broken) {
            return true;
        }
        return false;
    }

    /**
     * close random doors
     */
    private void actionChoice1() {
        for (int i = 0; i < doorsToChange; i++) {
            int[] selectedCell = new int[2];
            // Select a cell at random
            selectedCell[0] = Rando.randoRange(0, map.getLength());
            selectedCell[1] = Rando.randoRange(0, map.getWidth());
            while (map.cellBroken(selectedCell[0], selectedCell[1])) {
                selectedCell[0] = Rando.randoRange(0, map.getLength());
                selectedCell[1] = Rando.randoRange(0, map.getWidth());       
            }
            // Select a door at random
            Direction directionChoice = Direction.randomDirection();
            while (!map.canDoorToggle(selectedCell[0], selectedCell[1], directionChoice)) {
                directionChoice = Direction.randomDirection();
            }
            map.toggleDoor(selectedCell[0], selectedCell[1], directionChoice);
        }
    }

    /**
     * teleport
     */
    private void actionChoice2() {
        int[] selectedCell = new int[2];
        // select random cell
        selectedCell[0] = Rando.randoRange(0, map.getLength());
        selectedCell[1] = Rando.randoRange(0, map.getWidth());
        // make sure it's uninhabitted
        while (selectedCell == map.getPlayerPosition() || !map.isGood(selectedCell[0], selectedCell[1])) {
            selectedCell[0] = Rando.randoRange(0, map.getLength());
            selectedCell[1] = Rando.randoRange(0, map.getWidth());    
        }
        map.newEnemy(selectedCell[0], selectedCell[1], this);
        map.removeEnemy(position[0], position[1]);
        position = selectedCell;
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
