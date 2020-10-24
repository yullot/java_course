package qa.pkg.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    Point pointA = new Point(5, 6);
    Point pointB = new Point(0, 8);
    System.out.println("Distance between point A (" + pointA.x + ", " + pointA.y +
            ") and point B (" + pointA.x + ", " + pointA.y + ") equals to " + pointA.getDistance(pointB));
  }
}