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
      default:
        return makeTwoWayConfiguration(typedArray);
    }
  }

  private static TwoWayConfiguration makeTwoWayConfiguration(TypedArray typedArray) {
    int color = typedArray.getColor(R.styleable.LoadingPath_path_color, Color.RED);
    String movementDirection = typedArray.getString(R.styleable.LoadingPath_movement_direction);
    if (movementDirection == null) {
      movementDirection = Direction.LEFT;
    }
    float strokeWidth = typedArray.getDimension(R.styleable.LoadingPath_stroke_width, 10);
    int movementLoopTime = typedArray.getInt(R.styleable.LoadingPath_movement_loop_time, 3000);
    float lineSize = typedArray.getFloat(R.styleable.LoadingPath_line_size, 0.2f);
    int rightLineAnimationTime =
        typedArray.getInt(R.styleable.LoadingPath_right_line_animation_time, 2000);
    float rightLineMaxSize = typedArray.getFloat(R.styleable.LoadingPath_right_line_max_size, 0.3f);
    int rightLineAnimationStartDelay =
        typedArray.getInt(R.styleable.LoadingPath_right_line_animation_start_delay, 2000);
    int leftLineAnimationTime =
        typedArray.getInt(R.styleable.LoadingPath_left_line_animation_time, 2000);
    float leftLineMaxSize = typedArray.getFloat(R.styleable.LoadingPath_left_line_max_size, 0.3f);
    int leftLineAnimationStartDelay =
        typedArray.getInt(R.styleable.LoadingPath_left_line_animation_start_delay, 2000);

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
