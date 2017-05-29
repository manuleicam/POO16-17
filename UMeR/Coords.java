
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
    
}
