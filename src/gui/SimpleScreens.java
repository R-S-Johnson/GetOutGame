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
        drawBackground(bufferGraphics);

        // Write title
        bufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 48));
        bufferGraphics.setColor(Color.black);
        String title = "Get Out!";
        bufferGraphics.drawString(title, (SIZE - bufferGraphics.getFontMetrics().stringWidth(title))/2, Constants.MARGIN + 50);

        // Write difficulty instructions
        bufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 16));
        String difficultyInstructions = "Select a difficulty";
        String difficultyOptions = "(Press 1-5)";
        int xPos = (SIZE - bufferGraphics.getFontMetrics().stringWidth(difficultyInstructions))/2;
        bufferGraphics.drawString(difficultyInstructions, xPos, SIZE/2);
        xPos = (SIZE - bufferGraphics.getFontMetrics().stringWidth(difficultyOptions))/2;
        bufferGraphics.drawString(difficultyOptions, xPos, SIZE/2 + 22);

        // Write instructions option
        String instructions = "How to Play (I)";
        bufferGraphics.drawString(instructions, Constants.MARGIN, SIZE - Constants.MARGIN - 10);
    }


    public void drawLoadingScreen(MapPanel panel) {
        Graphics bufferGraphics = panel.getBufferGraphics();

        drawBackground(bufferGraphics);

        // Write loading..
        bufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 22));
        bufferGraphics.setColor(Color.black);
        String loading = "Loading map...";
        bufferGraphics.drawString(loading, (SIZE - bufferGraphics.getFontMetrics().stringWidth(loading))/2, SIZE/2 + Constants.MARGIN);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, SIZE, SIZE);

        g.setColor(Color.red);
        g.fillRect(Constants.MARGIN/2, Constants.MARGIN/2, SIZE - Constants.MARGIN, SIZE - Constants.MARGIN);

        g.setColor(Color.white);
        g.fillRect(Constants.MARGIN, Constants.MARGIN, SIZE - Constants.MARGIN*2, SIZE - Constants.MARGIN*2);
    }

}
