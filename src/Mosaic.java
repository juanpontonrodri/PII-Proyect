import java.util.*;
import java.io.*;

/**
 * Class to model the mosaic of tiles.
 * @author Juan.
 * @version 4.0.
 */
public class Mosaic implements Luminosity{

    private TreeMap<Coordinate, Tile> mapTiles;
	private int rows;
	private int columns;
	private int heightTile;
	private int widthTile;

    private ArrayList<RectangularRegion> regions;

    // Constructors
    /**
     * Constructor that creates a mosaic reading its values from the file passed by argument.
     * @param file The name of the file to be read.
     * @exception FileNotFoundException The exception is thrown and saved in the error.txt file if the constructor fails while opening the file.
     * @exception TileOutOfBoundsException The exception is thrown and saved in the error.txt file if there is a tile, that is out of the mosaic limits, in the file.
     */
    public Mosaic(String file) {
	    Scanner input=null;
	    try {
		    input = new Scanner (new FileInputStream(file)) ;
        }
	    catch (FileNotFoundException e) {
            PrintWriter eprint = null;       
            try {      
                eprint = new PrintWriter (new FileOutputStream("error.txt"));              
            }
            catch (FileNotFoundException ef) {
                System.exit(-1);
            }        
            eprint.println("FileNotFoundException");
            eprint.close();
		    System.exit(-1);
        }

	    String line =null;	
        boolean l1=false; //cheks if first line has been read
        boolean r1; // changes to make the program to skip the second while at the first line        
        
	    while (input.hasNextLine()){
		    line = input.nextLine();
		    if (!line.startsWith("*")) {
                line=line.trim().replaceAll("\\s", "");		
                r1=false;
                while (!l1) {
                    String szMosaic= line.substring(0, line.indexOf(","));
                    this.rows=Integer.parseInt(szMosaic.substring(0,szMosaic.indexOf("x")));
                    this.columns = Integer.parseInt(szMosaic.substring(szMosaic.indexOf("x")+1,szMosaic.length()));
                    String szTiles= line.substring(line.indexOf(",")+1,line.length());
                    this.heightTile = Integer.parseInt(szTiles.substring(0,szTiles.indexOf("x")));
                    this.widthTile = Integer.parseInt(szTiles.substring(szTiles.indexOf("x")+1,szTiles.length()));
                    Tile.setSize(this.widthTile,this.heightTile);
                    l1=true;
                    r1=true;
                }
                while(!r1){     
                    if (this.mapTiles==null) {
                        this.mapTiles = new TreeMap<>((a, b) -> {
                        if (a.equals(b)) return 0;
                        if (a.getRow()< b.getRow()) return -1;
                        else 
                            if (a.getRow() == b.getRow()){
                                if (a.getColumn()>b.getColumn()) return 1;
                                else return -1; 
                            } 
                            else return 1;
                        });
                    }
                    
                    String arrpos = line.substring (0,line.indexOf(":"));
                    int r=(Integer.parseInt(arrpos.substring(1,arrpos.indexOf(","))));
                    int c=(Integer.parseInt(arrpos.substring(arrpos.indexOf(",")+1,arrpos.indexOf(")"))));                   
                    int stus = Integer.parseInt(line.substring(line.indexOf(":")+1,line.indexOf("{")));
                    String large = line.substring(line.indexOf("{")+1,line.length());
                    large=large.toUpperCase();

                    try{
                        if((r>this.rows)||(c>this.columns)) throw new TileOutOfBoundsException();
                    }
                    catch (TileOutOfBoundsException exception){
                        PrintWriter p = null;       
                        try {      
                            p = new PrintWriter (new FileOutputStream("error.txt"));              
                        }
                        catch (FileNotFoundException ef) {
                            System.exit(-1);
                        }        
                        p.println(exception.getMessage());
                        p.close();
                        System.exit(-1);
                    }
                    
                    if (large.indexOf(":")==(-1)){                                            
                        String tilergb = large.substring (0,large.length()-1);
                        int tileR=Integer.parseInt(tilergb.substring(tilergb.indexOf("R") +1,tilergb.indexOf("G")));
                        int tileG=Integer.parseInt(tilergb.substring(tilergb.indexOf("G")+1,tilergb.indexOf("B")));
                        int tileB=Integer.parseInt(tilergb.substring(tilergb.indexOf("B")+1,tilergb.length()));
                        
                        this.mapTiles.put(new Coordinate (r,c), new Tile(new Color(tileR,tileG,tileB),stus));
                    }
                    
                    else {
                        String tilergb = large.substring (0,large.indexOf(","));
                        int tileR=Integer.parseInt(tilergb.substring(tilergb.indexOf("R") +1,tilergb.indexOf("G")));
                        int tileG=Integer.parseInt(tilergb.substring(tilergb.indexOf("G")+1,tilergb.indexOf("B")));
                        int tileB=Integer.parseInt(tilergb.substring(tilergb.indexOf("B")+1,tilergb.length()));
                        String fgtype=large.substring(large.indexOf(",")+1,large.indexOf(":"));
                        String fgvariables=large.substring(large.indexOf("{")+1,large.indexOf("}"));
                        String fgrgb=fgvariables.substring(0,fgvariables.indexOf(","));
                        int fgR=Integer.parseInt(fgrgb.substring(fgrgb.indexOf("R") +1,fgrgb.indexOf("G")));                        
                        int fgG=Integer.parseInt(fgrgb.substring(fgrgb.indexOf("G")+1,fgrgb.indexOf("B")));                        
                        int fgB=Integer.parseInt(fgrgb.substring(fgrgb.indexOf("B")+1,fgrgb.length()));
                        
                        String fgpos=fgvariables.substring(fgvariables.indexOf(",")+1,fgvariables.indexOf(",",fgvariables.indexOf(",")+1));
                        if (fgtype.equals("CIR")){        
                            int circleradious=Integer.parseInt(fgvariables.substring(fgvariables.indexOf(",",fgvariables.indexOf(",")+1)+1,fgvariables.length()));
                            this.mapTiles.put(new Coordinate (r,c), new Tile(new Circle(circleradious, new Color(fgR,fgG,fgB)),new Color(tileR,tileG,tileB),fgpos,stus));
                        }
                        
                        if (fgtype.equals("REC")){
                            String recparam=fgvariables.substring(fgvariables.indexOf(",",fgvariables.indexOf(",")+1)+1,fgvariables.length());
                            int recheight=Integer.parseInt(recparam.substring(0,recparam.indexOf(",")));
                            int recwidth=Integer.parseInt(recparam.substring(recparam.indexOf(",")+1,recparam.length()));    
                            this.mapTiles.put(new Coordinate (r,c), new Tile(new Rectangle(recwidth, recheight, new Color(fgR,fgG,fgB)),new Color(tileR,tileG,tileB),fgpos,stus));
                        }                        
                    }
                r1=true;
                } 
            }       
        }
	input.close();   
    initialize();        
}
   
    /**
     * Method of the mosaic that creates blank tiles in the gaps that the file passed to the constructor leaves.
     */
    public void initialize(){ 
        for(int i=1; i<=this.rows;i++){
            for (int k = 1; k <= this.columns; k++) {
                if(this.getTile(new Coordinate(i, k))==null)this.mapTiles.put(new Coordinate(i, k),new Tile(Color.WHITE(),1));
            }
        }
    }
    
    /**
     * Method that returns a String with information about the mosaic (the tiles and how they are ordered).
     * @return The String with the information.
     */
    public String toString(){     
        String linep=this.rows+"x"+this.columns+","+this.heightTile+"x"+this.widthTile+"\n"; 
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()){
            Tile tile = object.getValue();
            Coordinate coordinate = object.getKey();
            linep=linep.concat("(" + (coordinate.getRow()) + "," + (coordinate.getColumn()) + ")" + ":" + tile.toString()+"\n");
        }
        return linep;       
        }

    /**
     * Method of the mosaic that apllys the changes in luminosity and status in the mosaic and then it saves the mosaic in the file passed by argument using the toString method. 
     * @param file The name of the file in wich the mosaic will be saved.
     * @exception FileNotFoundException The exception will be thrown and saved in the error.txt file if the method fails while creating the file.
     */    
    public void saveToFile(String file){
        int r;
        int g;
        int b;
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()) {
            Tile tile = object.getValue();
            if(tile.getStatus()==0){
                tile.setColor(Color.BLACK());
                if(tile.hasFigure()) tile.getFigure().setColor(Color.BLACK());
            }
            if((tile.getStatus()==1)&&(tile.getLuminosityChange()!=0)){
                    r=(tile.getLuminosityChange()+tile.getColor().getR())%256;
                    g=(tile.getLuminosityChange()+tile.getColor().getG())%256;
                    b=(tile.getLuminosityChange()+tile.getColor().getB())%256;
                    tile.setColor(new Color(r, g, b));
                    if (tile.hasFigure()){
                        r=(tile.getLuminosityChange()+tile.getFigure().getColor().getR())%256;
                        g=(tile.getLuminosityChange()+tile.getFigure().getColor().getG())%256;
                        b=(tile.getLuminosityChange()+tile.getFigure().getColor().getB())%256;
                        tile.getFigure().setColor(new Color(r, g, b));
                    }
            }
            if((tile.getStatus()==2)){
                if(tile.hasFigure()) tile.getFigure().setColor(Color.BLACK());
                if(tile.getLuminosityChange()!=0){
                    r=(tile.getLuminosityChange()+tile.getColor().getR())%256;
                    g=(tile.getLuminosityChange()+tile.getColor().getG())%256;
                    b=(tile.getLuminosityChange()+tile.getColor().getB())%256;
                    tile.setColor(new Color(r, g, b));
                }
            }
        }

        PrintWriter output = null;       
        try {      
            output = new PrintWriter (new FileOutputStream(file));              
        }
        catch (FileNotFoundException e) {
            PrintWriter eprint = null;       
            try {      
                eprint = new PrintWriter (new FileOutputStream("error.txt"));              
            }
            catch (FileNotFoundException ef) {
                System.exit(-1);
            }        
            eprint.println("FileNotFoundException");
            eprint.close();
            System.exit(-1);
        }        
        output.print(this.toString());
        output.close();
        }
    
    /**
     * Method of the mosaic that adds the region passed by argument to the ArrayList regions attribute.
     * @param region The region to be added.
     */
    public void addRegion(RectangularRegion region){
        if (this.regions==null) this.regions= new ArrayList<>();
        this.regions.add(region);
    }
    
    /**
     * Method of the mosaic that sorts the regions of the ArrayList regions by comparing the area of the regions.
     */
    public void sortRegionsByAreaAsc(){
        Collections.sort(this.regions, new Comp());
    }

    /**
     * Method of the mosaic that returs a string with the information about the regions of the mosaic.
     * @return The string with the regions information.
     */
    public String toStringRegions(){
        String string="";
        for (RectangularRegion sample : this.regions) {
            string=string.concat(sample.toString()+"\n");
        }
        return string;
    }
    
    /**
     * Method of the mosaic that returns a list of the type of figures that the tiles inside the mosaic contain.
     * @return The ArrayList with the names of the types of figures.
     */
    public Collection<String> listFigureClasses(){
        ArrayList<String> figclas= new ArrayList<>();
        boolean isalready;
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()){
            Tile tile = object.getValue();
            isalready=false;
            if(tile.hasFigure()) {
                for (String sample : figclas) {
                    if ((sample.equals(tile.getFigure().getClass().getSimpleName()))) isalready=true;
                }
                if(!isalready)figclas.add(tile.getFigure().getClass().getSimpleName());
            }
        }
        return figclas;    
    }

     /**
     * Method of the mosaic that changes the luminosity with the value passed by argument in all the tiles of the mapTiles.
     * @param value The value to be incremented by.
     */
    public void changeLuminosity(int value){
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()) {
            Tile tile = object.getValue();
            tile.changeLuminosity(value);
        }
    }

    /**
     * Method that changes the status of all the tiles in the mapTiles by the value passed by argument.
     * @param status The value that the tiles will take as status.
     */
    public void changeStatus(int status){
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()) {
            Tile tile = object.getValue();
            tile.setStatus(status);
        }
    }

    // Getters
    
    /**
     * Getter method of the mosaic that returns a region which name is passed by argument as a string after searching it in the regions ArrayList.
     * @param name The name of the reigon to be returned.
     * @return The region that was found (null if it doesn't exist).
     */
    public RectangularRegion getRegion(String name){
        for (RectangularRegion sample : this.regions) {
            if (sample.getName().equals(name)) {
                return sample;
            }
        }
        return null;
    }

    /**
     * Getter method of the mosaic that returns the tile in the coordinate passed by argument.
     * @param c The coordinate of the tile.
     * @return The tile in that coordinate.
     */
    public Tile getTile(Coordinate c){
        return mapTiles.getOrDefault(c, null);
        }

    /**
     * Getter method of the mosaic relative to the row attribute.
     * @return The rows of the mosaic.
     */
    public int getRow(){
        return this.rows;
    }
    
    /**
     * Getter method of the mosaic relative to the column attribute.
     * @return The columns of the mosaic.
     */
    public int getColumn(){
        return this.columns;
    }
        
    //Setters
    /**
     * Setter method of the mosaic relative to the row attribute.
     * @param rows The rows of the mosaic.
     */
    public void setRow(int rows){
        this.rows=rows;
    }
    /**
     * Setter method of the mosaic relative to the column attribute.
     * @param columns The columns of the mosaic.
     */
    public void setColumn(int columns){
        this.columns=columns;
    }

    /**
     * Class to model the regions of tiles using coordinates.
     * @author Juan.
     * @version 3.0.
     */
    public class RectangularRegion implements Luminosity{

        private String name;
        private Coordinate origin;
        private int h;
        private int w;
        private ArrayList<Coordinate> coordinates;
        private Mosaic rrmosaic;
    
        /**
         * Constructor that crates a rectangular region by the mosaic, the name of the region, the row and column of the origin coordinate and the height and width of the region.
         * @param m The mosaic that owns the region.
         * @param name The name of the region.
         * @param r0 The row of the origin coordinate.
         * @param c0 The column of the origin coordinate.
         * @param h The height of the region.
         * @param w The width of the region.
         */
        public RectangularRegion(Mosaic m,String name, int r0, int c0,int h,int w){
            this.rrmosaic=m;
            this.name=name;
            this.origin= new Coordinate(r0,c0);
            this.h=h;
            this.w=w;
    
            if (r0>m.getRow()) this.origin.setRow(1);
            if (c0>m.getColumn()) this.origin.setColumn(1);
            if ((this.origin.getRow()+this.h)>(m.getRow()+1)) this.h=m.getRow()-this.origin.getRow()+1;
            if ((this.origin.getColumn()+this.w)>(m.getColumn()+1)) this.w=m.getColumn()-this.origin.getColumn()+1;
            
            this.coordinates=new ArrayList<>();
            for (int i = this.origin.getRow(); i < (this.h+this.origin.getRow()); i++) {
                for (int j = this.origin.getColumn(); j < (this.w+this.origin.getColumn()); j++) {
                    coordinates.add(new Coordinate(i,j));                
                }
            }
        }
    
        /**
         * Method of the region that changes the luminosity of the tiles inside that region.
         * @param value The value that is added to the luminosity of the tiles.
         */
        public void changeLuminosity(int value){
            for (Coordinate c : this.coordinates){
                this.rrmosaic.getTile(c).changeLuminosity(value);
            }
        }
    
        /**
         * Method of the region that changes the status of the tiles inside that region.
         * @param status The new status of the tiles.
         */
        public void changeStatus(int status){
            for (Coordinate c : this.coordinates){
                this.rrmosaic.getTile(c).setStatus(status);
            }
        }
        
        /**
         * Method of the region that sorts its coordinates by ascending order.
         */
        public void sortByCoordinateAsc(){
            if (this.coordinates!=null)Collections.sort(this.coordinates);
        }
     
        /**
         * Method of the region that shuffle its coordinates.
         */
        public void shuffle(){
             if (this.coordinates!=null)Collections.shuffle(this.coordinates);
        }
        
        /**
        * Method that returns a String with information about the origin row and column, the height and width and the information about its coordinates.
        * @return The String with the information.
        */
        public String toString(){
            return (this.name+":("+this.origin.getRow()+","+this.origin.getColumn()+"),"+this.h+"-"+this.w+":"+this.coordinates.toString());
        }
       
        // Getters

        /**
        * Getter method of the region that returns its area.
        * @return The area of the region.
        */
        public int getArea(){
             return this.coordinates.size();
        }
        
        /**
        * Getter method of the region relative to its mosaic attribute.
        * @return The mosaic.
        */
        public Mosaic getRrmosaic() {
            return this.rrmosaic;
        }
        /**
        * Getter method of the region relative to its name attribute.
        * @return The name of the region.
        */
        public String getName(){
            return this.name;
        }
        
         /**
        * Getter method of the region that returns a collection of its coordinates.
        * @return The collection.
        */
        public Collection<Coordinate> getCoordinates(){
            return this.coordinates;
        }
    
         /**
        * Getter method of the region relative to its height attribute.
        * @return The height of the region.
        */
        public int getH(){
            return this.h;
        }
    
         /**
        * Getter method of the region relative to its width attribute.
        * @return The width of the region.
        */
        public int getW(){
            return this.w;
        }
    }

    /**
     * Class that models a comparator object (implementing the Comparator interface) to sort the rectangular regions by its area.
     * @author Juan.
     * @version 5.0.
     */
    class Comp implements Comparator<RectangularRegion>{
        /**
         * Method of the Comp class that compares 2 rectangular regions to sort them by theyre area and theyre name.
         * @param a The first region.
         * @param b The second region.
         * @return 1 if the area of a is bigger than the area, -1 if it is less. If they are equal they are compared by its name.
         */
        public int compare(RectangularRegion a, RectangularRegion b){
            if ( a.getArea()>b.getArea()) return 1;
            if (a.getArea()==b.getArea()) return a.getName().compareTo(b.getName());
            else return -1;
        }
    }
}