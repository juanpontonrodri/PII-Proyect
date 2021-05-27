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

    Tile(Figure figure, Color color, String position, int status){
        this.color = color;
        this.position=position;
        this.figure=figure;
        this.status=status;
    }

    Tile(Color color, int status){
        this.color=color;
        this.status=status;
    }
    
    public boolean hasFigure(){
        return (this.figure!=null);
    }

    public boolean isEqualTo(Tile tile){
        return (this.figure.isEqualTo(tile.getFigure())&&(this.position.equals(tile.getPosition())));
    }

    public String toString(){
        if (this.figure==null) return (this.status + "{" + this.color.toString() +"}");
        if (this.figure instanceof Circle) return (this.status + "{" + this.color.toString() + ",CIR:{" + this.figure.getColor().toString() + "," + this.position + "," + ((Circle)this.getFigure()).getRadius() + "}" + "}");
        if (this.figure instanceof Rectangle) return (this.status + "{" + this.color.toString() + ",REC:{" + this.figure.getColor().toString() + "," + this.position + "," + ((Rectangle)this.getFigure()).getHeight() + "," + ((Rectangle)this.getFigure()).getWidth() + "}" + "}");
        return "If you reach this, update the code to the new figures";
    }
    
    public void changeLuminosity(int value){
        this.luminosityChange+=value;
    }
    
    //Getters
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

    public Figure getFigure(){
        return this.figure;
    }

    public int getLuminosityChange() {
        return luminosityChange;
    }
    
    //Setters
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

    public void setLuminosityChange(int luminosityChange) {
        this.luminosityChange = luminosityChange;
    }
}

