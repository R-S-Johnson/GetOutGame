package generation;


/**
 * The map object holds all information about the map:
 * Size and cells (which hold their own information).
 * The map object made for a game will be passed to other
 * objects in order to allow them to move and sense along it
 */

public class Map {
    
    // length and width of the map
    private int length;
    private int width;

    // Basic map is an array of cells based on length and width
    private Cell[][] map;

    // Tells  is the map object is complete and ready to play in
    private boolean complete;

    // The winning position of the map
    private int[] winningPosition;

    // Current position of the player
    private int[] playerPosition;

    
    /**
     * Initializing a map doesn't prepare it completely for the game.
     * It only fills every space with a perfect cell. Adding enemies
     * happens in generation.
     */
    public Map(int l, int w) {
        length = l;
        width = w;
        map = new Cell[length][width];
        for (int i = 0; i < length; i ++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell();
                // First row; north doors go outside
                if (i == 0) {
                    cell.changeDoorState(Direction.North, Door.Outside);
                }
                // Last row; south doors go outside
                if (i == (length - 1)) {
                    cell.changeDoorState(Direction.South, Door.Outside);
                }
                // First collumn; west doors go outside
                if (j == 0) {
                    cell.changeDoorState(Direction.West, Door.Outside);
                }
                // Last collumn; east doors go outside
                if (j == (width - 1)) {
                    cell.changeDoorState(Direction.East, Door.Outside);
                }
                map[i][j] = cell;
            }
        }
        complete = false;
    }

    /**
     * The major part of preparing the map for the game, creating
     * cells and putting them in positions. This method is called
     * by generation
     */
    public void setCell(int x, int y, Cell cell) {
        map[x][y] = cell;

        // make sure to up the counts
        if (!cell.isGood()) {
            // above cell
            if (x - 1 >= 0) {
                incrementCount(x - 1, y);
            }
            // below cell
            if (x + 1 < getLength()) {
                incrementCount(x + 1, y);
            }
            // left cell
            if (y - 1 >= 0) {
                incrementCount(x, y - 1);
            }
            // right cell
            if (y + 1 < getWidth()) {
                incrementCount(x, y + 1);
            }
        }
    }

    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }

    public int[] getWinningPosition() {
        return winningPosition;
    }
    public void setWinningPosition(int[] winningPosition) {
        this.winningPosition = winningPosition;
    }

    public int[] getPlayerPosition() {
        return playerPosition;
    }
    public void setPlayerPosition(int[] playerPosition) {
        this.playerPosition = playerPosition;
    }

    /**
     * Methods below are repeated from Cell but with
     * position inputs to target a cell in the map
     */

/////////////////////////////////////////////////////////

    public boolean isGood(int x, int y) {
        return map[x][y].isGood();
    }

    public void toggleGood(int x, int y) {
        if (map[x][y].toggleGood()) {
            // above cell
            if (x - 1 >= 0) {
                decrementCount(x - 1, y);
            }
            // below cell
            if (x + 1 < getLength()) {
                decrementCount(x + 1, y);
            }
            // left cell
            if (y - 1 >= 0) {
                decrementCount(x, y - 1);
            }
            // right cell
            if (y + 1 < getWidth()) {
                decrementCount(x, y + 1);
            }            
        } else {
            // above cell
            if (x - 1 >= 0) {
                incrementCount(x - 1, y);
            }
            // below cell
            if (x + 1 < getLength()) {
                incrementCount(x + 1, y);
            }
            // left cell
            if (y - 1 >= 0) {
                incrementCount(x, y - 1);
            }
            // right cell
            if (y + 1 < getWidth()) {
                incrementCount(x, y + 1);
            }
        }
    }

    public Door doorState(int x, int y, Direction d) {
        return map[x][y].doorState(d);
    }

    public boolean changeDoorState(int x, int y, Direction d, Door change) {
        // outside doors don't have an other side
        if (change == Door.Outside) {
            return map[x][y].changeDoorState(d, change);
        }
        if (map[x][y].changeDoorState(d, change)) {
            if (d == Direction.North) {
                map[x - 1][y].changeDoorState(Direction.South, change);
            }
            else if (d == Direction.South) {
                map[x + 1][y].changeDoorState(Direction.North, change);
            }
            else if (d == Direction.East) {
                map[x][y + 1].changeDoorState(Direction.West, change);
            }
            else if (d == Direction.West) {
                map[x][y - 1].changeDoorState(Direction.East, change);
            }
            return true;
        }
        return false;
    }

    public void incrementCount(int x, int y) {
        map[x][y].incrementCount();
    }

    public void decrementCount(int x, int y) {
        map[x][y].decrementCount();
    }

    public int getCount(int x, int y) {
        return map[x][y].getCount();
    }

    public boolean activate(int x, int y) {
        System.out.println(map[x][y].getEnemy());
        return map[x][y].activate();
    }

    public boolean newEnemy(int x, int y, EnemyState e) {
        int[] position = {x, y};
        if (map[x][y].newEnemy(e, this, position)) {
            // above cell
            if (x - 1 >= 0) {
                incrementCount(x - 1, y);
            }
            // below cell
            if (x + 1 < getLength()) {
                incrementCount(x + 1, y);
            }
            // left cell
            if (y - 1 >= 0) {
                incrementCount(x, y - 1);
            }
            // right cell
            if (y + 1 < getWidth()) {
                incrementCount(x, y + 1);
            }
            return true;
        }
        return false;
    }

    public void removeEnemy(int x, int y) {
        map[x][y].removeEnemy();
    }

    public boolean canDoorToggle(int x, int y, Direction d) {
        return map[x][y].canDoorToggle(d);
    }

    public boolean cellBroken(int x, int y) {
        return map[x][y].cellBroken();
    }

    public EnemyState getEnemy(int x, int y) {
        return map[x][y].getEnemy();
    }

///////////////////////////////////////////////////////////////


    /**
     * Allowing the changing of Complete in order
     * for the map to be complete and the game to begin.
     */
    public void makeComplete() {
        complete = true;
    }

    /**
     * Used to check if the map is complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Clense a cell of an enemy.
     * Call cleanCell in the given coordinates
     * then close the other side doors
     */
    public void clense(int x, int y) {
        map[x][y].cleanCell();

        // check above
        if (x - 1 >= 0) {
            map[x - 1][y].closeAbleDoor(Direction.South);
        }
        // check below
        if (x + 1 < length) {
            map[x + 1][y].closeAbleDoor(Direction.North);
        }
        // check left
        if (y - 1 >= 0) {
            map[x][y - 1].closeAbleDoor(Direction.East);
        }
        // check right
        if (y + 1 < width) {
            map[x][y + 1].closeAbleDoor(Direction.West);
        }
    }

    /**
     * Toggle the door of a given cell in a given direction.
     * Will also need to close the opposite door.
     */
    public void toggleDoor(int x, int y, Direction d) {
        map[x][y].toggleDoor(d);
        if (d == Direction.North) {
            map[x - 1][y].toggleDoor(Direction.South);
            System.out.println(map[x-1][y].doorState(Direction.South));
        }
        else if (d == Direction.South) {
            map[x + 1][y].toggleDoor(Direction.North);
            System.out.println(map[x+1][y].doorState(Direction.North));
        }
        else if (d == Direction.East) {
            map[x][y + 1].toggleDoor(Direction.West);
            System.out.println(map[x][y+1].doorState(Direction.West));
        }
        else if (d == Direction.West) {
            map[x][y - 1].toggleDoor(Direction.East);
            System.out.println(map[x][y-1].doorState(Direction.East));
        }
    }

    /**
     * toString method override for testing purposes
     */
    @Override
    public String toString() {
        String toreturn = "";
        // print mine map
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (!isGood(i, j)) {
                    toreturn += " * |";
                } else {
                    toreturn += "   |";
                }
            }
            toreturn += "\n";
            for (int j = 0; j < width; j++) {
                toreturn += "----";
            }
            toreturn += "\n";
        }
        toreturn += "\n";

        // print count map
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                toreturn += " " + Integer.toString(getCount(i, j)) + " |";
            }
            toreturn += "\n";
            for (int j = 0; j < width; j++) {
                toreturn += "----";
            }
            toreturn += "\n";
        }
        return toreturn;
    }

}
