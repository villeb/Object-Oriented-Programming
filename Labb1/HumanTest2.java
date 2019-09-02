package Human;

//Ramprogram E3


public class HumanTest2 {
    public static void main( String [] args){

	Human[] Humansobj = new Human[15]; //Array of objects
        
        for (int i = 0; i < 15; i++){
            
            Humansobj[i] = new Human();
            //Creates random Human objects
            
            System.out.println(Humansobj[i]);

            
            
        }
        
	 
   
    
    }
}

