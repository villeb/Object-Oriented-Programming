package labb2;
import java.awt.*;
import javax.swing.*;
import mybutton.MyButton;
//import mybutton.MyButton2;

/**
 *
 * @author Vilhelm
 */

public class ram1 {
    public static void main( String [] args){
        JFrame jframe = new JFrame();
        MyButton knapp = new MyButton();
       // MyButton2 knapp = new MyButton2();

        knapp.setPreferredSize(new Dimension(300, 200));
        knapp.setOpaque(true);
        knapp.setBorderPainted(false);
        jframe.add(knapp);
        // Pack method causes the window to be sized to fit the preferred size and layout of its subcompunent, 
        jframe.pack();
        //Makes the window and its subcomponent visible 
        jframe.setVisible(true);
        //programet slutas köra när man kryssar fönstret
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
    }

}
    
    

