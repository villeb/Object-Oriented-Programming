//E3
package labb2;

import mybutton.MyButton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Vilhelm
 */
public class tram2 implements ActionListener {

    private MyButton knapp;

    //KONSTRUKTOR
    public tram2() {
        JFrame frame = new JFrame();
        knapp = new MyButton();
        knapp.setPreferredSize(new Dimension(300, 200));
        frame.add(knapp);
                // Pack method causes the window to be sized to fit the preferred size and layout of its subcompunent
        frame.pack();
                //Makes the window and its subcomponent visible 
        frame.setVisible(true);
                //programet slutas köra när man kryssar fönstret
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        knapp.addActionListener(this);
        knapp.setOpaque(true);
        knapp.setBorderPainted(false);


    }

    public static void main(String[] args) {
        new tram2();
    }
//Ändras när vi klickar på knappen. Då klassen lyssnar på knappen
    public void actionPerformed(ActionEvent e) {
        knapp.toggleState();
    }

}