package gui;

import generation.Map;

public class StateInstructions implements GameState{

    Controller controller;
    MapPanel panel;

    @Override
    public void start(Controller controller, MapPanel mapPanel) {
        // TODO Auto-generated method stub
        
    }


    /**
     * Not needed for instructions
     */
    @Override
    public void setDifficulty(int difficulty) {}

    @Override
    public void setMapConfig(Map map) {}

    
    @Override
    public void keyDown(UserInput key, int value) {
        if (key == UserInput.exit) {
            controller.switchToTitle();
        }        
    }
    
}
