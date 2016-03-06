package com.github.glomadrian.loadingpath.painter.indeterminate;

import com.github.glomadrian.loadingpath.painter.PathPainter;

/**
 * Define how a path is painted
 *
 * @author Adrián García Lomas
 */
public interface IndeterminatePathPainter extends PathPainter {

  void start();

  void stop();

  void restart();
}
