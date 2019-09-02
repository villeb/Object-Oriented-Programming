/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pupp;

import java.awt.Color;


/**
 *
 * @author Hugo
 */
public class Message {
    
    private String userName;
    private Color textColor;
    private String xmlMessage;
    private String text;
    private boolean isDisconnect;
    
    
    public Message(String msg, String name,Color inCol){
        userName = name;
        textColor = inCol;
        text = convertXML(msg);
        
        int r = textColor.getRed();
        int g = textColor.getGreen();
        int b = textColor.getBlue();
        String hex = String.format("#%02X%02X%02X", r, g, b);
        xmlMessage = "<message sender=\"" + userName + "\">" +"<text color=\""
                    +hex+"\">"+text + "</text></message>";
        
    }
    
 
    public Message(String name, String textIn){
        userName = name;
        textColor = Color.BLACK;
        text = convertXML(textIn);
        
        int r = textColor.getRed();
        int g = textColor.getGreen();
        int b = textColor.getBlue();
        String hex = String.format("#%02X%02X%02X", r, g, b);
        xmlMessage = "<message sender=\"" + userName + "\">" +"<text color=\""
                    +hex+"\">"+text + "</text></message>";
        
    }
    
 
    public  void setDisconnect(){
        isDisconnect = true;
        
    }
    
    public boolean getDisconnect(){
        return isDisconnect;
    }
            

    public String getXMLText(){
        return xmlMessage;
    }
    public String getText(){
        return text;
    }
    public String getName(){
        return userName;
        
    }
    public Color getColor(){
        return textColor;
    }
    private String convertXML(String text) {
            text = text.replaceAll( "&","&amp;");
            text = text.replaceAll( ">","&gt;");
            return text.replaceAll( "<","&lt;");
    }   
    
}
