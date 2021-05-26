import java.util.*;
import java.io.*;

public class Mosaic {

    private TreeMap<Coordinate, Tile> mapTiles;
	private int rows;
	private int columns;
	private int heightTile;
	private int widthTile;

    private ArrayList<RectangularRegion> regions;

    // Constructors
    public Mosaic(String file) {
	Scanner input=null;
	try {
		input = new Scanner (new FileInputStream(file)) ;
}
	catch (FileNotFoundException e) {
		System.out.println("File open error");
		System.exit(-1);
}


	String line =null;	
        boolean l1=false; //cheks if first line has been read
        boolean r1; // changes to make the program to skip the second while at the first line        
        
	while (input.hasNextLine()){
		line = input.nextLine();
		if (!line.startsWith("*")) {
                line=line.trim();
                line=line.replaceAll("\\s", "");		
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
                    int r=(Integer.parseInt(arrpos.substring(1,arrpos.indexOf(",")))-1);
                    int c=(Integer.parseInt(arrpos.substring(arrpos.indexOf(",")+1,arrpos.indexOf(")")))-1);                   
                    int stus = Integer.parseInt(line.substring(line.indexOf(":")+1,line.indexOf("{")));
                    String large = line.substring(line.indexOf("{")+1,line.length());
                    large=large.toUpperCase();
                    
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
   

    public void initialize(){ 
        boolean whitecheck=false;
        while(!whitecheck){
        int i=0;
        int j=0; 
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()){     
            if(i==this.rows) break;   
            Coordinate coordinate = object.getKey();
            if(!coordinate.equals(new Coordinate(i,j))) {
                this.mapTiles.put(new Coordinate(i, j),new Tile(Color.WHITE(),1));
                whitecheck=false;
                break;
                }
            whitecheck=true;
            if (j==(this.columns-1)) {
                i++;
                j=0; 
            }
            else j++;

        }}
         

    }
    
   
    
    public String toString(){     
        String linep=this.rows+"x"+this.columns+","+this.heightTile+"x"+this.widthTile; 
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()){
            Tile tile = object.getValue();
            Coordinate coordinate = object.getKey();
            linep=linep.concat("\n" + "(" + (coordinate.getRow()+1) + "," + (coordinate.getColumn()+1) + ")" + ":" + tile.toString());
            }
        
        return linep;       
        }
    
    public void saveToFile(String file){
        
        PrintWriter output = null;       
        try {      
            output = new PrintWriter (new FileOutputStream(file));              
        }
        catch (FileNotFoundException e) {
            System.out.println("Error creating the file");
            System.exit(-1);
        }        
        output.println(toString());
        output.close();
        
        }
    
    public void addRegion(RectangularRegion region){
        if (this.regions==null) this.regions= new ArrayList<>();
        this.regions.add(region);
    }
    
    public void sortRegionsByAreaAsc(){
        Collections.sort(this.regions,RectangularRegion.comp);
    }

    public RectangularRegion getRegion(String name){
        for (RectangularRegion sample : this.regions) {
            if (sample.getName().equals(name)) {
                return sample;
            }
        }
        return null;
    }

    public String toStringRegions(){
        String string="";
        for (RectangularRegion sample : this.regions) {
            string=string.concat(sample.toString()+"\n");
            }
        return string;
    }

    public Tile getTile(Coordinate c){
        return mapTiles.getOrDefault(c, null);
        }

    public Collection<String>listFigureClasses(){
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

    public int totalNumberFiguresClass(String figureClass){
        int num=0;
        for (Map.Entry<Coordinate,Tile> object : mapTiles.entrySet()){
            Tile tile = object.getValue();
            if((tile.hasFigure())&& (figureClass.equals(tile.getFigure().getClass().getSimpleName()))) num++;
            }
        return num;
    }

    // Getters
    public int getRow(){
        return this.rows;
    }
    
    public int getColumn(){
        return this.columns;
    }
    
    public int getHeightTile(){
        return this.heightTile;
    }
    
    public int getWidthTile (){
        return this.widthTile;
    }    
    
    //Setters
    public void setRow(int rows){
        this.rows=rows;
    }
    public void setColumn(int columns){
        this.columns=columns;
    }
    
    public void setHeightTile(int heightTile){
        this.heightTile=heightTile;
    }
    
    public void setWidthTile(int widthTile){
        this.widthTile=widthTile;
    }
 }
