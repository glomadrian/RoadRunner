package com.github.glomadrian.roadrunner.painter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;
import com.github.glomadrian.roadrunner.path.PathContainer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public abstract class PointPathPainter implements PathPainter {

  private static final String TAG = "PointPathPainter";
  protected PathContainer pathData;
  protected View view;
  protected int pointsNumber = 10000;
  protected int maxLinePoints = pointsNumber / 2;
  protected List<FloatPoint> points;

  public PointPathPainter(PathContainer pathData, View view) {
    this.pathData = pathData;
    this.view = view;
    points = obtainPoints(pathData.path);
  }

  /**
   * Given a path obtain the list of points This method make use of pointsNumber size
   */
  private List<FloatPoint> obtainPoints(Path path) {
    List<FloatPoint> floatPoints = new ArrayList<>();
    for (float i = 0; i < pointsNumber; i++) {
      float fraction = i / pointsNumber;

      float[] cords = getPathCoordinates(path, fraction);
      if (cords[0] != 0 && cords[1] != 0) {
        FloatPoint point = new FloatPoint(cords[0], cords[1]);
        floatPoints.add(point);
      }
    }
    return floatPoints;
  }

  private float[] getPathCoordinates(Path path, float fraction) {
    float aCoordinates[] = { 0f, 0f };
    PathMeasure pm = new PathMeasure(path, false);
    pm.getPosTan(pm.getLength() * fraction, aCoordinates, null);
    return aCoordinates;
  }

  /**
   * Paint a line with a offset for right and left
   */
  protected void drawWithOffset(float zone, int pointsRight, int pointsLeft, float fixedPoints,
      Canvas canvas, Paint paint) {

    int position = getPositionForZone(zone);
    int firstPosition = (int) (position - pointsLeft - fixedPoints);
    int lastPosition = (int) (position + pointsRight + fixedPoints);

    /**
     * If the last position is greater than the limit then draw from 0 to the offset of this limit
     * also change the last point to the last drawable point without offset
     * for the other line painting
     */
    if (lastPosition > pointsNumber - 1 && lastPosition != pointsNumber) {
      int offset = lastPosition - pointsNumber - 1;
      float[] pointsF = getArrayFloat(points.subList(0, offset));
      lastPosition = pointsNumber - 1;
      canvas.drawPoints(pointsF, paint);
    }

    /**
     * If the firs position is negative then paint the negative offset
     */
    if (firstPosition < 0) {
      int offset = Math.abs(firstPosition);
      float[] pointsF =
          getArrayFloat(points.subList((pointsNumber - 1) - offset, pointsNumber - 1));
      canvas.drawPoints(pointsF, paint);
      firstPosition = 0;
    }

    float[] pointsF = getArrayFloat(points.subList(firstPosition, lastPosition));

    canvas.drawPoints(pointsF, paint);
  }

  protected int getPositionForZone(float zone) {
    return (int) (zone * pointsNumber);
  }

  /**
   * Given a list of points obtain a float[] points to be draw using canvas.drawPoints() method
   */
  protected float[] getArrayFloat(List<FloatPoint> points) {
    float[] floats = new float[points.size() * 2];
    int iList;

    for (int i = 0; i < floats.length; i += 2) {
      iList = i / 2;
      floats[i] = points.get(iList).x;
      floats[i + 1] = points.get(iList).y;
    }
    return floats;
  }

  /**
   * Draw a point in canvas
   */
  protected void drawPoint(FloatPoint point, Canvas canvas, Paint paint) {
    canvas.drawPoint(point.x, point.y, paint);
  }

  /**
   * Get the number of points in the given range from 0f to 1f 1f is the complete path
   */
  protected int getNumberOfPointsInRange(float range) {
    return (int) (range * pointsNumber);
  }

  /**
   * Given a range and line size obtain the number of points to draw
   */
  protected int getNumberOfLinePointsInRange(float range) {
    return (int) (range * maxLinePoints);
  }

  protected class FloatPoint {
    float x;
    float y;

    public FloatPoint(float x, float y) {
      this.x = x;
      this.y = y;
    }
  }
}
