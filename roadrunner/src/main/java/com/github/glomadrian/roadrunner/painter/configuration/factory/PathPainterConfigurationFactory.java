package com.github.glomadrian.roadrunner.painter.configuration.factory;

import android.content.res.TypedArray;
import android.graphics.Color;
import com.github.glomadrian.roadrunner.R;
import com.github.glomadrian.roadrunner.painter.configuration.Direction;
import com.github.glomadrian.roadrunner.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.roadrunner.painter.configuration.determinate.TwoWayDeterminateConfiguration;
import com.github.glomadrian.roadrunner.painter.configuration.indeterminate.MaterialPainterConfiguration;
import com.github.glomadrian.roadrunner.painter.configuration.indeterminate.TwoWayIndeterminateConfiguration;
import com.github.glomadrian.roadrunner.painter.determinate.DeterminatePainter;
import com.github.glomadrian.roadrunner.painter.indeterminate.IndeterminatePainter;

/**
 * @author Adrián García Lomas
 */
public class PathPainterConfigurationFactory {

  public static PathPainterConfiguration makeConfiguration(TypedArray typedArray,
      IndeterminatePainter indeterminatePainter) {
    switch (indeterminatePainter) {
      case TWO_WAY:
        return makeTwoWayConfiguration(typedArray);
      case MATERIAL:
        return makeMaterialConfiguration(typedArray);
      default:
        return makeTwoWayConfiguration(typedArray);
    }
  }

  public static PathPainterConfiguration makeConfiguration(TypedArray typedArray,
      DeterminatePainter indeterminatePainter) {
    switch (indeterminatePainter) {
      case TWO_WAY:
        return makeTwoWayDeterminateConfiguration(typedArray);
      default:
        return makeTwoWayDeterminateConfiguration(typedArray);
    }
  }

  private static PathPainterConfiguration makeMaterialConfiguration(TypedArray typedArray) {
    int color = typedArray.getColor(R.styleable.RoadRunner_path_color, Color.RED);
    int directionValue = typedArray.getInt(R.styleable.RoadRunner_movement_direction, 0);
    Direction movementDirection = Direction.fromId(directionValue);
    float strokeWidth = typedArray.getDimension(R.styleable.RoadRunner_stroke_width, 10);

    MaterialPainterConfiguration materialPainterConfiguration =
        MaterialPainterConfiguration.newBuilder()
            .withColor(color)
            .withStrokeWidth(strokeWidth)
            .withMovementDirection(movementDirection)
            .build();

    return materialPainterConfiguration;
  }

  private static TwoWayIndeterminateConfiguration makeTwoWayConfiguration(TypedArray typedArray) {
    int color = typedArray.getColor(R.styleable.RoadRunner_path_color, Color.RED);
    int directionValue = typedArray.getInt(R.styleable.RoadRunner_movement_direction, 0);
    Direction movementDirection = Direction.fromId(directionValue);
    float strokeWidth = typedArray.getDimension(R.styleable.RoadRunner_stroke_width, 10);
    int movementLoopTime = typedArray.getInt(R.styleable.RoadRunner_movement_loop_time, 4000);
    float lineSize = typedArray.getFloat(R.styleable.RoadRunner_line_size, 0.05f);
    int rightLineAnimationTime =
        typedArray.getInt(R.styleable.RoadRunner_right_line_animation_time, 1400);
    float rightLineMaxSize = typedArray.getFloat(R.styleable.RoadRunner_right_line_max_size, 0.4f);
    int rightLineAnimationStartDelay =
        typedArray.getInt(R.styleable.RoadRunner_right_line_animation_start_delay, 3000);
    int leftLineAnimationTime =
        typedArray.getInt(R.styleable.RoadRunner_left_line_animation_time, 2000);
    float leftLineMaxSize = typedArray.getFloat(R.styleable.RoadRunner_left_line_max_size, 0.5f);
    int leftLineAnimationStartDelay =
        typedArray.getInt(R.styleable.RoadRunner_left_line_animation_start_delay, 1000);

    TwoWayIndeterminateConfiguration twoWayIndeterminateConfiguration =
        TwoWayIndeterminateConfiguration
            .newBuilder()
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
    return twoWayIndeterminateConfiguration;
  }

  private static TwoWayDeterminateConfiguration makeTwoWayDeterminateConfiguration(
      TypedArray typedArray) {
    int color = typedArray.getColor(R.styleable.RoadRunner_path_color, Color.RED);
    int directionValue = typedArray.getInt(R.styleable.RoadRunner_movement_direction, 0);
    Direction movementDirection = Direction.fromId(directionValue);
    float strokeWidth = typedArray.getDimension(R.styleable.RoadRunner_stroke_width, 10);
    int movementLoopTime = typedArray.getInt(R.styleable.RoadRunner_movement_loop_time, 4000);
    float lineSize = typedArray.getFloat(R.styleable.RoadRunner_line_size, 0.05f);

    TwoWayDeterminateConfiguration twoWayDeterminateConfiguration =
        TwoWayDeterminateConfiguration.newBuilder()
            .withColor(color)
            .withStrokeWidth(strokeWidth)
            .withMovementDirection(movementDirection)
            .withMovementLoopTime(movementLoopTime)
            .withMovementLineSize(lineSize)
            .build();

    return twoWayDeterminateConfiguration;
  }
}
