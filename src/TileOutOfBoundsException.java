/**
 * Class to model the custom exception TileOutOfBoundsException.
 * @author Juan.
 * @version 1.0.
 */
public class TileOutOfBoundsException extends Exception{
    /**
     * Constructor that creates a new custom exception passing the String "TileOutOfBoundsException" to the super constructor.
     */
    TileOutOfBoundsException(){
        super("TileOutOfBoundsException");
    }
}