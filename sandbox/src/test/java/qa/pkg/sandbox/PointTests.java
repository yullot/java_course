package qa.pkg.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistanceZeroPoint() {
    Point pointC = new Point(0, 0);
    Point pointB = new Point(-5, -8);
    Assert.assertEquals(pointC.getDistance(pointB), 9.4339, 0.0001);
  }

  @Test
  public void testDistanceForOnePoint() {
    Point pointC = new Point(6, 120);
    Point pointB = new Point(6, 120);
    Assert.assertEquals(pointC.getDistance(pointB), 0);
  }

  @Test
  public void testDistanceForNegativeAndPositiveCoord() {
    Point pointC = new Point(-6, 120);
    Point pointB = new Point(6, -120);
    Assert.assertEquals(pointC.getDistance(pointB), 240.2998, 0.0001);
  }

  @Test
  public void testDistanceIntegerAnswer() {
    Point pointC = new Point(1, 5);
    Point pointB = new Point(4, 1);
    Assert.assertEquals(pointC.getDistance(pointB), 5);
  }
}
