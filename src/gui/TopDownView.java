package gui;

import java.awt.Color;

import generation.Map;

public class TopDownView {

    // colors used for the view
    static final Color wallColor = Color.gray;
    static final Color doorColor = Color.black;
    static final Color enemyShade = Color.red;

    private Map map;

    private boolean zoomed;


    public TopDownView(Map map) {
        this.map = map;
        zoomed = false;
    }

    public void draw() {}
    
}
