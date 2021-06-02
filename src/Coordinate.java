/**
 * Class to model the coordinates.
 * @author Juan.
 * @version 2.0.
 */
public class Coordinate implements Comparable<Coordinate>{
    private int row;
    private int column;

    /**
     * Constructor that creates a coordinate from its row and column index.
     * @param row The integer with the index of the row.
     * @param column The integer with the index of the column.
     */
    Coordinate(int row, int column){
        this.row=row;
        this.column=column;

    }

    /**
     * Method that returns a String with information about the crow and column index.
     * @return The String with the information.
     */
    public String toString(){
        return "("+this.row+","+this.column+")";
    }

    /**
     * Method that compares the coordinate passed by argument with the object from wich the method is invoked  in order to compare them by ascending coordinate.
     * @param c The coordinate to be compared with.
     * @return 1 if its indexes are higher, -1 if they are lower and 0 if they are equal.
     */
    public int compareTo(Coordinate c) { //compares by ascendig coordinate
        if (this.equals(c)) return 0;
		if (row< c.getRow()) return -1;
		else 
			if (row == c.getRow()){
                if (this.column>c.getColumn()) return 1;
                else return -1; 
            } 
			else return 1;
	}
    
    /**
     * Method that compares the coordinate passed by argument with the object from wich the method is invoked in order to see if theyre attributes are equal.
     * @param coordinate The coordinate to be compared with.
     * @return True if they are equal, false if they aren't.
     */
    public boolean equals(Coordinate coordinate){
        return ((coordinate.getColumn()== this.column)&&(coordinate.getRow()==this.row));
        
    }

    /**
     * Getter method of the coordinate relative to the row attribute.
     * @return The row attribute.
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Getter method of the coordinate relative to the column attribute.
     * @return The colummn attribute.
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Setter method of the coordinate relative to the row attribute.
     * @param row The row attribute.
     */
    public void setRow(int row){
        this.row=row;
    }

    /**
     * Setter method of the coordinate relative to the column attribute.
     * @param column The column attribute.
     */
    public void setColumn(int column){
        this.column=column;
    }
}