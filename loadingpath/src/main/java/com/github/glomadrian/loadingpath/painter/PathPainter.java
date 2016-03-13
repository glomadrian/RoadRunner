package com.github.glomadrian.loadingpath.painter;

import android.graphics.Canvas;

/**
 * Define how a path is painted
 *
 * @author Adrián García Lomas
 */
public interface PathPainter {

  void start();

  void stop();

  void restart();

  void paintPath(Canvas canvas);

  void setPosition(float position);

  void setColor(int color);
}
