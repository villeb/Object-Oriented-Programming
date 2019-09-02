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

public class DirTreeC2 extends JFrame implements ActionListener {
   private JCheckBox box;
   private JTree tree;
   private DefaultMutableTreeNode root;
   private DefaultTreeModel treeModel;  
   private JPanel controls;
   private static final String closeString = " Close ";
   private static final String showString = " Show Details ";

       
    public DirTreeC2() {

        Container c = getContentPane();
        
        /*
        Build the tree and a mouse listener to handle clicks
        */
        try{
        root = readNode();
      }catch(FileNotFoundException e){
          
          /* 
          Det här är om filen ej kan hittas.
          */
          System.out.println("File not found.");
    
      }catch(unbalancedTagsException e){
          
          System.out.println(e);
          
      }catch(XMLFormatException e){
          System.out.println(e);
      }
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        MouseListener ml = new MouseAdapter() {
            
        public void mouseClicked(MouseEvent e) {
            if (box.isSelected()) {
                showDetails(tree.getPathForLocation(e.getX(),
                    e.getY()));}
            }
        };
        tree.addMouseListener(ml);/* build the tree by adding the nodes*/
        controls = new JPanel(); /* panel the JFrame to hold controls and the tree */
        box = new JCheckBox(showString);
        init(); /* set colors, fonts, etc. and add buttons*/
        c.add(controls, BorderLayout.NORTH);
        c.add(tree, BorderLayout.CENTER);
        setVisible(true); /* display the framed window*/
    }

    
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(closeString)) {
            dispose();
            
        }
    }
     
    private void init() { 
        addButton(closeString);
        tree.setFont(new Font("Dialog", Font.BOLD, 12));
        controls.add(box);
        controls.setBackground(Color.lightGray);
        controls.setLayout(new FlowLayout());
        setSize(400, 400);
    }

    /**
     * Makes it possible to click/interact
     */
    private void addButton(String n) {
        JButton b = new JButton(n);
        b.setFont(new Font("Dialog", Font.BOLD, 12));
        b.addActionListener(this);
        controls.add(b);
    }

    /** 
     *  Used to show the message for the information
     */
    private void showDetails(TreePath p) {
        if (p == null) {
            return;
        }
        MyNode currNode = (MyNode) p.getLastPathComponent();
        ArrayList<TreeNode> nodeList = new ArrayList();
                
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
    
    /**
    * Returns the node 
    */
    private MyNode createNode(String[] inputs) {

        String name;
        String level;
        String text;
        name = inputs[2];
        level = inputs[0];
        text = inputs[1];

        return new MyNode(name, text, level);
    }
        
    /**
    *This method exists so we can handle names with cap
    */
    public String getName(String argLine) {
        String empty = "";
        int citIndex = argLine.indexOf("\"");
        if (citIndex != -1) {
            String name = argLine.substring(citIndex + 1,
                                            argLine.indexOf("\"", citIndex + 1));
            return name;
        }
       
        return empty;
    }
    
    /** 
    *removes all the things from a line
    */
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
    
    /**
      Definerar en clean sträng och gör den till en matris med level,text,namn
     *split("TECKEN",integer): splittar upp en string till en array som är integer lång, vid varje TECKEN ( tills den är integer lång)
    */
     private String[] makeAttributes(String inLine) {

        String line = cleanLine(inLine);
        String[] output = {line.split(" ", 2)[0], line.split(" ", 2)[1], getName(inLine)};

        return output;
}

    /**
    * Loads the file and catch an exception
    */ 
    private Scanner scanFile() {
        Scanner sc;
        try {
            sc = new Scanner(new File("Liv.xml"));
        }  catch (FileNotFoundException e) {
           System.out.println("Error: " + e.toString());
            return null;
        }
        
        return sc;
    }
    
    /**
     * Checks all the possible errors in the XML file on a line.
     * indexOf() returns -1 if the string don't exsists
     */
    
    public void lineChecker(String argLine) throws XMLFormatException{
       String checkedLine = argLine;
       String[] splitLine = checkedLine.split(""); 
       int rightArrowIndex = checkedLine.indexOf(">");
       int nameIndex =checkedLine.indexOf("namn=");
       int firstCitIndex = checkedLine.indexOf("\"");
       int secondCitIndex = checkedLine.indexOf("\"",firstCitIndex+1);
       
       if(rightArrowIndex==-1){
           
           throw new XMLFormatException("Line missing an \">\".");
       }
       
        /* This if is checking if the line is an endTag*/
        if(splitLine[1].equals("/")){
            if(checkedLine.indexOf(" ")!= -1 ||
                    rightArrowIndex!=(checkedLine.length()-1) ){
                throw new XMLFormatException("Wrong close-tag formatting.");
            }
            
        return; 
        }
       
        if(!splitLine[0].equals("<")){        /*Every line should start on a < */
           throw new XMLFormatException("Line missing an opening \"<\".");
       }
       
        /*This part checks the order*/
        if(nameIndex == -1){
           throw new XMLFormatException("Line missing a name.");
       }
       
       if(firstCitIndex ==-1 || secondCitIndex == -1){
           throw new XMLFormatException("Line missing a \".");
       }
       
       if((nameIndex+5 != firstCitIndex)||(secondCitIndex+1) != rightArrowIndex){
           throw new XMLFormatException("Line not ordered properly.");
       }
       
   }
   /**
    * Checks and makesure we have the right endkey,  
    */
   public void endKeyCheck(String endKey, String nextLine) throws
           unbalancedTagsException{
        if(nextLine.split("")[1].equals("/")&& 
            !cleanLine(nextLine.split(" ")[0]).equals(endKey)){
                    throw new unbalancedTagsException(
                            ".xml file does not have valid"
                                    + " tags!");
       }
       
   }
   
   /**
    * Handeling the steping for the scanner. also checks for error in file 
    */
   public String handleNextLine(Scanner argScanner, String endKey) 
           throws unbalancedTagsException, XMLFormatException{
        String nextLine = argScanner.nextLine();
        lineChecker(nextLine);
        endKeyCheck(endKey, nextLine);
       
        return nextLine;
        }
   
   /**
    * Same as above but when you dont need to check the endKey
    */
   public String handleNextLine(Scanner argScanner) throws XMLFormatException{
       String nextLine = argScanner.nextLine();
       lineChecker(nextLine);
       
       return nextLine;
       }
    
    /**
    * Returns the Node that will meet the requirements 
    */
     public MyNode readNode()
            throws FileNotFoundException, unbalancedTagsException, XMLFormatException {

        Scanner xmlScan = scanFile(); // Scanner 
        xmlScan.nextLine(); //Skippar första xml-raden tekniska raden
        String currLine = xmlScan.nextLine(); //Nuvarande rad vi är på
        String nextLine ="this is a placeholder line"; //exakt vad de står
        String[] attributes = makeAttributes(currLine); // Gör en matris med level, text och namn
        String endTag = "/" + attributes[0]; // denfinierar sluttaggen </...>
        MyNode retNode = createNode(attributes); // Skapar Noden 
        nextLine = handleNextLine(xmlScan, endTag);
        
        /*
        Check if the next line is the endtag if not it calls on readNode with argument
         */
        while (!cleanLine(nextLine).split(" ")[0].equals(endTag)) {
            readNode(retNode, nextLine,endTag, xmlScan);
            nextLine = handleNextLine(xmlScan);
        }
        xmlScan.close();
        return retNode;

    }

    /**
    *This method is used for recusion inside readNode() its pretty much the same except that it adds its parentode and dont initiate any scanner
    */
    public MyNode readNode(MyNode parent, String inLine, String parentEndKey,Scanner inScan) 
            throws unbalancedTagsException, XMLFormatException {

        Scanner xmlScan = inScan;
        String[] attributes = makeAttributes(inLine);
        String endTag = "/" + attributes[0];
        MyNode retNode = createNode(attributes);
        parent.add(retNode);
        String nextLine = handleNextLine(xmlScan,endTag);

        /* Changed compare to the one above*/
        while(!cleanLine(nextLine).split(" ")[0].equals(endTag)){
            readNode(retNode, nextLine,endTag,xmlScan);
            if(xmlScan.hasNext()){
                nextLine = handleNextLine(xmlScan, endTag);
              }
            else{ //If the scanner runs out of the list befor it finds the endTag we have a problem
                
                throw new unbalancedTagsException(".xml file does not have"
                          + " balanced tags!");
              }
       }

        return retNode;

    }
  
    public static void main(String[] args) {
        
        new DirTreeC2();
    }

}

  