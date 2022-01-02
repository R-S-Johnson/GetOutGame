package generation;

import gui.StateGeneration;

/**
 * Given an empty map, is the job of the MapGenerator
 * to get the map ready for play. It needs a given
 * difficulty to do so, which determins the size of
 * the maze (not set here) and the number of enemies.
 */

public class MapGenerator implements Runnable{
    
    // Map object to be edited
    Map map;

    // The given difficulty of the map, must be btwn 0 - 100
    int difficulty;

    // State needed to allow generator to give map object over
    StateGeneration state;


    /**
     * The only thing needed to initialize the
     * generator is an empty map object. The
     * difficulty will be set later
     */
    public MapGenerator(Map map) {
        this.map = map;
    }

    /**
     * Set method for the difficulty for the map
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setState(StateGeneration s) {
        state = s;
    }

    /**
     * Main method for the generator. After generation
     * is done, this method returns the map object.
     */
    public Map generate() {
        for (int i = 0; i < map.getLength(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                int rand = Rando.randoRange(0, 100);
                if (rand <= difficulty) {
                    map.newEnemy(i, j, null);
                }
                openRandomDoors(i, j);
            }
        }
        setPlayerPosition();
        setWinningPosition();
        return map;
    }


    private void setWinningPosition() {
        int[] position = map.getPlayerPosition();

        while (position == map.getPlayerPosition()) {
            position[0] = Rando.randoRange(0, map.getLength());
            position[1] = Rando.randoRange(0, map.getWidth());
        }
        map.setWinningPosition(position);
        map.clense(position[0], position[1]);
    }

    /**
     * Randomly opens 25% of the doors
     */
    private void openRandomDoors(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int rand = Rando.randoRange(0, 100);
            if (rand <= 25) {
                if (i == 0 && map.canDoorToggle(x, y, Direction.North)) {
                    map.changeDoorState(x, y, Direction.North, Door.Open);
                }
                else if (i == 1 && map.canDoorToggle(x, y, Direction.South)) {
                    map.changeDoorState(x, y, Direction.South, Door.Open);
                }
                else if (i == 2 && map.canDoorToggle(x, y, Direction.East)) {
                    map.changeDoorState(x, y, Direction.East, Door.Open);
                }
                else if (i == 3 && map.canDoorToggle(x, y, Direction.West)) {
                    map.changeDoorState(x, y, Direction.West, Door.Open);
                }
            }
        }
    }

    /**
     * Used to set the player's position after
     * the map has been generated. The starting 
     * position cannot have an enemy, and all doors
     * in the room will close (use the clense method)
     */
    private void setPlayerPosition() {
        int[] position = new int[2];
        // randomly generate a posiiton
        position[0] = Rando.randoRange(0, map.getLength());
        position[1] = Rando.randoRange(0, map.getWidth());
        map.setPlayerPosition(position);
        map.clense(position[0], position[1]);
    }



    @Override
    public void run() {
        Map toReturn = generate();
        state.setMapConfig(toReturn);
    }



    /**
     * Main method used for testing generation
     */
    // public static void main(String[] args) {
    //     Map map = new Map(3, 3);
    //     MapGenerator gen = new MapGenerator(map);
    //     gen.setDifficulty(30);
    //     System.out.println(gen.generate().toString());
    //     for (int i = 0; i < map.getLength(); i++) {
    //         for (int j = 0; j < map.getWidth(); j++) {
    //             map.activate(i, j);
    //             System.out.println(map.toString()); // TODO Always remember to turn off hasMoved in a final loop through
    //         }
    //     }
    //     System.out.println(map.toString());
    // }
}
