package pupp;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vilhelm
 */
public class View extends JPanel{
    
    JTextField sendField;
    JTextField nameField;
    JTextField colorField;
    private JScrollPane scroller;
    private JTextPane receivePane;
    
    public View(){
        
        setPreferredSize(new Dimension(800,500));
        setVisible(true);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        
        receivePane = new ReceivePane();
       
        nameField = new JTextField("Name",15);
        
        colorField = new JTextField("Color",15);
        
        
        sendField = new JTextField();
        sendField.setPreferredSize(new Dimension(800,50));

        scroller = new JScrollPane(receivePane);
        scroller.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(800, 400));
        scroller.setMinimumSize(new Dimension(800, 400));
        
        add(nameField,gbc);
        gbc.gridy++;
        add(colorField,gbc);
        gbc.gridy++;
        
        add(scroller,gbc);
        gbc.gridy++;
        add(sendField,gbc);
        
        
    }
    
    
     public void showMessage(Message m){
        
        StyledDocument doc = receivePane.getStyledDocument();

        Style style = receivePane.addStyle("Style", null);
        StyleConstants.setForeground(style, m.getColor());
        
        String text = m.getText();
        text = text.replaceAll("&gt;", ">");
        text = text.replaceAll("&lt;", "<");
        text = text.replaceAll("&amp;","&");
        try {
            doc.insertString(doc.getLength(), m.getName() + ": " + text + "\n", style);
        } catch (BadLocationException e) {
        }
        
     }   
    
}