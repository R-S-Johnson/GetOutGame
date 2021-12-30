package gui;

import generation.Map;
import generation.MapGenerator;

public class StateGeneration implements GameState{

    Controller controller;

    MapPanel panel;

    Map map;

    int difficulty;

    @Override
    public void start(Controller controller, MapPanel mapPanel) {
        this.controller = controller;
        this.panel = mapPanel;
        
        SimpleScreens sc = new SimpleScreens();
        sc.drawLoadingScreen(panel);
        panel.update();

        map = new Map(difficulty + 5, difficulty + 5);
        MapGenerator mg = new MapGenerator(this.map);
        mg.setDifficulty(difficulty);
        mg.setState(this);

        Thread t = new Thread(mg);
        t.start();
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;        
    }

    @Override
    public void setMapConfig(Map map) {
        controller.switchFromGeneratingToPlaying(map);
    }

    @Override
    public void keyDown(UserInput key, int value) {
        if (key == UserInput.exit) {
            controller.switchToTitle();
        }
    }
    
}
