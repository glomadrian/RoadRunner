package com.github.glomadrian.loadingpath.painter.configuration;

import android.content.res.TypedArray;
import android.graphics.Color;
import com.github.glomadrian.loadingpath.R;
import com.github.glomadrian.loadingpath.painter.Painter;

/**
 * @author Adrián García Lomas
 */
public class PathPainterConfigurationFactory {

  public static PathPainterConfiguration makeConfiguration(TypedArray typedArray, Painter painter) {
    switch (painter) {
      case TWO_WAY:
        return makeTwoWayConfiguration(typedArray);
      case MATERIAL:
        return makeMaterialConfiguration(typedArray);
      default:
        return makeTwoWayConfiguration(typedArray);
    }
  }

  private static PathPainterConfiguration makeMaterialConfiguration(TypedArray typedArray) {
    int color = typedArray.getColor(R.styleable.LoadingPath_path_color, Color.RED);
    int directionValue = typedArray.getInt(R.styleable.LoadingPath_movement_direction, 0);
    Direction movementDirection = Direction.fromId(directionValue);
    float strokeWidth = typedArray.getDimension(R.styleable.LoadingPath_stroke_width, 10);

    MaterialPainterConfiguration materialPainterConfiguration =
        MaterialPainterConfiguration.newBuilder()
            .withColor(color)
            .withStrokeWidth(strokeWidth)
            .withMovementDirection(movementDirection)
            .build();

    return materialPainterConfiguration;
  }

  private static TwoWayConfiguration makeTwoWayConfiguration(TypedArray typedArray) {
    int color = typedArray.getColor(R.styleable.LoadingPath_path_color, Color.RED);
    int directionValue = typedArray.getInt(R.styleable.LoadingPath_movement_direction, 0);
    Direction movementDirection = Direction.fromId(directionValue);
    float strokeWidth = typedArray.getDimension(R.styleable.LoadingPath_stroke_width, 10);
    int movementLoopTime = typedArray.getInt(R.styleable.LoadingPath_movement_loop_time, 4000);
    float lineSize = typedArray.getFloat(R.styleable.LoadingPath_line_size, 0.05f);
    int rightLineAnimationTime =
        typedArray.getInt(R.styleable.LoadingPath_right_line_animation_time, 1400);
    float rightLineMaxSize = typedArray.getFloat(R.styleable.LoadingPath_right_line_max_size, 0.4f);
    int rightLineAnimationStartDelay =
        typedArray.getInt(R.styleable.LoadingPath_right_line_animation_start_delay, 3000);
    int leftLineAnimationTime =
        typedArray.getInt(R.styleable.LoadingPath_left_line_animation_time, 2000);
    float leftLineMaxSize = typedArray.getFloat(R.styleable.LoadingPath_left_line_max_size, 0.5f);
    int leftLineAnimationStartDelay =
        typedArray.getInt(R.styleable.LoadingPath_left_line_animation_start_delay, 1000);

    TwoWayConfiguration twoWayConfiguration = TwoWayConfiguration.newBuilder()
        .withColor(color)
        .withStrokeWidth(strokeWidth)
        .withMovementDirection(movementDirection)
        .withMovementLoopTime(movementLoopTime)
        .withMovementLineSize(lineSize)
        .withLeftLineLoopTime(leftLineAnimationTime)
        .withLeftLineStartDelayTime(leftLineAnimationStartDelay)
        .withLeftLineMaxSize(leftLineMaxSize)
        .withRightLineLoopTime(rightLineAnimationTime)
        .withRightLineMaxSize(rightLineMaxSize)
        .withRightLineStartDelayTime(rightLineAnimationStartDelay)
        .build();
    return twoWayConfiguration;
  }
}
