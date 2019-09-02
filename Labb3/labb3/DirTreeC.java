package labb3;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Vilhelm
 */
public class DirTreeC extends JFrame implements ActionListener {
   private JCheckBox box;
   private JTree tree;
   private DefaultMutableTreeNode root;
   private DefaultTreeModel treeModel;  
   private JPanel controls;
   private static final String closeString = " Close ";
   private static final String showString = " Show Details ";

    
    
    


    public DirTreeC() {

        Container c = getContentPane();
        //*** Build the tree and a mouse listener to handle clicks

        root = readNode();

        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        MouseListener ml
                = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (box.isSelected()) {
                    showDetails(tree.getPathForLocation(e.getX(),
                            e.getY()));
                }
            }
        };
        tree.addMouseListener(ml);
        //*** build the tree by adding the nodes

        //*** panel the JFrame to hold controls and the tree
        controls = new JPanel();
        box = new JCheckBox(showString);
        init(); //** set colors, fonts, etc. and add buttons
        c.add(controls, BorderLayout.NORTH);
        c.add(tree, BorderLayout.CENTER);
        setVisible(true); //** display the framed window
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(closeString)) {
            dispose();
        }
    }

    private void init() {  // Istället för main-metod
        tree.setFont(new Font("Dialog", Font.BOLD, 12));
        controls.add(box);
        addButton(closeString);
        controls.setBackground(Color.lightGray);
        controls.setLayout(new FlowLayout());
        setSize(400, 400);
    }

    private void addButton(String n) {
        JButton b = new JButton(n);
        b.setFont(new Font("Dialog", Font.BOLD, 12));
        b.addActionListener(this);
        controls.add(b);
    }

   // Ger en arraylista med alla förändrar 
    //E2.3
    private void showDetails(TreePath p) {
        if (p == null) {
            return;
        }
        // Hämtar noden man klickar på
        MyNode currNode = (MyNode) p.getLastPathComponent();
        ArrayList<TreeNode> nodeList = new ArrayList(); // skapar en arraylist
                
        TreeNode node = currNode.getParent();        
        while (node != null){
            
            nodeList.add(node);
            node = node.getParent();
            
        }
        String text = currNode.toString();
        
        
        for (int i = 0; i < nodeList.size(); i++){
            
            
            text =  text + " är " + nodeList.get(i);
        }
            
        
        JOptionPane.showMessageDialog(this, "men allt som är " + text );
    }

    
      
    
        
    // Definerar en clean sträng och gör den till en matris med level,text,namn
    //split("TECKEN",integer): splittar upp en string till en array som är integer lång, vid varje TECKEN ( tills den är integer lång)
      
    private String[] makeAttributes(String inLine) {

        String line = cleanLine(inLine);
        String[] output = {line.split(" ", 2)[0], line.split(" ", 2)[1], getName(inLine)};

        return output;

    }

    //Skapar Noder 
    private MyNode createNode(String[] inputs) {

        String name;
        String level;
        String text;

        
        name = inputs[2];
        level = inputs[0];
        text = inputs[1];

        return new MyNode(name, text, level);

    }
    /*
    Denna metod existerar för att man ska kunna klara av namn med mellanslag i
    */
    public String getName(String argLine) {
        int citIndex = argLine.indexOf("\"");
        if (citIndex != -1) {
            String name = argLine.substring(citIndex + 1,
                                            argLine.indexOf("\"", citIndex + 1));
            return name;
        }
        String empty = "";
        return empty;

    }

    // Gör om raden så vi blir av med alla tecken
    private String cleanLine(String line) {
        String outLine = line;
        outLine = outLine.replace("<", "");
        outLine = outLine.replace(">", "");
        outLine = outLine.replace("namn=", "");
        outLine = outLine.replace("\"", "");

        String name = getName(line);
        if (!name.equals("")) {
            outLine.replace(name, "");
        }

        return outLine;
    }

    // Läser in filen vi vill använda. catchar ett exception för felmeddelande. 
    private Scanner scanFile() {
        Scanner sc;
        try {
            sc = new Scanner(new File("Liv.xml"));
        } catch (FileNotFoundException e) {
           System.out.println("Error: " + e.toString());
            return null;
        }
        return sc;
    }
    
    
    
    public MyNode readNode() {

        Scanner xmlScan = scanFile(); // Scanner 
        xmlScan.nextLine(); //Skippar första xml-raden tekniska raden
        String currLine = xmlScan.nextLine(); //Nuvarande rad vi är på
        String[] attributes = makeAttributes(currLine); // Gör en matris med level, text och namn
        String endTag = "/" + attributes[0]; // denfinierar sluttaggen </...>

        MyNode retNode = createNode(attributes); // Skapar Noden 

        String nextLine = xmlScan.nextLine(); // Skapar nästa rad
        /*
        While loopen kollar om nästa rad är sluttaggen. Om den inte är det kallar den på
        readNode som har argument.
         */
        while (!cleanLine(nextLine).split(" ")[0].equals(endTag)) {
            readNode(retNode, nextLine, xmlScan);
            nextLine = xmlScan.nextLine();
        }
        xmlScan.close();
        return retNode;

    }

    /*
    Denna metod används för rekursion inuti readNode(), den är i princip likadan förutom att den
    lägger till sin parentNode, och inte initierar nån scanner.
     */
    public MyNode readNode(MyNode parent, String inLine, Scanner inScan) {

        Scanner xmlScan = inScan;
        String[] attributes = makeAttributes(inLine);
        String endTag = "/" + attributes[0];

        MyNode retNode = createNode(attributes);
        parent.add(retNode);
        String nextLine = xmlScan.nextLine();
        while (!cleanLine(nextLine).split(" ")[0].equals(endTag)) {
            readNode(retNode, nextLine, xmlScan);
            nextLine = xmlScan.nextLine();
        }

        return retNode;

    }
    
   
    

    public static void main(String[] args) {
        
        new DirTreeC();
    }

}

  