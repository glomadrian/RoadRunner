package com.github.glomadrian.loadingpath.painter;

import android.graphics.Canvas;

/**
 * Define how a path is painted
 *
 * @author Adrián García Lomas
 */
public interface PathPainter {

  void paintPath(Canvas canvas);

  void onViewSizeChange(int w, int h, int oldw, int oldh);
}
