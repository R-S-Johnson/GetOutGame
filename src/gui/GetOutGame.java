package gui;

import javax.swing.JFrame;
import java.awt.event.KeyListener;

public class GetOutGame extends JFrame{
    
    GetOutGame() {
        Controller controller = new Controller();
        add(controller.getPanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        KeyListener keyListener = new InputListener(this, controller);
        addKeyListener(keyListener);

        setSize(Constants.VIEW_WIDTH+22, Constants.VIEW_HEIGHT+22);
        setVisible(true);

        setFocusable(true);

        controller.start();
    }

    public static void main(String[] args) {
        GetOutGame a = new GetOutGame();
        a.repaint();
    }

}
