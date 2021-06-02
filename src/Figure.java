/**
 * Class to model the geometrical figures.
 * @author Juan.
 * @version 1.0.
 */
public abstract class Figure {
    protected Color color;

    /**
     * Constructor that creates a figure from its color.
     * @param color The color of the figure.
     */
    protected Figure(Color color){
        this.color=color;
    }

    //Methods

    /**
     * Abstact method of the figure that will return its area when being implemented.
     */    
    public abstract double area();

    /**
     * Method that compares the figure passed by argument with the object from wich the method is invoked in order to see if they are equal.
     * @param figure The figure to be compare with.
     * @return True if it is equal, false if it isn't.
     */
    public boolean isEqualTo(Figure figure){
        return this.color.isEqualTo(figure.getColor());
    }
    /**
     * Method that returns a String with information about the figure color.
     * @return The String with the information.
     */
    public String toString(){
        return "Color: "+ this.color;
    }

     /**
     * Getter method of the figure relative to the color attribute.
     * @return The color of the figure.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Setter method of the figure relative to the color attribute.
     * @param color The color of the figure.
     */
    public void setColor(Color color){
        this.color=color;
    }
}
