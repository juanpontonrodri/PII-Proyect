public class Tile{

    private Color color;
    private Figure figure;
    private String position;
    private int status;
    

    public static final String RIGHT="R";
    public static final String UP="U";
    public static final String CENTERED="C";
    public static final String DOWN="D";
    public static final String LEFT="L";

    private static int wTile;
    private static int hTile;

    Tile(Figure figure, Color color, String position, int status){
        this.color = color;
        this.position=position;
        this.figure=figure;
        this.status=status;
    }
    // Tile(Circle circle, Color color, String position, int status){
    //     this.color = color;
    //     this.position=position;
    //     this.figure=circle;
    //     this.status=status;
    // }Tile(Rectangle rectangle, Color color, String position, int status){
    //     this.color = color;
    //     this.position=position;
    //     this.figure=rectangle;
    //     this.status=status;
    // }
    Tile(Color color, int status){
        this.color=color;
        this.status=status;
    }

    public void setPosition(String position){
        if (position.equals(RIGHT)) this.position=RIGHT;
        if (position.equals(UP)) this.position=UP;
        if (position.equals(CENTERED)) this.position=CENTERED;
        if (position.equals(DOWN)) this.position=DOWN;
        if (position.equals(LEFT) )this.position=LEFT;

    }
    public void setStatus(int status){
        this.status=status;
    }
    
    public void setColor(Color color){
        this.color=color;
    }
    public static void setSize( int w,int h){
        hTile=h;
        wTile=w;
    }

    public int getStatus(){
        return this.status;
    }
    public Color getColor(){
        return this.color;
    }

    public int getWidth(){
        return wTile;

    }

    public int getHeight(){
        return hTile;
    }
    
    public String getPosition(){
        return this.position;
    }
    public boolean hasFigure(){
        return (this.figure!=null);
    }
    public Figure getFigure(){
        return this.figure;
    }

    public boolean isEqualTo(Tile tile){
        //REVISAR

        // int i=0;
        // switch (this.ctnfig) {
        //     case 0:
        //         if(tile.ctnfig==0) i=1;                
        //         break;
        //     case 1:
        //         if(tile.ctnfig!=1) break;                
        //         if(this.circle.isEqualTo(tile.circle)&&(this.position.equals(tile.position))) i=1;
        //         break;
        //     case 2:
        //         if (tile.ctnfig!=2) break;
        //         if(this.rectangle.isEqualTo(tile.rectangle)&&(this.position.equals(tile.position))) i=1;
        //         break;
        //     default:
        //         break;
        // }
        // return (this.color.isEqualTo(tile.color)&&(i==1));

        return (this.figure.isEqualTo(tile.getFigure())&&(this.position.equals(tile.getPosition())));
       
    }
    public String toString(){
        if (this.figure==null) return (this.status + "{" + this.color.toString() +"}");
        else{
            if (this.figure instanceof Circle) return (this.status + "{" + this.color.toString() + ",CIR:{" + this.figure.getColor().toString() + "," + this.position + "," + ((Circle)this.getFigure()).getRadius() + "}" + "}");
            else {
               if (this.figure instanceof Rectangle) return (this.status + "{" + this.color.toString() + ",REC:{" + this.figure.getColor().toString() + "," + this.position + "," + ((Rectangle)this.getFigure()).getHeight() + "," + ((Rectangle)this.getFigure()).getWidth() + "}" + "}");
               else return "error at Tile.toString";
            }
            
        }
        
    }
}
