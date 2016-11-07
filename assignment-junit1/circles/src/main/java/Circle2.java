/**
* Implements a class Circle1 which inherits methods from Circle abstract class. A
* intersects() method is implemented here. 
**/
public class Circle2 extends Circle
{

/**
* Create new circle
* @param x is the x coordinate of the center
* @param y is the y coordinate of the center
* @param radius is the radius
**/
public Circle2(double x, double y, double radius)
{
   //super(y,x,radius);
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
   double d;
   d = Math.sqrt(Math.pow(center.x - other.center.x, 2) + 
                 Math.pow(center.y - other.center.y, 2));
   boolean intersect = true;
   if(d > Math.abs(radius-other.radius) && d < (radius+other.radius)) {
	intersect = true;
   }
   else if(d == Math.abs(radius-other.radius)) {
	intersect = true;
   }
   else if(d==(radius+other.radius)) {
	intersect = true;
   }
   else {
	intersect = false;
   }
   return intersect;
}
  // if (d < radius)
    //  return true;
  // else
     // return false;


}

