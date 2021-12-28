package generation;


/**
 * Allows for the detailing of different
 * enemy types based on the actions they
 * perform during the enemy-turn
 */

public interface EnemyState {

    /**
     * set and get the map
     */
    public Map getMap();
    public void setMap(Map map);

    /**
     * set and get the position of the enemy
     */
    public int[] getPosition();
    public void setPosition(int[] pos);

    /**
     * set's the player's position
     */
    public void setPlayerPosition(int[] pos);

    /**
     * Dictates the actions of the enemy during
     * the enemy turn
     */
    public void act();

    /**
     * Allows the map to know whether or not
     * the enemy has moved as to not double act
     */
    public boolean hasActed();
    public void setHasActed(boolean b);
}
