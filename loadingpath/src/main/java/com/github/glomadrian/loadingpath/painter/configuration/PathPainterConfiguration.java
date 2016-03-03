package com.github.glomadrian.loadingpath.painter.configuration;

/**
 * @author Adrián García Lomas
 */
public class PathPainterConfiguration {

  protected String movementDirection;
  protected int color;
  protected float strokeWidth;
  protected int movementLoopTime;
  protected float movementLineSize;

  public String getMovementDirection() {
    return movementDirection;
  }

  public void setMovementDirection(String movementDirection) {
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

  public int getMovementLoopTime() {
    return movementLoopTime;
  }

  public void setMovementLoopTime(int movementLoopTime) {
    this.movementLoopTime = movementLoopTime;
  }

  public float getMovementLineSize() {
    return movementLineSize;
  }

  public void setMovementLineSize(float movementLineSize) {
    this.movementLineSize = movementLineSize;
  }
}
