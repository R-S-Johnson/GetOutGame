package generation;


/**
 * Holds all possible states for doors
 */

public enum Door {
    Open, Closed, Broken, Marked, Outside;

    public static Door toggleDoor(Door d) {
        if (d == Open) {
            return Closed;
        }
        return Open;
    }
}
