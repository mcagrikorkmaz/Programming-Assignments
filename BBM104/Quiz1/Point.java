//NAME: Mustafa Çağrı
//SURNAME: Korkmaz
//NUMBER: 2200356833


public class Point {
	private double x;
	private double y;
	public Point()// creates a point at (0,0)
    {
    	this.x = 0;
    	this.y = 0;
    }

	public Point(double x, double y) // creates a point at (x,y)
	{	
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;

	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	// returns quadrant number (1,2,3,4) where a point is on the coordinate plane
	public int quadrant()
	{
		if(this.x>0&&this.y>0) return 1;
		else if(this.x<0&&this.y>0) return 2;
		else if(this.x<0&&this.y<0) return 3;
		else return 4;
	}
	//returns double value which is distance of between two point objects
  	public static double distance(Point obj1,Point obj2)
  	{
  		return Math.sqrt(Math.pow(obj1.x-obj2.x,2)+Math.pow(obj1.y-obj2.y,2));
  	} 
	// checks if two point objects hold equivalent data and if equals returns true, else returns false
	boolean equals(Point obj)
	{
      return (this.x==obj.x&&this.y==obj.y);
	}
   
	public String toString(/*parameters if necessary*/){
		return "("+this.x+","+this.y+")";
	}
}
