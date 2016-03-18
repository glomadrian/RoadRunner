package com.github.glomadrian.loadingpath.painter.indeterminate.factory;

import android.view.View;
import com.github.glomadrian.loadingpath.painter.indeterminate.IndeterminatePainter;
import com.github.glomadrian.loadingpath.painter.configuration.indeterminate.MaterialPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.indeterminate.TwoWayIndeterminateConfiguration;
import com.github.glomadrian.loadingpath.painter.indeterminate.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.MaterialPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.TwoWayIndeterminatePainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class IndeterminatePainterFactory {

  public static IndeterminatePathPainter makeIndeterminatePathPainter(IndeterminatePainter indeterminatePainter,
      PathContainer pathContainer, View view, PathPainterConfiguration pathPainterConfiguration) {

    switch (indeterminatePainter) {
      case TWO_WAY:
        return makeTwoWayPainter(pathContainer, view, pathPainterConfiguration);
      case MATERIAL:
        return makeMaterialPainter(pathContainer, view, pathPainterConfiguration);
      //case TWO_WAY_DETERMINATE:
      //  return makeTwoWayDeterminatePainter(pathContainer, view, pathPainterConfiguration);
      default:
        return makeTwoWayPainter(pathContainer, view, pathPainterConfiguration);
    }
  }


  private static IndeterminatePathPainter makeMaterialPainter(PathContainer pathContainer,
      View view, PathPainterConfiguration pathPainterConfiguration) {
    return new MaterialPainter(pathContainer, view,
        (MaterialPainterConfiguration) pathPainterConfiguration);
  }

  private static TwoWayIndeterminatePainter makeTwoWayPainter(PathContainer pathContainer,
      View view,
      PathPainterConfiguration pathPainterConfiguration) {
    return new TwoWayIndeterminatePainter(view, pathContainer,
        (TwoWayIndeterminateConfiguration) pathPainterConfiguration);
  }


}
