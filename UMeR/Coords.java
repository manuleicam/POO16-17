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


    public int setx(x){
    	 this.x = x;
	}
	public int sety(y){
    	 this.y = y;
	}
}
