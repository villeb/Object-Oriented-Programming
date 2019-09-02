
package Human;
import java.util.Arrays;
/**
 *
 * @author Vilhelm
 */
public class FysikerTest {
    public static void main(String[] args) {
        
       Fysiker[] fysikerArray = new Fysiker[15];
       for (int i=0; i<15; i++){
            int count = 0;
            int maxTries=20;
               
            while (count <= maxTries){
                try {
       
                    fysikerArray[i]=new Fysiker();
                
                    }
                catch(IllegalArgumentException e) {
                    count++;
                    }
                }
       }
       Arrays.sort(fysikerArray);
       for (Fysiker fysikerList1 : fysikerArray) {
            System.out.println(fysikerList1);
        }
     } 
}