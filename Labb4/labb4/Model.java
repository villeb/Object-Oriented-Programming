
package labb4;
/**
 *
 * @author vilhelm
 */
public class Model {

    private static double L = 1;
    private Particle[] particleArray;

    /**
 * Defines how many particles we have
     */
    public Model(int particleNum) {
        particleArray = new Particle[particleNum];
        for (int i = 0; i < particleNum; i++) {
            particleArray[i] = new Particle();
        }
    }

    public void updateTotalPosition() {
        for (int i = 0; i < particleArray.length; i++) {
            particleArray[i].updatePosition();
        }
    }

    /**
    * Returns the total positions of the particles
    */
    public double[] getTotalPosition() {
        double[] positionArray = new double[particleArray.length * 2];
        int index = 0;

        for (int i = 0; i < particleArray.length*2; i = i + 2) {
            positionArray[i] = particleArray[index].getX();
            positionArray[i + 1] = particleArray[index].getY();
            index++;
        }
        return positionArray;

    }

    /**
     * 
     * Sets the length
     */
    public void setLength(double argL) {
        L = argL;
    }

   
    public double getLength() {
        return L;
    }
    

    // inre klass, kan bara instansieras i en instans av denyttre klassen.tillgång till alla privata fält,metoder
    private class Particle {
        boolean locked; 
        private double x;
        private double y;

        
        public Particle(double xIn, double yIn) {
            x = xIn;
            y = yIn;
           locked = false;
        }
        
        public void setPos(double argX, double argY) {
            x = argX;
            y = argY;
        }
         public void setX(double argX){
            x = argX;
    }
    
        public void setY(double argY){
            y = argY;
    }
        
        public boolean isLocked(){
            return locked;
            
        }
             
        public void lock(){
            int m_x = (int) Math.floor(x/50);
            int m_y = (int) Math.floor(y/50);
      //      Model.this.addParticleToMatrix(m_x,m_y,this);
            locked = true;
            
        }
        
         public double getDist(double argX, double argY){
            return Math.sqrt((x-argX)*(x-argX) + (y-argY)*(y-argY));
        }
        
            
        /**
        * Constructor that gives a random position
        *Undvika kodupprepning
        */
        public Particle() {
            this(Math.random(), Math.random());
        }

        public void updatePosition() {
            double theta = Math.random() * 2 * 3.1415;
            x = x + 0.01 * L * Math.cos(theta); //multiplicerar L med 0.01 eftersom i slidern i Controller måste det vara en int och jag vill ha ett litet tal.
            y = y + 0.01 * L * Math.sin(theta);
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
    
        }
    }
    }