package qa.pkg.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getDistance(Point p2) {
    return sqrt(pow((this.x - p2.x), 2) + pow((this.y - p2.y), 2));
  }
}
