package com.github.glomadrian.loadingpath.painter.indeterminate.factory;

import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.github.glomadrian.loadingpath.painter.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.LoopPathLoadingPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.TwoWayPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class PathLoadingPainterFactory {

  public static final String LOOP = "loop";
  public static final String MATERIAL = "material";
  public static final String TWO_WAY = "towWay";

  public static IndeterminatePathPainter makeIndeterminatePathPainter(String pathLoading,
      PathContainer pathContainer, View view) {

    switch (pathLoading) {
      case LOOP:
        return makeLoopPathLoadingPainter(pathContainer, view);
      case TWO_WAY:
        return makeTwoWayPainter(pathContainer, view);
      default:
        return makeLoopPathLoadingPainter(pathContainer, view);
    }
  }

  private static LoopPathLoadingPainter makeLoopPathLoadingPainter(PathContainer pathContainer,
      View view) {
    return LoopPathLoadingPainter.newBuilder()
        .withAnimationDuration(5000)
        .withPathContainer(pathContainer)
        .withAnimationInterpolator(new LinearInterpolator())
        .withStrokeColor(Color.WHITE)
        .withView(view)
        .withStrokeWidth(15)
        .withVisibleLineFraction(0.3f)
        .build();
  }

  private static TwoWayPainter makeTwoWayPainter(PathContainer pathContainer, View view) {
    return TwoWayPainter.newBuilder()
        .withView(view)
        .withPathContainer(pathContainer)
        .withColor(Color.WHITE)
        .build();
  }
}
