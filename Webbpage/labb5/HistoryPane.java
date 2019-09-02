/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb5;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
/**
 *
 * @author Vilhelm
 */
public class HistoryPane extends JTextPane implements HyperlinkListener{
    
    private ViewPane viewPane;
    
    public HistoryPane(ViewPane v){
        viewPane = v;
        setVisible(false);
        setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        setEditable(false);
        setPreferredSize(new Dimension(250,600));
        addHyperlinkListener(this);
    }

    public void displayHistoryPane(){
        if (!this.isVisible()){
            this.setVisible(true);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < viewPane.historyURL.size() ; i++){
                
                sb.append(("<a href=" + viewPane.historyURL.get(i).toString() + 
                        ">" + viewPane.historyURL.get(i).toString() + "</a>" + "<br>"));
                
            }
            this.setText(sb.toString());
        }
        else{
            this.setVisible(false);
        }
    }
    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        viewPane.hyperlinkUpdate(e);
    }
}
