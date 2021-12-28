package gui;

public class Controller {
    
    // States for the game
    GameState[] states;

    // Current state of the game
    GameState currentState;

    // Panel used to draw on the screen
    MapPanel panel;

    Controller() {
        states = new GameState[5];
        states[0] = new StateTitle();
        states[1] = new StateGeneration();
        states[2] = new StatePlaying();
        states[3] = new StateWinning();
        states[4] = new StateLoosing();
        currentState = states[0];
        panel = new MapPanel();
    }

    public MapPanel getPanel() {
        return panel;
    }

    public void start() {
        currentState.start(this, panel);
    }

    public void keyDown(UserInput key, int value) {
        currentState.keyDown(key, value);
    }

}