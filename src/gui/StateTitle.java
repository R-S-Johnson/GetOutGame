package gui;

import generation.Map;

public class StateTitle implements GameState{

    Controller controller;

    MapPanel panel;

    @Override
    public void start(Controller controller, MapPanel mapPanel) {
        this.controller = controller;
        this.panel = mapPanel;

        SimpleScreens sc = new SimpleScreens();
        sc.drawTitle(panel);
        panel.update();
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
        if (key == UserInput.startGame) {
            controller.switchFromTitleToGenerating(value);
        }
        if (key == UserInput.instructions) {
            controller.switchFromTitleToInstructions();
        }
    }
    
}
