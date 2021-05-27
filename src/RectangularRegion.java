import java.util.*;
public class RectangularRegion implements Comparator<RectangularRegion>,Luminosity{

    private String name;
    private Coordinate origin;
    private int h;
    private int w;
    private ArrayList<Coordinate> coordinates;
    private Mosaic rrmosaic;

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

    public void changeLuminosity(int value){
        for (Coordinate c : this.coordinates){
            this.rrmosaic.getTile(c).changeLuminosity(value);
        }
    }

    public void changeStatus(int status){
        for (Coordinate c : this.coordinates){
            this.rrmosaic.getTile(c).setStatus(status);
        }
    }
    
    public void sortByCoordinateAsc(){
        if (this.coordinates!=null)Collections.sort(this.coordinates);
    }
 
    public void shuffle(){
         if (this.coordinates!=null)Collections.shuffle(this.coordinates);
    }
    
    public String toString(){
        return (this.name+":("+this.origin.getRow()+","+this.origin.getColumn()+"),"+this.h+"-"+this.w+":"+this.coordinates.toString());
    }

    @Override
    public int compare(RectangularRegion a, RectangularRegion b){
        if ( a.getArea()>b.getArea()) return -1;
        if (a.getArea()==b.getArea()) return a.getName().compareTo(b.getName());
        else return 1;
    }

    public static final Comparator<RectangularRegion> comp = (a, b) -> {
        if ( a.getArea()>b.getArea()) return 1;
        if (a.getArea()==b.getArea()) return a.getName().compareTo(b.getName());
        else return -1;
    };

    // Getters
    public int getArea(){
         return this.coordinates.size();
    }
    
    public Mosaic getRrmosaic() {
        return this.rrmosaic;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Collection<Coordinate> getCoordinates(){
        return this.coordinates;
    }

    public int getH(){
        return this.h;
    }

    public int getW(){
        return this.w;
    }
    
    //Setters
    public void setRrmosaic(Mosaic rrmosaic) {
        this.rrmosaic = rrmosaic;
    }
}