/**
 * Class to model the circles extending the Figure class.
 * @author Juan.
 * @version 2.0.
 */
public class Circle extends Figure {
    private int radius;
            
    private static final double PI=3.141592654;

    /**
     * Constructor that creates a circle from its radious and color.
     * @param radius    The radious of the circle.
     * @param color     The color of the circle.
     */
    Circle(int radius, Color color) {
        super(color);
        if (radius>50){
            this.radius=49;
        }
        else {
        if (radius<0) this.radius=1;
        else this.radius=radius;
        }
    }

    /**
     * Method of the circle that returns its area. Its an implementation of the abstract mehod from the parent class.
     * @return The area of the circle.
     */    
    public double area() { 
        return PI*(this.radius*this.radius)/100;    
    }
    
    /**
     * Method that compares the circle passed by argument with the object from wich the method is invoked in order to see if they are equal.
     * @param circle The circle to compare with.
     * @return True if it is equal, false if it isn't.
     */
    public boolean isEqualTo(Circle circle) {	
        return ((this.color.isEqualTo(circle.getColor()))&&(this.radius==circle.getRadius()));
    }

    /**
     * Method that returns a String with information about the circle radious and color.
     * @return The String with the information.
     */
    @Override
    public String toString() {                
        return "Radius: " + radius + ", Color: " + color;                
    }	

    
    // Getters And Setters
    
    /**
     * Getter method of the circle relative to the radious attribute.
     * @return The radious of the circle.
     */
    public int getRadius(){
        return this.radius;
    }
    
    /**
     * Setter method of the circle relative to the radious attribute.
     * @param radius The radious to be set to the circle.
     */
    public void setRadius(int radius){
        this.radius=radius;
    }
 }
