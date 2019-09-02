
package labb4;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.PrintWriter;
/**
 *
 * @author vilhelm
 */
public class Controller extends JPanel implements ChangeListener, ActionListener {

    private View myView;
    private Model myModel;
    private Timer myTimer;
    private JSlider lengthSlider;
    private JSlider deltaSlider;
    private StringBuilder outputs;
    private PrintWriter outputStream = null;
    private int timeElapsed = 0;
    private static final int L_MIN = 0;
    private static final int L_MAX = 100;
    private static final int L_INIT = 5;
    private static final int DELTA_MIN = 0;
    private static final int DELTA_MAX = 500;
    private static final int DELTA_INIT = 50;
    private static final int X_SIZE = 800;
    private static final int Y_SIZE = 600;
    private static final int logTime = 1000;

    public Controller(Model modelArg, View viewArg) {

        myModel = modelArg;
        myView = viewArg;
        createFile();
        myTimer = new Timer(DELTA_INIT, this);
        myTimer.start();
        
        
        lengthSlider = new JSlider(JSlider.HORIZONTAL, L_MIN, L_MAX, L_INIT);
        lengthSlider.addChangeListener(this);
        add(lengthSlider);

        deltaSlider = new JSlider(JSlider.HORIZONTAL, DELTA_MIN, DELTA_MAX, DELTA_INIT);
        deltaSlider.addChangeListener(this);
        add(deltaSlider);
        
        
    }

   // @Override
    public void stateChanged(ChangeEvent e) {

        JSlider source = (JSlider) e.getSource();

        if (source == lengthSlider) {
            double l = (double) source.getValue();
            myModel.setLength(l);
        }
        if (source == deltaSlider) {
            myTimer.setDelay(source.getValue());
        }

    }
    
    // Creates a row with time,x,y Use StringBuilder when we change it.
    private StringBuilder createLogString(int timeIn){
        double[] positions = myModel.getTotalPosition();
        outputs = new StringBuilder(positions.length + 1);
        outputs.append(timeIn);
        for (int i = 0; i < positions.length; i = i + 2) {
            double x = positions[i] * X_SIZE;
            double y = positions[i + 1] * Y_SIZE;
            outputs.append(",");
            outputs.append(x); 
            outputs.append(",");
            outputs.append(y);
            
        }
        
        return outputs;
    }
    
    //@Uppdates the model and calls on repaint
    public void actionPerformed(ActionEvent e) {
        myModel.updateTotalPosition();
        myView.repaint();
        if (timeElapsed <= logTime) {
            StringBuilder fileRow = createLogString(timeElapsed);
            addToFile(fileRow);
            timeElapsed = timeElapsed + deltaSlider.getValue();
              
        } else {
            outputStream.close();    
        }
        
    }
    
    
    private void createFile(){
        try{
            outputStream = new PrintWriter("output.csv");
        }
        catch(Exception e){
             System.out.println("Error: " + e.toString());
        }
      
        
    }   
    private void addToFile(StringBuilder input){
        outputStream.println(input);
        outputStream.flush();
    }
}
