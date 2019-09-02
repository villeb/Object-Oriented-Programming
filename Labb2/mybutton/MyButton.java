//E1


package mybutton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 /**
 *
 * @author Vilhelm
 */

public class MyButton extends JButton {
    //Nödvändiga fält
    private Color c1;
    private Color c2;
    private String firsttext;
    private String sndtext;

    //Konstruktor som sätter kanppen till första tillstådent och färg,text
    public MyButton(Color col1, Color col2, String text1, String text2) {
        setText(text1);
        setBackground(col1);
        c1 = col1;
        c2 = col2;
        firsttext = text1;
        sndtext = text2;


    }

    public MyButton() {
        this(Color.GREEN, Color.RED, "ON", "OFF");
    }
    //Används för att byta tillstånd, knapp 
//    public void toggleState() {
//        if (getBackground() == c1) {
//           setBackground(c2);
//            setText(sndtext);
//        } else {
//            setBackground(c1);
//            setText(firsttext);
//        }
//    }
    
    
    public void toggleState() {
        if (getBackground() == c1 & getText()== firsttext){
        
            setBackground(c2);
            setText(sndtext);
        } else {
            setBackground(c1);
            setText(firsttext);
        }
    }
    
    
    
    
    

}