
package labb5;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *
 * @author Vilhelm
 */
public class ViewPane extends JEditorPane implements HyperlinkListener{
    private URL homeURL;
    public URL currentURL;
    public Stack<URL> forwardURL;
    public Stack<URL> backwardURL;
    public Stack<URL> historyURL;
                
    public ViewPane(){
        setPreferredSize(new Dimension(800, 600));
        forwardURL = new Stack<URL>();
        backwardURL = new Stack<URL>();
        historyURL = new Stack<URL>();
        
        
        try {
            homeURL = new URL("https://www.kth.se/utbildning/civilingenjor/teknisk-fysik/studenter-berattar/patrik-teknisk-fysik-300-hp-1.766608");
        }catch (MalformedURLException ex) {
            System.err.println("Attempted to read a bad URL:" + currentURL);
        }
        setEditable(false);
        addHyperlinkListener(this);
        navigate(homeURL);
    }
    
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType()== HyperlinkEvent.EventType.ACTIVATED){
            backwardURL.push(currentURL);
            forwardURL.clear();
            navigate(e.getURL()); 
            
        } 
    }
    
    public void editHistory(){
    if (currentURL != null) {
        if (!historyURL.contains(currentURL)) {
        historyURL.push(currentURL);
            } else {
                historyURL.push(currentURL);
        }
    }
}
   
  
    
    /* 
    * Navigaes to a new page 
    */
    public void navigate(URL inURL){
        editHistory();  
        currentURL=inURL;
        try {
            setPage(currentURL);
        } catch (IOException ex) {
            System.err.println("Attempted to read a bad URL: " + currentURL);
        }
    }
        
    public void goBackward(){
        if(!backwardURL.empty()){
            forwardURL.push(currentURL);
            navigate(backwardURL.pop());
        }
        
    }
        
    public void goForward(){
        if(!forwardURL.empty()){
            forwardURL.push(currentURL);
            navigate(backwardURL.pop());
        }
        
    }
}



