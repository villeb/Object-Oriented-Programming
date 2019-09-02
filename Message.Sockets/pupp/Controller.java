/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pupp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Vilhelm
 */
public class Controller implements ActionListener{
    

    private Model myModel;
    private View myView;
    private String userName = "Putte";
    private Color textColor = Color.BLACK;
    private boolean running = true;

    
    
    // Lyssnar på text,färg. Sparar det som skrivs in
    public Controller(Model m, View v){
        myModel = m;
        myView = v;
        myView.sendField.addActionListener(this);
        myView.nameField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = ((JTextField)e.getSource()).getText();
            }
            
        });
        myView.colorField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String colHex = ((JTextField)e.getSource()).getText();
                textColor = Color.decode(colHex);
            }
            
        });
        
        // Lyssnar alltid efter meddelanden som skickas (receiveMessage)
        Thread thr = new Thread(new Runnable(){
            @Override
            public void run() {
                while(running){
                   
                    try{
                        System.out.println("try it bro");
                        receiveMessage();
                    }
                    catch(Exception AAA){
                        System.out.println("Error in receivethread " + AAA);
                    }
                }
            }
            
        });
        thr.start();
    }

    public void sendMessage(String s){
        Message m = new Message(s,userName,textColor);
        myModel.sendMessage(m.getXMLText());
        myView.showMessage(m);
    }
    
    public void receiveMessage(){
        System.out.println("3. attempted to receive");
        Message m = myModel.receiveMessage();
        if(m.getDisconnect()){
            running = false;
            
        }
        System.out.println("received: " + m.getText());
        myView.showMessage(m);
       
    }
    
  
    @Override
    public void actionPerformed(ActionEvent e) {
        sendMessage(((JTextField)e.getSource()).getText());
        ((JTextField)e.getSource()).setText("");
        
    }
}