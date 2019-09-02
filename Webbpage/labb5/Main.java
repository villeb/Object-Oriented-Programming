package labb5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



/**
 *  
 * @author hugo
 * Huvudfönstret
 */
public class Main extends JFrame {
    
    private URLField urlField;
    private ViewPane viewPane;
    private HistoryPane historyPane;
    private JScrollPane scrollPane;
    private JPanel container;
    private JPanel navBarPanel;
    private JButton closeButton;
    private NavButton back;
    private NavButton forward;
    private NavButton history;
    
    
    public Main(){
        super("Webbrowser");
        container = new JPanel();
        container.setPreferredSize(new Dimension(1200,900));
        viewPane = new ViewPane();
        scrollPane = createScrollPane(viewPane);
        urlField = new URLField(viewPane);
        closeButton = new CloseButton(this);
        historyPane = new HistoryPane(viewPane);
        
         
        handleLayout();
        add(container);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
    * En metod som skapar ett fönster som har scrollbar och lägger argumentet i den.
    */
    private JScrollPane createScrollPane(JEditorPane inPane){
        JScrollPane scroller = new JScrollPane(inPane);
        scroller.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(800, 600));
        scroller.setMinimumSize(new Dimension(800, 600));
        return scroller;
    }
    
    /**
    * En riktig shitfest, denna metod sätter in allt i container och ser till att det ser snyggt ut
    * mha GridBagLayout vilket påstås vara den värsta swing klassen av dem alla.
    */
    private void handleLayout() {
        
        JLabel label = new JLabel("Webbrowser");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        
        container.add(label,gbc);
        gbc.gridy++;
        
        container.add(createNavBar(), gbc);
        gbc.gridy++;
        
        container.add(urlField, gbc);
        gbc.gridy++;
        
        container.add(scrollPane, gbc);
        gbc.gridx++;
        container.add(historyPane, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        container.add(closeButton, gbc);
        gbc.gridy++;
        
        
    }
    
    /**
    * Skapar navigeringsraden (Del-C)
    */
    private JPanel createNavBar(){
        
        navBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        back = new NavButton("BACK");
        history = new NavButton("HISTORY");
        forward = new NavButton("FORWARD");
        navBarPanel.add(back);
        navBarPanel.add(history);
        navBarPanel.add(forward);

        return navBarPanel;
    }
    
    public static void main(String[] args){
        new Main();
    }
    

    
 
   
public class NavButton extends JButton implements ActionListener {
    
    
    public NavButton(String text){
        
        setBackground(Color.lightGray);
        setText(text);
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (getText().equals("BACK")){
            viewPane.goBackward();
        }
        if (getText().equals("FORWARD")){
            viewPane.goForward();
        }
        if (getText().equals("HISTORY")){
            historyPane.displayHistoryPane();
        }
    }
    
}

    
    
}