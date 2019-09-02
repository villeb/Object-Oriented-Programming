package Human;
/**
 *
 * @author Vilhelm
 */
public class Fysiker extends Human{



    //Field

    private int year;
    
    // Constructor

    public Fysiker(int ageln, String nameln, int yearln){   // Måste kalla på Humen konstruktorn i Fysik konstruktorn
	super(ageln,nameln); // Spelar ingen roll att fälten i Humen är privat då vi endast kallar på konstruktorn
        year = yearln;
         
    }
        
    
    public Fysiker(){
        this( (int)(Math.random()*100),names[(int)(Math.random()*(names.length-1))],(int)(Math.random()*(2015-1932+1) + 1932));

    
    
    
     if( getAge()- 15 < 2018- year){
            throw new IllegalArgumentException("Age too high: "+getAge());
        
     }
    }
    	
    //Methods     
    public int getYear(){
         return year;
     }
     
    public int compareTo(Human o){
    	int zero = 0;
        if (o instanceof Fysiker){
            if  (((Integer)getAge()).compareTo(o.getAge()) == zero ) {
            	return ((Integer)getYear()).compareTo(((Fysiker) o).getYear());
            }
            else {
                return ((Integer)getAge()).compareTo(o.getAge());    
                    }
        }
        else {
            //är Human objekt
            return ((Integer)getAge()).compareTo(o.getAge());
        }
    } 
     
    public String toString(){
        
        String year_str = Integer.toString(year);
        String xx = year_str.substring(2,4);
        String Argang = String.format("F%s",xx) ;
        
       return "namn:" + getName() + " Ålder:"+ getAge() +" år" + " Började Fysik:"+ Argang;}
       //return "namn:" + name + " Ålder:"+ age +" år" + " Började Fysik:"+ year;}

    
}
