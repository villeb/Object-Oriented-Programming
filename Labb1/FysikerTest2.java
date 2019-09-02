
package Human;
import java.util.Arrays;
/**
 *
 * @author Vilhelm
 */

public class FysikerTest2 {
    public static void main(String[] args) {
        Human[] fysikerList = new Human[500];
        
       for (int i=0; i<250; i++){
            int count = 0;
            int maxTries=20;   
            while (count <= maxTries){
                try {
       
                    fysikerList[i]=new Fysiker();
                
                    }
                //Catch contains code that is executed if the exception handler is invoked
                catch(IllegalArgumentException e) {
                    count++;
                    }
                }
       }
       
       
       
      for (int i=250; i < 500; i++){
       fysikerList[i] = new Human();
       
      }
      
       Arrays.sort(fysikerList);
       for (Human fysikerList1 : fysikerList) {
            System.out.println(fysikerList1);
        }
     } 
}