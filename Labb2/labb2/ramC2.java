/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb2;

import mybutton.MyButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Vilhelm
 */
public class ramC2 implements ActionListener {
    
    private JFrame frame;
    private static String[] texts = {"Trump","Bush","Reagan","Ford","Nixon","Eisenhower","Hoover","Coolidge","Harding"};
    private static Color[] colors = {Color.red,Color.blue,Color.yellow,Color.orange,Color.green,Color.gray,Color.pink,Color.cyan,Color.magenta};
    private MyButton[] buttons;
    private int numButtons;
    
    public ramC2(String[] args){
        frame = new JFrame();
        FlowLayout layout = new FlowLayout();
        frame.setLayout(layout);
        if(args.length>1){ // Checks if multiple arguments have been sent
            throw new IllegalArgumentException("Multiple arguments!");
        }
        
        try{ // tries to convert the string to an integer
            numButtons = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            /* should an exception occur this is 
            thrown
            */
            throw new IllegalArgumentException("Argument not an integer!");    
        }
        
        createButtons();
       
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void createButtons(){
        buttons = new MyButton[numButtons];
        for (int i = 0; i < numButtons; i++){
            buttons[i] = new MyButton(colors[(int)(Math.random()*(colors.length-1))],
                                      colors[(int)(Math.random()*(colors.length-1))],
                                      texts[(int)(Math.random()*(texts.length-1))],
                                      texts[(int)(Math.random()*(texts.length-1))]);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            buttons[i].setEnabled(true);

            
        }
        
    }
    
    public static void main(String[] args){
        new ramC2(args);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < numButtons; i++){
            if (!e.getSource().equals(buttons[i])){
                buttons[i].toggleState();
            }
        }

    }
  
}