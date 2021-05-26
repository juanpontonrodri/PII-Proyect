public class Circle extends Figure {
    private int radius;
            
    private static final double PI=3.141592654;

    
    Circle(int radius, Color color) {
        super(color);
        if (radius>50){
            this.radius=49;
        }
        else {
        if (radius<0) this.radius=1;
        else this.radius=radius;
        }
    }

    
    public double area() { 
        return PI*(this.radius*this.radius)/100;    
    }
    
    public boolean isEqualTo(Circle circle) {	
        return ((this.color.isEqualTo(circle.getColor()))&&(this.radius==circle.radius));
    }
    @Override
    public String toString() {                
        return "Radius: " + radius + ", Color: " + color;                
    }	

    
    // Getters And Setters
    public int getRadius(){
        return this.radius;
    }
    
    public void setRadius(int radius){
        this.radius=radius;
    }
 }
