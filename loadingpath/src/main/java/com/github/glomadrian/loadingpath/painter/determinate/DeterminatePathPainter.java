package com.github.glomadrian.loadingpath.painter.determinate;

import com.github.glomadrian.loadingpath.painter.PathPainter;

/**
 * @author Adrián García Lomas
 */
public interface DeterminatePathPainter extends PathPainter {

  /**
   * progress from 0f to 1f
   */
  void setProgress(float value);
}
