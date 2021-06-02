/**
 * Class to model the rectangles extending the Figure class.
 * @author Juan.
 * @version 2.0.
 */
public class Rectangle extends Figure {
    private int width;
    private int height;
    
    /**
     * Constructor that creates a rectangle from its width, height and color.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */
    Rectangle(int width, int height, Color color){
        super(color);
        if (height>100) this.height=100;
        else{
            if (height<0) this.height=0;
            else this.height = height;
        }

        if (width>100) this.width=100;
        else{
            if(width<0) this.width=0;
            else this.width=width;
        }
    }

    /**
     * Method of the rectangle that returns its area. Its an implementation of the abstract mehod from the parent class.
     * @return The area of the rectangle.
     */    
    public double area(){
        return (this.width*this.height)/100.00;
    }
    /**
     * Method that compares the rectangle passed by argument with the object from wich the method is invoked in order to see if they are equal.
     * @param rectangle The rectangle to compare with.
     * @return True if it is equal, false if it isn't.
     */
    public boolean isEqualTo(Rectangle rectangle) {		
        return ((this.color.isEqualTo(rectangle.getColor()))&&(this.width==rectangle.getWidth())&&(this.height==rectangle.getHeight()));     
    }
    
    /**
     * Method that returns a String with information about the rectangle's width,height and color.
     * @return The String with the information.
     */
    @Override
    public String toString() {
                
        return "Width: " + this.width + ", Height:" + this.height + ", Color: " + this.color;
                
    }	

    /**
     * Getter method of the rectangle relative to the width attribute.
     * @return The width of the rectangle.
     */
    public int getWidth(){
        return this.width;
    }
    /**
     * Getter method of the rectangle relative to the height attribute.
     * @return The height of the rectangle.
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Setter method of the rectangle relative to the width attribute.
     * @param width The width of the rectangle.
     */
    public void setWidth(int width){
        this.width=width;
    }

    /**
     * Setter method of the rectangle relative to the heigth attribute.
     * @param width The height of the rectangle.
     */
    public void setHeight(int height){
        this.height = height;
    }
}
