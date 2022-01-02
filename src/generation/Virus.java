package generation;


/**
 * The Virus spreads (max distance 2)
 * through any open door on the enemy
 * turn.
 */

public class Virus implements EnemyState{

    // The map the enemy needs to access to move
    private Map map;

    // Starting position (the virus doens't move)
    private int[] position;

    // Keeps track of if the virus has acted (and if new viruses should act)
    boolean hasMoved;
    

    /**
     * Initialize a map and starting position
     */
    Virus(Map map, int[] position) {
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
        this.position = pos;        
    }


    /**
     * The virus tries to find an open door in its room,
     * if found it will infect the room through it (if not 
     * already inhabitted by an enemy)
     */
    @Override
    public void act() {
        Direction[] doorsToAct = {Direction.North, Direction.South, Direction.East, Direction.West};
        for (int i = 0; i < 4; i++) {
            actOnDoor(doorsToAct[i], 1, position);
        }
    }

    private void actOnDoor(Direction direction, int i, int[] position) {
        if (map.doorState(position[0], position[1], direction) == Door.Open) {
            int[] newPos = infectInDirection(position, direction);
            if (i == 1 && newPos != position) {
                Direction[] doorsToAct = {Direction.North, Direction.South, Direction.East, Direction.West};
                for (int j = 0; j < 4; j++) {
                    actOnDoor(doorsToAct[j], 2, newPos);
                }      
            }
        } 
    }

    private int[] infectInDirection(int[] position, Direction direction) {
        EnemyState newVirus = null;
        int[] posInsert = new int[2];
        switch (direction) {
            case North: {
                posInsert[0] = position[0] - 1; posInsert[1] = position[1];
                if (!map.isGood(posInsert[0], posInsert[1])) {
                    return position;
                }
                newVirus = new Virus(map, posInsert);
                break;
            }
            case South: {
                posInsert[0] = position[0] + 1; posInsert[1] = position[1];
                if (!map.isGood(posInsert[0], posInsert[1])) {
                    return position;
                }
                newVirus = new Virus(map, posInsert);
                break;
            }
            case East: {
                posInsert[0] = position[0]; posInsert[1] = position[1] + 1;
                if (!map.isGood(posInsert[0], posInsert[1])) {
                    return position;
                }
                newVirus = new Virus(map, posInsert);
                break;
            }
            case West: {
                posInsert[0] = position[0]; posInsert[1] = position[1] - 1;
                if (!map.isGood(posInsert[0], posInsert[1])) {
                    return position;
                }
                newVirus = new Virus(map, posInsert);
                break;
            }
        }
        newVirus.setHasActed(true);
        map.newEnemy(posInsert[0], posInsert[1], newVirus);
        return posInsert;
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
