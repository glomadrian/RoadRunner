package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import com.github.glomadrian.loadingpath.painter.PathPainter;
import com.github.glomadrian.loadingpath.painter.dashed.StrokeDeterminateAnimatePathPainter;
import com.github.glomadrian.loadingpath.svg.ConstrainedSvgPathParser;
import com.github.glomadrian.loadingpath.svg.SvgPathParser;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public abstract class LoadingPath extends View {

  private PathContainer pathData;
  private PathPainter pathPainter;
  private int originalWidth = 512;
  private int originalHeight = 512;

  public LoadingPath(Context context) {
    super(context);
  }

  public LoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void initPathData(int viewWidth, int viewHeight) {
    pathData = new PathContainer();
    SvgPathParser svgPathParser =
        new ConstrainedSvgPathParser.Builder().originalWidth(originalWidth)
            .originalHeight(originalHeight)
            .viewHeight(viewHeight)
            .viewWidth(viewWidth)
            .build();

    try {
      pathData.path = svgPathParser.parsePath(Paths.GITHUB);
    } catch (ParseException e) {
      //TODO implement
    }
    PathMeasure pm = new PathMeasure(pathData.path, true);
    while (true) {
      pathData.length = Math.max(pathData.length, pm.getLength());
      if (!pm.nextContour()) {
        break;
      }
    }
  }

  private void initPathPainter() {
    pathPainter = new StrokeDeterminateAnimatePathPainter(pathData, this);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    initPathData(w, h);
    initPathPainter();
    pathPainter.onViewSizeChange(w, h, oldw, oldh);
  }
}
