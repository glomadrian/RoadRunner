package com.github.glomadrian.loadingpath.painter.configuration;

/**
 * @author Adrián García Lomas
 */
public abstract class PathPainterConfiguration {

  protected Direction movementDirection;
  protected int color;
  protected float strokeWidth;

  public Direction getMovementDirection() {
    return movementDirection;
  }

  public void setMovementDirection(Direction movementDirection) {
    this.movementDirection = movementDirection;
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
}
