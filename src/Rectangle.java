public class Rectangle extends Figure {
    private int width;
    private int height;
    
    Rectangle(int width, int height, Color color){
        super(color);
        if (height>100) this.height=100;
        else{
            if (height<0) this.height=0;
            else this.height = height;
        }

        if (width>100) this.width=100;
        else{
            if(width<0) this.width=0;
            else this.width=width;
        }
    }

    public double area(){
        return (this.width*this.height)/100.00;
    }
    public boolean isEqualTo(Rectangle rectangle) {		
        return ((this.color.isEqualTo(rectangle.getColor()))&&(this.width==rectangle.getWidth())&&(this.height==rectangle.getHeight()));     
    }
    
    @Override
    public String toString() {
                
        return "Width: " + this.width + ", Height:" + this.height + ", Color: " + this.color;
                
    }	

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    
    public void setWidth(int i){
        this.width=i;
    }
    public void setHeight(int i){
        this.height = i;
    }
}
