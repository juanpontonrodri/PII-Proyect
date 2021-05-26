public class Coordinate implements Comparable<Coordinate>{
    private int row;
    private int column;

    Coordinate(int row, int column){
        this.row=row;
        this.column=column;

    }

    public String toString(){
        return "("+row+","+column+")";
    }

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
    
    public boolean equals(Coordinate coordinate){
        return ((coordinate.getColumn()== this.column)&&(coordinate.getRow()==this.row));
        
    }

    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
    public void setRow(int row){
        this.row=row;
    }
    public void setColumn(int column){
        this.column=column;
    }
}