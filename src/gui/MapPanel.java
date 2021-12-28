package gui;

import java.awt.Panel;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Graphics;


/**
 * used to store the graphics used to draw.
 * Uses a buffer image to allow drawings to
 * finish before showing them on screen
 */
public class MapPanel extends Panel{
    
    private Image bufferImage;
    private Graphics2D graphics;


    MapPanel() {
        setFocusable(false);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void update() {
        paint(getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        if (g != null) {
            g.drawImage(bufferImage, 0, 0, null);
        }
    }


    public Graphics getBufferGraphics() {
        if (graphics == null) {
            if (bufferImage == null) {
                bufferImage = createImage(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
            }
            graphics = (Graphics2D) bufferImage.getGraphics();
        }
        return graphics;
    }

}
