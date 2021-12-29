package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Container;

public class InputListener implements KeyListener {

    private Container parent;
    private Controller controller;


    InputListener(Container parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyChar();
        UserInput ui = null;
        int value = 0;

        if (key == 'w') {
            ui = UserInput.up;
        }
        else if (key == 'a') {
            ui = UserInput.left;
        }
        else if (key == 's') {
            ui = UserInput.down;
        }
        else if (key == 'd') {
            ui = UserInput.right;
        }
        else if (49 <= key && key <= 53) {
            value = key - 48;
            ui = UserInput.startGame;
        }
        else if (key == '-') {
            ui = UserInput.zoomout;
        }
        else if (key == '+' || key == '=') {
            ui = UserInput.zoomin;
        }
        else if (key == KeyEvent.VK_ESCAPE) {
            ui = UserInput.exit;
        }
        controller.keyDown(ui, value);
        parent.repaint();
    }


    ///// We don't need these ////

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
