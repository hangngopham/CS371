
/***
* Example JUnit testing class for Circle2 (and Circle)
*
* - must have your classpath set to include the JUnit jarfiles
* - to run the test do:
*     java org.junit.runner.JUnitCore Circle2Test
* - note that the commented out main is another way to run tests
* - note that normally you would not have print statements in
*   a JUnit testing class; they are here just so you see what is
*   happening. You should not have them in your test cases.
***/

import org.junit.*;
import java.util.*;

public class Circle2Test
{
   // Data you need for each test case
   private Circle2 circle;
   private Circle2 circle1;
   private Circle2 circle2;
   private Circle2 circle3;

private Circle1 c5;
private Circle c6;
private Circle c7;
private Circle c8;
private Circle c9;
private Circle c10;
private Circle c11;

// 
// Stuff you want to do before each test case
//
@Before
public void setup()
{
   System.out.println("\nTest starting...");
   circle  = new Circle2(3,4,5);
   circle1 = new Circle2(3,4,5);
   circle2 = new Circle2(3,4,5);
   circle3 = new Circle2(3,4,5);

c5 = new Circle1(0,0,2);
c6 = new Circle1(3,4,4);
c7 = new Circle1(-5,-5,1);
c8 = new Circle1(3,4,3);
c9 = new Circle1(3,4,7);
c10 = new Circle1(2,2,10);
c11 = new Circle1(10,0,2);
}

//
// Stuff you want to do after each test case
//
@After
public void teardown()
{
   System.out.println("\nTest finished.");
}

//
//Test constructor
//
@Test 
public void testConstructor1() {
  Circle1 myCircle = new Circle1(2,3,4);
  System.out.println("Running test testConstructor1.");
  Point c = myCircle.center;
  Assert.assertTrue(c.x==2);
  Assert.assertTrue(c.y==3);
  Assert.assertTrue(myCircle.radius==4);
}


//
// Test a scale method with positive factor smaller than 1
//
@Test
public void testScaleFactorSmallerThan1()
{
   double radius1 = circle1.radius;
   System.out.println("Radius of the circle before invoking scale method: " + radius1);
   System.out.println("Running test testScaleFactorSmallerThan1.");
   double radius2 = circle1.scale(0.8);
   System.out.println("Radius of the circle after invoking scale method: " + radius2);
   Assert.assertTrue(circle1.radius == radius2);
}

//
// Test a scale method with positive factor greater than 1
//
@Test
public void testScaleFactorGreaterThan1()
{
   double r1 = circle2.radius;
   System.out.println("Radius of the circle before invoking scale method: " + r1);
   System.out.println("Running test testScaleFactorGreaterThan1.");
   double r2 = circle2.scale(8);
   System.out.println("Radius of the circle after invoking scale method: " + r2);
   Assert.assertTrue(circle2.radius == r2);
}

//
// Test a scale method with negative factor 
//
@Test
public void testScaleFactorNegative()
{
   double r1 = circle3.radius;
   System.out.println("Radius of the circle before invoking scale method: " + r1);
   System.out.println("Running test testScaleFactorNegative.");
   double r2 = circle3.scale(-8);
   System.out.println("Radius of the circle after invoking scale method: " + r2);
   Assert.assertFalse(r2>0);
}

// 
// Test a simple positive move in x and y
//
@Test
public void simpleMovePosXPosY()
{
   Point p;
   System.out.println("Running test simpleMovePosXPosY.");
   p = circle.moveBy(2,2);
   Assert.assertTrue(p.x == 5 && p.y == 6);
}

// 
// Test a simple negative move in x and y
//
@Test
public void simpleMoveNegXNegY()
{
   Point p;
   System.out.println("Running test simpleMoveNegXNegY.");
   p = circle1.moveBy(-3,-3);
   Assert.assertTrue(p.x == 0 && p.y == 1);
}

// 
// Test a simple negative move in x and positive move in y
//
@Test
public void simpleMoveNegXPosY()
{
   Point p;
   System.out.println("Running test simpleMoveNegXNegY.");
   p = circle2.moveBy(-3,3);
   Assert.assertTrue(p.x == 0 && p.y == 7);
}

// 
// Test a simple positive move in x and negative move in y
//
@Test
public void simpleMovePosXNegY()
{
   Point p;
   System.out.println("Running test simpleMoveNegXNegY.");
   p = circle3.moveBy(3,-3);
   Assert.assertTrue(p.x == 6 && p.y == 1);
}

// 
// Test to see whether the two circles intersect each other or not. In this case,
// the two circles intersect at two points.
//
@Test
public void testIntersectsAtTwoPoints() {
   System.out.println("Running test testIntersectsAtTwoPoints().");
   Assert.assertEquals("The two circles does not intersect",true,c5.intersects(c6));
}

// 
// Test to see whether the two circles intersect each other or not. In this case,
// the two circles intersect at one point and outside each other.
//
@Test
public void testIntersectsAtOnePointOut() {
   System.out.println("Running test testIntersectsAtOnePoint().");
   Assert.assertEquals("The two circles does not intersect",true,c5.intersects(c8));
}

// 
// Test to see whether the two circles intersect each other or not. In this case,
// the two circles intersect at one point and c5 is inside c9.
//
@Test
public void testIntersectsAtOnePointIn1() {
   System.out.println("Running test testIntersectsAtOnePoint().");
   Assert.assertEquals("The two circles does not intersect",true,c5.intersects(c9));
}

// 
// Test to see whether the two circles intersect each other or not. In this case,
// the two circles does not intersect.
//
@Test
public void testNotIntersects1() {
   System.out.println("Running test testNotIntersects1().");
   Assert.assertEquals("The two circles does not intersect",false,c5.intersects(c7));
}

// 
// Test to see whether the two circles intersect each other or not. In this case,
// the two circles does not intersect. (distance between two center is smaller 
// than the difference of two radii)
//
@Test
public void testNotIntersects2() {
   System.out.println("Running test testNotIntersects2().");
   Assert.assertEquals("The two circles does not intersect",false,c5.intersects(c10));
}

// 
// Test to see whether the two circles intersect each other or not. In this case,
// the two circles does not intersect. (distance between two center is greater 
// than the sum of two radii)
//
@Test
public void testNotIntersects3() {
   System.out.println("Running test testNotIntersects3().");
   Assert.assertEquals("The two circles does not intersect",false,c5.intersects(c11));
}


/*** NOT USED
public static void main(String args[])
{
   try {
      org.junit.runner.JUnitCore.runClasses(
               java.lang.Class.forName("Circle2Test"));
   } catch (Exception e) {
      System.out.println("Exception: " + e);
   }
}
***/

}

