package generation;


/**
 * Holds all information about a cell:
 * Doors, good/bad, enemy type, count
 */

public class Cell {
    
    // All doors:
    private Door NorthDoor;
    private Door EastDoor;
    private Door SouthDoor;
    private Door WestDoor;

    // whether or not the cell is bad and what enemy is within
    private boolean IsGood;
    private EnemyState Enemy;

    // The count of how many bad cells are surounding this one
    private int count;

    /**
     * To set up a cell, we need to initialize:
     * all doors closed
     * IsGood based on input
     * EnemyState generated randomly if necessary
     * count is zero
     */
    Cell() {
        
        IsGood = true;

        NorthDoor = Door.Closed;
        EastDoor = Door.Closed;
        SouthDoor = Door.Closed;
        WestDoor = Door.Closed;

        count = 0;
    }

    /**
     * Used to check if the cell is good or not
     */
    public boolean isGood() {
        return IsGood;
    }

    /**
     * Change the boolean IsGood
     */
    public boolean toggleGood() {
        IsGood = !IsGood;
        return IsGood;
    }

    /**
     * Retrieve the state of a door in a given direction
     */
    public Door doorState(Direction d) {
        switch (d) {
            case North: return NorthDoor;
            case South: return SouthDoor;
            case East: return EastDoor;
            case West: return WestDoor;
        }
        return null;
    }

    /**
     * Changes the state of the door in a given direction.
     * Returns false if given change wouldn't change current state
     */
    public boolean changeDoorState(Direction d, Door change) {
        switch (d) {
            case North: {
                if (NorthDoor == change) {
                    return false;
                }
                NorthDoor = change;
                return true;      
            }
            case South: {
                if (SouthDoor == change) {
                    return false;
                }
                SouthDoor = change;
                return true;      
            }
            case East: {
                if (EastDoor == change) {
                    return false;
                }
                EastDoor = change;
                return true;      
            }
            case West: {
                if (WestDoor == change) {
                    return false;
                }
                WestDoor = change;
                return true;      
            }
        }
        return false;
    }


    /**
     * Used to increment the count of a room if it has a bad
     * cell in a surounding room
     */
    public void incrementCount() {
        count ++;
    }
    
    /**
     * Used to decrement the count if a room goes from bad to
     * good later in the game (gets "cleared")
     */
    public void decrementCount() {
        count --;
    }

    /**
     * Returns the count of the cell
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the enemy object for the game to give it
     * necessary information
     */
    public EnemyState getEnemy() {
        if (IsGood) {
            return null;
        }
        return Enemy;
    }

    /**
     * Allows the Map to tell the enemy within a room to
     * activate. Returns false if unsuccessful for any reason
     */
    public boolean activate() {
        if (IsGood || Enemy.hasActed()) {
            return false;
        }
        Enemy.act();
        return true;
    }

    /**
     * Allows outside classes to fill a previously good room
     * with an enemy. Returns false if room is already bad.
     * Allows the choice of an enemy type if necessary
     */
    public boolean newEnemy(EnemyState e, Map map, int[] position) {
        if (!IsGood) {
            return false;
        }
        if (e == null) {
            int choice = Rando.randoRange(0, 3);
            switch (choice) {
                case 0: Enemy = new Virus(map, position); break;
                case 1: Enemy = new Soldier(map, position); break;
                case 3: Enemy = new Hacker(map, position, 3); break;
            }    
        } else {
            Enemy = e;
        }
        IsGood = false;
        return true;
    }

    /**
     * Removes an existing enemy without
     * cleaning the cell
     */
    public void removeEnemy() {
        Enemy = null;
        IsGood = true;
    }


    /**
     * Allows the cleaning of cells if enemies are killed.
     * Resets the room to good, all doors closed (if able)
     */
    public void cleanCell() {
        Enemy = null;
        IsGood = true;
        closeAbleDoor(Direction.North);
        closeAbleDoor(Direction.South);
        closeAbleDoor(Direction.East);
        closeAbleDoor(Direction.West);
    }

    /**
     * Closes a door if able and used in Map for cleansing ONLY
     */
    public void closeAbleDoor(Direction d) {
        switch (d) {
            case North: {
                if (NorthDoor != Door.Broken && NorthDoor != Door.Outside) {
                    NorthDoor = Door.Closed;
                }
                break;
            }
            case South: {
                if (SouthDoor != Door.Broken && SouthDoor != Door.Outside) {
                    SouthDoor = Door.Closed;
                }
                break;
            }
            case East: {
                if (EastDoor != Door.Broken && EastDoor != Door.Outside) {
                    EastDoor = Door.Closed;
                }
                break;
            }
            case West: {
                if (WestDoor != Door.Broken && WestDoor != Door.Outside) {
                    WestDoor = Door.Closed;
                }
                break;
            }
        }
    }




    /**
     * Check if a door is able to be used given a direction
     */
    public boolean canDoorToggle(Direction d) {
        switch (d) {
            case North: return canDoorToggleDoor(NorthDoor);
            case South: return canDoorToggleDoor(SouthDoor);
            case East: return canDoorToggleDoor(EastDoor);
            case West: return canDoorToggleDoor(WestDoor);
        }
        return false;
    }

    /**
     * Helper method for above
     */
    private boolean canDoorToggleDoor(Door d) {
        if (d != Door.Broken && d != Door.Outside) {
            return true;
        }
        return false;
    }

    /**
     * toggle the given door if able
     */
    public void toggleDoor(Direction d) {
        if (canDoorToggle(d)) {
            switch (d) {
                case North: NorthDoor = Door.toggleDoor(NorthDoor); break;
                case South: SouthDoor = Door.toggleDoor(SouthDoor); break;
                case East: EastDoor = Door.toggleDoor(EastDoor); break;
                case West: WestDoor = Door.toggleDoor(WestDoor); break;
            }
        }
    }


    /**
     * Check if a cell has any working doors
     */
    public boolean cellBroken() {
        if (!canDoorToggleDoor(NorthDoor)) {
            if (!canDoorToggleDoor(SouthDoor)) {
                if (!canDoorToggleDoor(EastDoor)) {
                    if (!canDoorToggleDoor(WestDoor)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
