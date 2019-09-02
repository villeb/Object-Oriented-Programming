package Human;
/**
 *
 * @author Vilhelm
 */
public class C3 {
// args = string av inputet
	public static void main(String[] args) {
		Human[] arrayHum = new Human[args.length]; // create an array of objects
                int k = 0;
                // args() = stränga av indata där varje index är ett ord
		for (int i = 0; i < args.length; i++ )	{
			if (args[i].equals("-H"))  {
                                        //Indexen efter -H blir ålder,år
					arrayHum[k] = new Human(Integer.parseInt(args[i+2]), args[i+1]); 
					i += 2;
                                        k++;
			}
			else if (args[i].equals("-F"))	{
                                        // då year endast är två sista bokstäverna
                                        int year;
                                        int x = Integer.parseInt(args[i+3]);
                                        if (x < 32) {
                                            year = 2000 + x;
                                        }
                                        else {
                                            year = 1900 + x;
                                        }
					arrayHum[k] = new Fysiker(Integer.parseInt(args[i+2]), args[i+1], year);
					i += 3;
                                        k++;
			}
                        
                        
                }
                        Human[] arrayHum2 = new Human[k];
                        System.arraycopy(arrayHum, 0, arrayHum2, 0, k);
		
		
                        for (Human i : arrayHum2) {
                            System.out.println(i);
                        }

	}

}