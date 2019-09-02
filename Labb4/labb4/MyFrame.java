package labb4;

import java.awt.Color;
import javax.swing.*;
/**
 *
 * @author Vilhelm
 */
public class MyFrame extends JFrame {

    private View myView;
    private Model myModel;
    private Controller myController;

    public MyFrame() {

        myModel = new Model(10000);
        myView = new View(myModel);
        myController = new Controller(myModel, myView);
        myView.add(myController);
        add(myView);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.green);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}