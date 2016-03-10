package com.github.glomadrian.loadingpath.painter.determinate.factory;

import android.view.View;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.determinate.TwoWayDeterminateConfiguration;
import com.github.glomadrian.loadingpath.painter.determinate.DeterminatePainter;
import com.github.glomadrian.loadingpath.painter.determinate.DeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.determinate.TwoWayDeterminatePainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class DeterminatePathLoadingPainterFactory {

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
