package labb2;

import mybutton.MyButton2;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Vilhelm 
 */
public class ram3 extends JApplet {

// Använder init istället för konstruktor, should be short 
    public void init() {
        createGUI();
    }

    private void createGUI() {
        //Create and set up the content pane.

        MyButton2 knapp = new MyButton2();
        knapp.setPreferredSize(new Dimension(600, 400));
        add(knapp);
        knapp.setOpaque(true);
        knapp.setBorderPainted(false);
        setVisible(true);

    }
}