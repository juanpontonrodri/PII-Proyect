import java.util.*;
public class RectangularRegion implements Comparator<RectangularRegion>{

    private String name;
    private Coordinate origin;
    private int h;
    private int w;
    private ArrayList<Coordinate> coordinates;

    public RectangularRegion(Mosaic m,String name, int r0, int c0,int h,int w){
        this.name=name;
        this.origin= new Coordinate(r0,c0);
        this.h=h;
        this.w=w;
        if (c0>m.getColumn()) this.origin.setColumn(1);
        if (r0>m.getRow()) this.origin.setRow(1);
        if (this.origin.getColumn()+this.h>(m.getColumn()+1)) this.h=m.getColumn()-this.origin.getColumn()+1;
        if (this.origin.getRow()+this.w>(m.getColumn()+1)) this.w=m.getRow()-this.origin.getRow()+1;
        this.coordinates=new ArrayList<>();
        for (int i = this.origin.getColumn(); i < this.h+this.origin.getColumn(); i++) {
            for (int j = this.origin.getRow(); j < this.w+this.origin.getRow(); j++) {
                coordinates.add(new Coordinate(i,j));                
            }
            
        }
        
    }

    public String getName(){
        return this.name;
    }

    public int getH(){
        return this.h;
    }

    public int getW(){
        return this.w;
    }

    public void sortByCoordinateAsc(){
       if (this.coordinates!=null)Collections.sort(this.coordinates);
    }

    public void shuffle(){
        if (this.coordinates!=null)Collections.shuffle(this.coordinates);
    }
    
    public int getArea(){
        return this.coordinates.size();
    }

    public Collection<Coordinate> getCoordinates(){
        return this.coordinates;
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
}