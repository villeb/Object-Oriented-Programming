//E1
package Human;


//E1.4 Publika då vi vill kunna ärva från Humans till Fysiker

public class Human implements Comparable<Human>{
    

    //Field//
    private int age;
    private String name;
    static public String[] names = {"Gustav","Karl","Madison","Adam","Sofia","John","Linnea","Ida","Peter","Gert"};
    

    //Constructor//
    public Human(int ageln, String nameln) {
	age = ageln;
	name = nameln;
    }

    // Skapar en slumpmässig person, E3.1
    public Human(){
       	this( (int)(Math.random()*100),names[(int)(Math.random()*(names.length-1))]); //(int)(Math.random()*10000));
	
    }

    //Methods//
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public String toString(){
        return "namn:" + name + " Ålder:"+ age +" år";
    }
    
    //Jämför åldern av Human med godtycklig variabel 
    public int compareTo(Human h){
        return ((Integer)age).compareTo(h.getAge());
        
    
    }

}
	
