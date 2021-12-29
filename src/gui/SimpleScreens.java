package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


/**
 * Responsible for the creation of
 * screens that aren't the playing
 * screen
 */
public class SimpleScreens {

    private static final int SIZE = Constants.VIEW_HEIGHT;


    public void drawTitle(MapPanel panel) {
        Graphics bufferGraphics = panel.getBufferGraphics();

        // Draw the background
        bufferGraphics.setColor(Color.black);
        bufferGraphics.fillRect(0, 0, SIZE, SIZE);

        bufferGraphics.setColor(Color.red);
        bufferGraphics.fillRect(Constants.MARGIN/2, Constants.MARGIN/2, SIZE - Constants.MARGIN, SIZE - Constants.MARGIN);

        bufferGraphics.setColor(Color.white);
        bufferGraphics.fillRect(Constants.MARGIN, Constants.MARGIN, SIZE - Constants.MARGIN*2, SIZE - Constants.MARGIN*2);

        // Write title
        bufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 48));
        bufferGraphics.setColor(Color.black);
        String title = "Get Out!";
        bufferGraphics.drawString(title, SIZE - bufferGraphics.getFontMetrics().stringWidth(title), Constants.MARGIN);
    }

}
