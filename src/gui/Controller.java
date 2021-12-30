package gui;

import generation.Map;

public class Controller {
    
    // States for the game
    GameState[] states;

    // Current state of the game
    GameState currentState;

    // Panel used to draw on the screen
    MapPanel panel;

    Controller() {
        states = new GameState[6];
        states[0] = new StateTitle();
        states[1] = new StateGeneration();
        states[2] = new StatePlaying();
        states[3] = new StateWinning();
        states[4] = new StateLoosing();
        states[5] = new StateInstructions();
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


    /**
     * methods used to switch the state
     * of the game
     */
    public void switchFromTitleToGenerating(int difficulty) {
        // TODO remove (testing)
        System.out.println("switching to generating");
        System.out.println(Integer.toString(difficulty));
        currentState = states[1];
        currentState.setDifficulty(difficulty);
        currentState.start(this, panel);
    }

    public void switchFromTitleToInstructions() {
        currentState = states[5];
        currentState.start(this, panel);
    }

    public void switchFromGeneratingToPlaying(Map map) {
        currentState = states[2];
        currentState.setMapConfig(map);
        currentState.start(this, panel);
    }

    public void switchFromPlayingToWinning() {
        currentState = states[3];
        currentState.start(this, panel);
    }

    public void switchFromPlayingToLoosing() {
        currentState = states[4];
        currentState.start(this, panel);
    }

    public void switchToTitle() {
        currentState = states[0];
        currentState.start(this, panel);
    }

}