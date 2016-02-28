package com.github.glomadrian.loadingpath.painter;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.View;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public abstract class FractionPathPainter implements PathPainter {

  protected PathContainer pathData;
  protected View view;

  public FractionPathPainter(PathContainer pathData, View view) {
    this.pathData = pathData;
    this.view = view;
  }

  protected void drawPathInterval(Canvas canvas, float fractionA, float fractionB, Paint paint) {
    float[] dashes =
        { 0f, fractionA * pathData.length, fractionB * pathData.length, pathData.length };
    paint.setPathEffect(new DashPathEffect(dashes, 0));
    canvas.drawPath(pathData.path, paint);
  }
}
