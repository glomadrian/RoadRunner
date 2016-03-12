package com.github.glomadrian.loadingpath.painter;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.github.glomadrian.loadingpath.painter.configuration.Direction;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * Define common actions to all LoadingPathPainters
 *
 * @author Adrián García Lomas
 */
public abstract class LoadingPathPainter extends PointPathPainter {

  protected Paint paint;
  protected int color = Color.WHITE;
  protected float strokeWidth = 20;
  protected float zone = 0f;
  protected int movementLinePoints = 50;
  protected Direction movementDirection = Direction.CLOCKWISE;

  public LoadingPathPainter(PathContainer pathData, View view) {
    super(pathData, view);
  }

  @Override public void setPosition(float position) {
    if (position > 0f && position < 1f) {
      zone = position;
      view.invalidate();
    }
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public float getStrokeWidth() {
    return strokeWidth;
  }

  public void setStrokeWidth(float strokeWidth) {
    this.strokeWidth = strokeWidth;
  }

  public int getMovementLinePoints() {
    return movementLinePoints;
  }

  public void setMovementLinePoints(int movementLinePoints) {
    this.movementLinePoints = movementLinePoints;
  }

  public Direction getMovementDirection() {
    return movementDirection;
  }

  public void setMovementDirection(
      Direction movementDirection) {
    this.movementDirection = movementDirection;
  }
}
