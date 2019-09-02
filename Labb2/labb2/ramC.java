package labb2;

import mybutton.MyButton2;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Vilhelm
 */
public class ramC {
    private static JFrame frame;
    private static String[] texts = {"Trump","Bush","Reagan","Ford","Nixon","Eisenhower","Hoover","Coolidge","Harding"};
    private static Color[] colors = {Color.red,Color.blue,Color.yellow,Color.orange,Color.green,Color.gray,Color.pink,Color.cyan,Color.magenta};
    

    public static void main(String[] args){
        int numButtons;
        frame = new JFrame();
        FlowLayout layout = new FlowLayout();
        frame.setLayout(layout);
        if(args.length>1){ // Checks if multiple arguments have been sent
            throw new IllegalArgumentException("Multiple arguments!");
        }
        
        try{ // tries to convert the string to an integer
            numButtons = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            // should an exception occur, thrown
    
            throw new IllegalArgumentException("Argument not an integer!");    
        }
        
        createButtons(numButtons);
        
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
        
        private static void createButtons(int k){
            MyButton2[] b = new MyButton2[k];
           
            for (int i= 0; i < k; i++){
                b[i] = new MyButton2(colors[(int)(Math.random()*(colors.length-1))], 
                                     colors[(int)(Math.random()*(colors.length-1))],
                                      texts[(int)(Math.random()*(texts.length-1))],
                                      texts[(int)(Math.random()*(texts.length-1))]);                
            
            
        
        
                frame.add(b[i]);
                b[i].setOpaque(true);
                b[i].setBorderPainted(false);
                b[i].setEnabled(true);
                b[i].doClick();
            }
        }
        
}
                
            
        
            
            
 