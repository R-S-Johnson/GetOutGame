package gui;

import generation.Map;

public class StateTitle implements GameState{

    @Override
    public void start(Controller controller, MapPanel mapPanel) {
        // TODO Auto-generated method stub
        
    }

    /**
     * not needed for title
     */
    @Override
    public void setDifficulty(int difficulty) {}

    /**
     * not needed for title
     */
    @Override
    public void setMapConfig(Map map) {}


    
    @Override
    public void keyDown(UserInput key, int value) {
        // TODO Auto-generated method stub
        
    }
    
}
