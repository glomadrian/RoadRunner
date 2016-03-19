package com.github.glomadrian.roadrunner.painter.configuration.determinate;

import com.github.glomadrian.roadrunner.painter.configuration.Direction;
import com.github.glomadrian.roadrunner.painter.configuration.PathPainterConfiguration;

/**
 * @author Adrián García Lomas
 */
public class TwoWayDeterminateConfiguration extends PathPainterConfiguration {

  protected int movementLoopTime;
  protected float movementLineSize;

  private TwoWayDeterminateConfiguration(Builder builder) {
    setMovementDirection(builder.movementDirection);
    setColor(builder.color);
    setStrokeWidth(builder.strokeWidth);
    setMovementLoopTime(builder.movementLoopTime);
    setMovementLineSize(builder.movementLineSize);
  }

  public static Builder newBuilder() {
    return new Builder();
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

  public static final class Builder {
    private Direction movementDirection;
    private int color;
    private float strokeWidth;
    private int movementLoopTime;
    private float movementLineSize;

    private Builder() {
    }

    public Builder withMovementDirection(Direction val) {
      movementDirection = val;
      return this;
    }

    public Builder withColor(int val) {
      color = val;
      return this;
    }

    public Builder withStrokeWidth(float val) {
      strokeWidth = val;
      return this;
    }

    public Builder withMovementLoopTime(int val) {
      movementLoopTime = val;
      return this;
    }

    public Builder withMovementLineSize(float val) {
      movementLineSize = val;
      return this;
    }

    public TwoWayDeterminateConfiguration build() {
      return new TwoWayDeterminateConfiguration(this);
    }
  }
}
