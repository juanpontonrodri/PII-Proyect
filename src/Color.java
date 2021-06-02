/**
 * Class to model the colors of the figures and the tiles.
 * @author Juan.
 * @version 1.0.
 */
public class Color {

    private int r;
    private int g;
    private int b;

    private static final int MAX = 255;

    /**
     * Constructor that creates a color combining the red, green and blue parameters.
     * @param r The integer that represents the red amount of color.
     * @param g The integer that represents the green amount of color.
     * @param b The integer that represents the blue amount of color.
     */

    Color(int r, int g, int b) {
        if ((r > MAX) || (g > MAX) || (b > MAX) ) {
            this.r= MAX;
            this.g= MAX;
            this.b= MAX;
        }
        else {
            this.r= r;
            this.g= g;
            this.b= b;
        }
    }


    // Static Methods

    /**
     * Static and final method that returns a prefixed white color.
     * @return The white color.
     */
    public static final Color WHITE() {
        return new Color(255,255,255);
    }
     /**
     * Static and final method that returns a prefixed black color.
     * @return The black color.
     */
    public static final Color BLACK() {
        return new Color (0,0,0);
    }
     /**
     * Static and final method that returns a prefixed red color.
     * @return The red color.
     */
    public static final Color RED() {
        return new Color (229,20,0);
    }
     /**
     * Static and final method that returns a prefixed green color.
     * @return The green color.
     */
    public static final Color GREEN() {
        return new Color (96,169,23);
    }
     /**
     * Static and final method that returns a prefixed blue color.
     * @return The blue color.
     */
    public static final Color BLUE() {
        return new Color (0,80,239);
    }
     /**
     * Static and final method that returns a prefixed yellow color.
     * @return The yellow color.
     */
    public static final Color YELLOW() {
        return new Color (227,200,0);
    }
     /**
     * Static and final method that returns a prefixed magenta color.
     * @return The magenta color.
     */
    public static final Color MAGENTA() {
        return new Color (216,0,115);
    }
     /**
     * Static and final method that returns a prefixed cyan color.
     * @return The cyan color.
     */
    public static final Color CYAN() {
        return new Color (27,161,226);
    }

    /**
     * Method that compares the color passed by argument with the object from wich the method is invoked in order to see if they are equal.
     * @param color The color to be compared with.
     * @return True if they are equal, false if they aren't.
     */
    public boolean isEqualTo (Color color) {
        return ((this.r==color.getR())&&(this.g==color.getG())&&(this.b==color.getB())) ;
    }

    /**
     * Method that returns a String with information about the red, green and blue amount of color.
     * @return The String with the information.
     */
    public String toString() {
        return "R"+r+"G"+g+"B"+b;
    }

    /**
     * Setter method of the color relative to the r attribute.
     * @param r The integer with the amount of red.
     */
   public void setR(int r){
       this.r=r;
   }

   /**
     * Setter method of the color relative to the g attribute.
     * @param g The integer with the amount of green.
     */
   public void setG(int g){
    this.g=g;
    }

     /**
     * Setter method of the color relative to the b attribute.
     * @param b The integer with the amount of blue.
     */
    public void setB(int b){
        this.b=b;
    }

     /**
     * Getter method of the color relative to the r attribute.
     * @return The integer with the amount of red.
     */
    public int getR() {
        return this.r;
    }

    /**
     * Getter method of the color relative to the g attribute.
     * @return The integer with the amount of green.
     */
    public int getG() {
        return this.g;
    }

    /**
     * Getter method of the color relative to the b attribute.
     * @return The integer with the amount of blue.
     */
    public int getB() {
        return this.b;
    }
}
