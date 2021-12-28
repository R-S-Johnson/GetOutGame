package generation;

public enum Direction {
    North, South, East, West;

    /**
     * Chooses a random direction
     */
    public static Direction randomDirection() {
        int choice = Rando.randoRange(0, 3);
        if (choice == 0) {
            return North;
        }
        if (choice == 1) {
            return South;
        }
        if (choice == 2) {
            return East;
        }
        else {
            return West;
        }
    }
}
