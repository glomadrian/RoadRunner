package com.github.glomadrian.roadrunner.painter.determinate;

import com.github.glomadrian.roadrunner.painter.PathPainter;

/**
 * @author Adrián García Lomas
 */
public interface DeterminatePathPainter extends PathPainter {

  /**
   * progress from 0f to 1f
   */
  void setProgress(float value);
}
