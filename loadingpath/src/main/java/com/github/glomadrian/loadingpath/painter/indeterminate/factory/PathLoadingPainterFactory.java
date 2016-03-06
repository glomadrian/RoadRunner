package com.github.glomadrian.loadingpath.painter.indeterminate.factory;

import android.view.View;
import com.github.glomadrian.loadingpath.painter.indeterminate.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.Painter;
import com.github.glomadrian.loadingpath.painter.configuration.MaterialPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.TwoWayConfiguration;
import com.github.glomadrian.loadingpath.painter.indeterminate.MaterialPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.TwoWayPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class PathLoadingPainterFactory {

  public static IndeterminatePathPainter makeIndeterminatePathPainter(Painter painter,
      PathContainer pathContainer, View view, PathPainterConfiguration pathPainterConfiguration) {

    switch (painter) {
      case TWO_WAY:
        return makeTwoWayPainter(pathContainer, view, pathPainterConfiguration);
      case MATERIAL:
        return makeMaterialPainter(pathContainer, view, pathPainterConfiguration);
      default:
        return makeTwoWayPainter(pathContainer, view, pathPainterConfiguration);
    }
  }

  private static IndeterminatePathPainter makeMaterialPainter(PathContainer pathContainer,
      View view, PathPainterConfiguration pathPainterConfiguration) {
    return new MaterialPainter(pathContainer, view,
        (MaterialPainterConfiguration) pathPainterConfiguration);
  }

  private static TwoWayPainter makeTwoWayPainter(PathContainer pathContainer, View view,
      PathPainterConfiguration pathPainterConfiguration) {
    return new TwoWayPainter(view, pathContainer, (TwoWayConfiguration) pathPainterConfiguration);
  }
}
