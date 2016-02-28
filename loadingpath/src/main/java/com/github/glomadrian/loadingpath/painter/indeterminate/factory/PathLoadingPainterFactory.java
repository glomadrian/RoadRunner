package com.github.glomadrian.loadingpath.painter.indeterminate.factory;

import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.github.glomadrian.loadingpath.painter.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.LoopPathLoadingPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class PathLoadingPainterFactory {

  public static final String LOOP = "loop";

  public static IndeterminatePathPainter makeIndeterminatePathPainter(String pathLoading,
      PathContainer pathContainer, View view) {

    switch (pathLoading) {
      case LOOP:
        return makeLoopPathLoadingPainter(pathContainer, view);
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
        .withStrokeColor(Color.GREEN)
        .withView(view)
        .withStrokeWidth(15)
        .withVisibleLineFraction(0.3f)
        .build();
  }
}
