package labb3;


import javax.swing.tree.*;
/**
 *
 * @author Vilhelm
 */

public class MyNode extends DefaultMutableTreeNode{
   // String variablerna
    public String text;
    public String level;
    
    public MyNode(String nodeName, String textIn, String Level){
        //Konstruktorn ger noden värderna 
        //Super roapr på subklassen
        
        super(nodeName);
        text = (textIn);
        level = (Level);
        
        
    }
    
    public String getText(){
        return text;
    }

    String getRundown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}