package com.github.glomadrian.loadingpath.painter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.view.View;
import com.github.glomadrian.loadingpath.PathData;
import java.util.ArrayList;
import java.util.List;

/**
 * Paint a path using a fraction for start and end
 *
 * @author Adrián García Lomas
 */
public abstract class FractionPathPainter implements PathPainter {

  private PathData pathData;
  private List<Point> pathPoints;
  private float pointsToDraw = 1000f;
  private View view;

  public FractionPathPainter(PathData pathData, View view) {
    this.pathData = pathData;
    this.view = view;
    init();
  }

  private void init() {
    pathPoints = obtainAllPathPoints(pathData.path);
  }

  private List<Point> obtainAllPathPoints(Path path) {
    List<Point> points = new ArrayList<>();
    PathMeasure pm = new PathMeasure(path, false);
    float aCoordinates[] = { 0f, 0f };
    for (int i = 0; i < pointsToDraw; i++) {
      float point = i / pointsToDraw;
      pm.getPosTan(pm.getLength() * point, aCoordinates, null);
      int x = (int) aCoordinates[0];
      int y = (int) aCoordinates[1];
      points.add(new Point(x, y));
    }
    return points;
  }

  protected void drawPathFraction(Canvas canvas, float fraction, Paint paint) {
    Point point = obtainPoint(fraction);
    canvas.drawPoint(point.x, point.y, paint);
  }

  protected void drawPathInterval(Canvas canvas, float fractionA, float fractionB, Paint paint) {
    List<Point> points = obtainPoints(fractionA, fractionB);
    for (Point point : points) {
      canvas.drawPoint(point.x, point.y, paint);
    }
  }

  /**
   * Obtain the point to draw based on a fraction of the path from 0.0 to 1.0
   */
  protected Point obtainPoint(float fraction) {
    int point = (int) (fraction * pathPoints.size());
    return pathPoints.get(point);
  }

  /**
   * Given a start fraction and end fraction between 0.0 and 1.0 return all the points
   * from the path
   */
  protected List<Point> obtainPoints(float initFraction, float endFraction) {
    int pointA = (int) (initFraction * pathPoints.size());
    int pointB = (int) (endFraction * pathPoints.size());

    List<Point> points = new ArrayList<>();
    for (int i = pointA; i < pointB; i++) {
      if (isValidPoint(i)) {
        points.add(pathPoints.get(i));
      } else {
        int pointNormalized = normalizePoint(i);
        points.add(pathPoints.get(pointNormalized));
      }
    }

    return points;
  }

  /**
   * Obtain the subPath fraction from the complete path using the init and end fraction
   * @param initFraction
   * @param endFraction
   * @return
   */
  protected Path obtainPath(float initFraction, float endFraction){

  }

  /**
   * If a path point if negative or beats the max then return a valid point
   */
  protected int normalizePoint(int point) {
    if (point < 0) {
      return pathPoints.size() - Math.abs(point);
    } else if (point >= pathPoints.size()) {
      return Math.abs(point - pathPoints.size());
    }
    return point;
  }

  /**
   * Check if the point is a valid point to draw
   */
  private boolean isValidPoint(int point) {
    return point > 0 && point < pathPoints.size() - 1;
  }

  protected void requestInvalidate() {
    view.invalidate();
  }

  public View getView() {
    return view;
  }
}
