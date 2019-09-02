/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb5;

import java.awt.event.*;
import java.awt.*;
import java.net.*;
/**
 *
 * @author Vilhelm
 */
public class URLField extends TextField implements ActionListener {
    private URL url;
    private ViewPane viewer;
    
    public URLField(ViewPane v){
        viewer = v;
        addActionListener(this);
        setPreferredSize(new Dimension(800,25));
        
    }
    public URL  getURL(){
        return url;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        
        try{
            url = new URL(getText());
        }
        catch (MalformedURLException ex) {
            System.err.println("Attemped to read a bad URL: " + url);
        }
        viewer.navigate(url);
    }
}
