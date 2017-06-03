
import static java.lang.Math.sqrt;

public class Coords
{
    private int x; 
    private int y;


    public Coords(){
        this.x=0;
        this.y=0;
    }

    public Coords(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public Coords(Coords c){
        this.x = c.getx();
        this.y = c.gety();
    }

    public int getx(){
        return this.x;
    }
    public int gety(){
        return this.y;
    }


    public void setx(int x){
         this.x = x;
    }
    
    public void sety(int y){
         this.y = y;
    }
    
    public double distancia(Coords x){
         double dist, tempX, tempY;
         tempX = x.getx();
         tempY = x.gety();
         tempX = this.x - tempX;
         tempY = this.y - tempY;
         dist = sqrt((tempX*tempX)+(tempY*tempY));
         return dist;
    }
    
    public boolean equals(Object coords){
        if(this == coords) return true;
        if ((coords == null) || (this.getClass() != coords.getClass())) return false;
        else {
            Coords c = (Coords) coords;
            return(this.x == c.getx() && this.y == c.gety());
        }
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("X: ");
        sb.append(this.x);
        sb.append(", Y: ");
        sb.append(this.y);
        return sb.toString();
    }
    
    public Coords clone(){
        return new Coords(this);
    }
    
}
