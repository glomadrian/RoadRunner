package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import com.github.glomadrian.loadingpath.path.PathContainer;
import com.github.glomadrian.loadingpath.svg.ConstrainedSvgPathParser;
import com.github.glomadrian.loadingpath.svg.SvgPathParser;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public abstract class LoadingPath extends View {

  public LoadingPath(Context context) {
    super(context);
  }

  public LoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  protected PathContainer buildPathData(int viewWidth, int viewHeight, String pathData,
      int originalWidth, int originalHeight) throws ParseException {
    Path path = parsePath(pathData, originalWidth, originalHeight, viewHeight, viewWidth);
    PathContainer pathContainer = new PathContainer();
    pathContainer.path = path;
    pathContainer.length = getPathLength(path);
    return pathContainer;
  }

  private Path parsePath(String data, int originalHeight, int originalWidth, int viewHeight,
      int viewWidth) throws ParseException {
    SvgPathParser svgPathParser =
        new ConstrainedSvgPathParser.Builder().originalWidth(originalWidth)
            .originalHeight(originalHeight)
            .viewHeight(viewHeight)
            .viewWidth(viewWidth)
            .build();
    return svgPathParser.parsePath(data);
  }

  private float getPathLength(Path path) {
    float pathLength = 0;

    PathMeasure pm = new PathMeasure(path, true);
    while (true) {
      pathLength = Math.max(pathLength, pm.getLength());
      if (!pm.nextContour()) {
        break;
      }
    }
    return pathLength;
  }
}
