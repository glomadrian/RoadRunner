package com.github.glomadrian.roadrunner.painter.determinate.factory;

import android.view.View;
import com.github.glomadrian.roadrunner.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.roadrunner.painter.configuration.determinate.TwoWayDeterminateConfiguration;
import com.github.glomadrian.roadrunner.painter.determinate.DeterminatePainter;
import com.github.glomadrian.roadrunner.painter.determinate.DeterminatePathPainter;
import com.github.glomadrian.roadrunner.painter.determinate.TwoWayDeterminatePainter;
import com.github.glomadrian.roadrunner.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class DeterminatePainterFactory {

  public static DeterminatePathPainter makeIndeterminatePathPainter(
      DeterminatePainter determinatePainter,
      PathContainer pathContainer, View view, PathPainterConfiguration pathPainterConfiguration) {

    switch (determinatePainter) {
      case TWO_WAY:
        return makeTwoWayDeterminatePainter(pathContainer, view, pathPainterConfiguration);
      default:
        return makeTwoWayDeterminatePainter(pathContainer, view, pathPainterConfiguration);
    }
  }

  private static DeterminatePathPainter makeTwoWayDeterminatePainter(PathContainer pathContainer,
      View view, PathPainterConfiguration pathPainterConfiguration) {
    return new TwoWayDeterminatePainter(pathContainer, view,
        (TwoWayDeterminateConfiguration) pathPainterConfiguration);
  }
}
