package com.github.glomadrian.loadingpath.painter.configuration.indeterminate;

import com.github.glomadrian.loadingpath.painter.configuration.Direction;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfiguration;

/**
 * @author Adrián García Lomas
 */
public class MaterialPainterConfiguration extends PathPainterConfiguration {

  private MaterialPainterConfiguration(Builder builder) {
    setMovementDirection(builder.movementDirection);
    setColor(builder.color);
    setStrokeWidth(builder.strokeWidth);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private Direction movementDirection;
    private int color;
    private float strokeWidth;

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

    public MaterialPainterConfiguration build() {
      return new MaterialPainterConfiguration(this);
    }
  }
}
