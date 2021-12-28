package gui;

import javax.swing.JFrame;
import java.awt.event.KeyListener;

public class GetOutGame extends JFrame{
    
    GetOutGame() {
        Controller controller = new Controller();
        add(controller.getPanel());

        KeyListener keyListener = new InputListener();
        addKeyListener(keyListener);

        setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        setVisible(true);

        setFocusable(true);

        controller.start();
    }

}
