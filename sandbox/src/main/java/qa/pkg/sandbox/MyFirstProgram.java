package qa.pkg.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    Point pointA = new Point(5, 6);
    Point pointB = new Point(0, 8);
    System.out.println("The distance between a point A (" + pointA.x + ", " + pointA.y +
            ") and a point B (" + pointB.x + ", " + pointB.y + ") equals to " + pointA.getDistance(pointB));
  }
}