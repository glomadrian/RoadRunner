package com.github.glomadrian.loadingpath.painter.determinate.factory;

import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.github.glomadrian.loadingpath.painter.determinate.DeterminatePathLoadingPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class DeterminatePathLoadingFactory {

  public static DeterminatePathLoadingPainter makeDeterminatePathLoadingPainter(
      PathContainer pathContainer, View view) {
    return DeterminatePathLoadingPainter.newBuilder()
        .withAnimationDuration(400)
        .withPathContainer(pathContainer)
        .withAnimationInterpolator(new LinearInterpolator())
        .withStrokeColor(Color.GREEN)
        .withView(view)
        .withStrokeWidth(10)
        .build();
  }
}
