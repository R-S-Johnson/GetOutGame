package gui;
import generation.Map;


/**
 * ALlows for the separation of roles
 * of different states of the game based
 * on their gui, information collection,
 * and user inputs.
 */
public interface GameState {
    
    /**
     * Starts the selected activity given a
     * controller for user input and a panel
     * to draw on
     */
    void start(Controller controller, MapPanel mapPanel);


    /**
     * Sets the difficulty of the game,
     * only used in generation
     */
    void setDifficulty(int difficulty);

    /**
     * Sets the map configuration for playing
     * the game
     */
    void setMapConfig(Map map);

    /**
     * Responsible for handling key inputs
     * from the user
     */
    void keyDown(UserInput key, int value);
}
