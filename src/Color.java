public class Color {

    private int r;
    private int g;
    private int b;

    private static final int MAX = 255;


    Color(int r, int g, int b) {
        if ((r > MAX) || (g > MAX) || (b > MAX) ) {
            this.r= MAX;
            this.g= MAX;
            this.b= MAX;
            // PROJECT TASK: In this case (during the programming of the
            // project) and after you have learned to use exceptions, you
            // must generate an exception to deal with the case that any
            // of the r, g or b values is greater than 255.
        }
        else {
            this.r= r;
            this.g= g;
            this.b= b;
        }
    }


    // Static Methods
    public static final Color WHITE() {
        return new Color(255,255,255);
    }
    public static final Color BLACK() {
        return new Color (0,0,0);
    }
    public static final Color RED() {
        return new Color (229,20,0);
    }
    public static final Color GREEN() {
        return new Color (96,169,23);
    }
    public static final Color BLUE() {
        return new Color (0,80,239);
    }
    public static final Color YELLOW() {
        return new Color (227,200,0);
    }
    public static final Color MAGENTA() {
        return new Color (216,0,115);
    }
    public static final Color CYAN() {
        return new Color (27,161,226);
    }

    public boolean isEqualTo (Color color) {
        return ((this.r==color.r)&&(this.g==color.g)&&(this.b==color.b)) ;
    }

    public String toString() {
        return "R"+r+"G"+g+"B"+b;
    }

   public void setR(int r){
       this.r=r;
   }

   public void setG(int g){
    this.g=g;
    }

    public void setB(int b){
        this.b=b;
    }

    public int getR() {
        return this.r;
    }

    public int getG() {
        return this.g;
    }

    public int getB() {
        return this.b;
    }
}
