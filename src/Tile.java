/**
 * Class to model the tiles of the mosaic.
 * @author Juan.
 * @version 2.0.
 */
public class Tile implements Luminosity{

    private Color color;
    private Figure figure;
    private String position;
    private int status;
    private int luminosityChange=0;

    public static final String RIGHT="R";
    public static final String UP="U";
    public static final String CENTERED="C";
    public static final String DOWN="D";
    public static final String LEFT="L";

    private static int wTile;
    private static int hTile;

    /**
     * Constructor that creates a tile from the figure, color, position and status parameters.
     * @param figure The figure to be placed inside the tile.
     * @param color The color of the tile.
     * @param position The position of the figure inside the tile.
     * @param status The status of the tile (0,1,2).
     */
    Tile(Figure figure, Color color, String position, int status){
        this.color = color;
        this.position=position;
        this.figure=figure;
        this.status=status;
    }

    /**
     * Constructor that creates a tile from its color and status, without a figure inside.
     * @param color The color of the tile.
     * @param status The status of the tile.
     */
    Tile(Color color, int status){
        this.color=color;
        this.status=status;
    }
    
    /**
     * Method of the tile that cheks if it has a figure inside.
     * @return True if it has a figure, false if it is empty.
     */
    public boolean hasFigure(){
        return (this.figure!=null);
    }

    /**
     * Method that compares the tile passed by argument with the object from wich the method is invoked in order to see if they are equal.
     * @param tile The tile to be compare with.
     * @return True if it is equal, false if it isn't.
     */
    public boolean isEqualTo(Tile tile){
        return (this.figure.isEqualTo(tile.getFigure())&&(this.position.equals(tile.getPosition())));
    }

    /**
     * Method that returns a String with information about the tile's status, color and figure.
     * @return The String with the information.
     */
    public String toString(){
        if (this.figure==null) return (this.status + "{" + this.color.toString() +"}");
        if (this.figure instanceof Circle) return (this.status + "{" + this.color.toString() + ",CIR:{" + this.figure.getColor().toString() + "," + this.position + "," + ((Circle)this.getFigure()).getRadius() + "}" + "}");
        if (this.figure instanceof Rectangle) return (this.status + "{" + this.color.toString() + ",REC:{" + this.figure.getColor().toString() + "," + this.position + "," + ((Rectangle)this.getFigure()).getHeight() + "," + ((Rectangle)this.getFigure()).getWidth() + "}" + "}");
        return "If you reach this, update the code to the new figures";
    }
    
    /**
     * Method of the tile that increments the the value of the attribute luminosityChange by the value parameter.
     * @param value The value to be incremented by.
     */
    public void changeLuminosity(int value){
        this.luminosityChange+=value;
    }
    
    //Getters

    /**
     * Getter method of the tile relative to the status attribute.
     * @return The status of the tile.
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * Getter method of the tile relative to the color attribute.
     * @return The color of the tile.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Getter method of the tile relative to the width static attribute.
     * @return The width of the tiles.
     */
    public int getWidth(){
        return wTile;
    }

    /**
     * Getter method of the tile relative to the heigth static attribute.
     * @return The height of the tiles.
     */
    public int getHeight(){
        return hTile;
    }
    
    /**
     * Getter method of the tile relative to the position attribute.
     * @return The position of the figure inside the tile.
     */
    public String getPosition(){
        return this.position;
    }

    /**
     * Getter method of the tile relative to the figure attribute.
     * @return The figure inside the tile.
     */
    public Figure getFigure(){
        return this.figure;
    }

    /**
     * Getter method of the tile relative to the luminosityChange attribute.
     * @return The luminosityChange attribute of the tile.
     */
    public int getLuminosityChange() {
        return luminosityChange;
    }
    
    //Setters

    /**
     * Setter method of the tile relative to the position attribute.
     * @param position The position of the figure inside the tile.
     */
    public void setPosition(String position){
        if (position.equals(RIGHT)) this.position=RIGHT;
        if (position.equals(UP)) this.position=UP;
        if (position.equals(CENTERED)) this.position=CENTERED;
        if (position.equals(DOWN)) this.position=DOWN;
        if (position.equals(LEFT) )this.position=LEFT;
    }

     /**
     * Setter method of the tile relative to the status attribute.
     * @param status The status of the tile.
     */
    public void setStatus(int status){
        this.status=status;
    }
    
     /**
     * Setter method of the tile relative to the color attribute.
     * @param color The color of the tile.
     */
    public void setColor(Color color){
        this.color=color;
    }

    /**
     * Setter static method of the tile relative to the static attributes wTile and hTile.
     * @param w The with of the tiles.
     * @param h The height of the tiles.
     */
    public static void setSize( int w,int h){
        hTile=h;
        wTile=w;
    }

    /**
     * Setter method of the tile relative to the luminosityChange attribute.
     * @param luminosityChange The luminosityChange value of the tile.
     */
    public void setLuminosityChange(int luminosityChange) {
        this.luminosityChange = luminosityChange;
    }
}

