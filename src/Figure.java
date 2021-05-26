public abstract class Figure {
    protected Color color;

    protected Figure(Color color){
        this.color=color;
    }

    //Methods
    public abstract double area();

    public boolean isEqualTo(Figure figure){
        return this.color.isEqualTo(figure.getColor());
    }
    public String toString(){
        return "Color: "+ this.color;
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color){
        this.color=color;
    }
}
