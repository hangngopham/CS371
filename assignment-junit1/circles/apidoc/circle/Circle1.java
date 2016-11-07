/**
* Implements a class Circle1 which inherits methods from Circle abstract class. A
* intersects() method is implemented here. 
**/
public class Circle1 extends Circle
{

/**
* Create new circle
* @param x is the x coordinate of the center
* @param y is the y coordinate of the center
* @param radius is the radius
**/
public Circle1(double x, double y, double radius)
{
   super(x,y,radius);
}

/**
* Test if this circle intersects another circle.
* @param other is the other circle
* @return True if the circles' outer edges intersect
*         at all, False otherwise
**/
public boolean intersects(Circle other)
{
	boolean intersect = true;
	double xDistance = Math.abs(center.x-other.center.x);
	double yDistance = Math.abs(center.y-other.center.y);
	double distance = Math.pow(xDistance*xDistance + yDistance*yDistance,0.5);
	if(distance > Math.abs(radius-other.radius) && distance < (radius + other.radius)) {
		intersect = true;
	}
	else if(distance==Math.abs(radius-other.radius)) {
		intersect = true;
	}
	else if(distance==(radius+other.radius)) {
		intersect = true;
	}
	else {
		intersect = false;
	}
	return intersect;
	  // if (Math.abs(center.x - other.center.x) < radius &&
	      // Math.abs(center.y - other.center.y) < radius)
	      //return true;
	   //return false;
}

}

