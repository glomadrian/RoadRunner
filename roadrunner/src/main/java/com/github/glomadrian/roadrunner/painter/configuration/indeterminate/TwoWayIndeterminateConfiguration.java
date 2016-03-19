package com.github.glomadrian.roadrunner.painter.configuration.indeterminate;

import com.github.glomadrian.roadrunner.painter.configuration.Direction;
import com.github.glomadrian.roadrunner.painter.configuration.PathPainterConfiguration;

/**
 * @author Adrián García Lomas
 */
public class TwoWayIndeterminateConfiguration extends PathPainterConfiguration {

  protected int movementLoopTime;
  protected float movementLineSize;
  private int rightLineLoopTime;
  private int rightLineStartDelayTime;
  private float rightLineMaxSize;
  private int leftLineLoopTime;
  private int leftLineStartDelayTime;
  private float leftLineMaxSize;

  private TwoWayIndeterminateConfiguration(Builder builder) {
    setMovementDirection(builder.movementDirection);
    setColor(builder.color);
    setStrokeWidth(builder.strokeWidth);
    setMovementLoopTime(builder.movementLoopTime);
    setMovementLineSize(builder.movementLineSize);
    setRightLineLoopTime(builder.rightLineLoopTime);
    setRightLineStartDelayTime(builder.rightLineStartDelayTime);
    setRightLineMaxSize(builder.rightLineMaxSize);
    setLeftLineLoopTime(builder.leftLineLoopTime);
    setLeftLineStartDelayTime(builder.leftLineStartDelayTime);
    setLeftLineMaxSize(builder.leftLineMaxSize);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public int getRightLineLoopTime() {
    return rightLineLoopTime;
  }

  public void setRightLineLoopTime(int rightLineLoopTime) {
    this.rightLineLoopTime = rightLineLoopTime;
  }

  public int getRightLineStartDelayTime() {
    return rightLineStartDelayTime;
  }

  public void setRightLineStartDelayTime(int rightLineStartDelayTime) {
    this.rightLineStartDelayTime = rightLineStartDelayTime;
  }

  public float getRightLineMaxSize() {
    return rightLineMaxSize;
  }

  public void setRightLineMaxSize(float rightLineMaxSize) {
    this.rightLineMaxSize = rightLineMaxSize;
  }

  public int getLeftLineLoopTime() {
    return leftLineLoopTime;
  }

  public void setLeftLineLoopTime(int leftLineLoopTime) {
    this.leftLineLoopTime = leftLineLoopTime;
  }

  public int getLeftLineStartDelayTime() {
    return leftLineStartDelayTime;
  }

  public void setLeftLineStartDelayTime(int leftLineStartDelayTime) {
    this.leftLineStartDelayTime = leftLineStartDelayTime;
  }

  public float getLeftLineMaxSize() {
    return leftLineMaxSize;
  }

  public void setLeftLineMaxSize(float leftLineMaxSize) {
    this.leftLineMaxSize = leftLineMaxSize;
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
    private int rightLineLoopTime;
    private int rightLineStartDelayTime;
    private float rightLineMaxSize;
    private int leftLineLoopTime;
    private int leftLineStartDelayTime;
    private float leftLineMaxSize;

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

    public Builder withRightLineLoopTime(int val) {
      rightLineLoopTime = val;
      return this;
    }

    public Builder withRightLineStartDelayTime(int val) {
      rightLineStartDelayTime = val;
      return this;
    }

    public Builder withRightLineMaxSize(float val) {
      rightLineMaxSize = val;
      return this;
    }

    public Builder withLeftLineLoopTime(int val) {
      leftLineLoopTime = val;
      return this;
    }

    public Builder withLeftLineStartDelayTime(int val) {
      leftLineStartDelayTime = val;
      return this;
    }

    public Builder withLeftLineMaxSize(float val) {
      leftLineMaxSize = val;
      return this;
    }

    public TwoWayIndeterminateConfiguration build() {
      return new TwoWayIndeterminateConfiguration(this);
    }
  }
}
