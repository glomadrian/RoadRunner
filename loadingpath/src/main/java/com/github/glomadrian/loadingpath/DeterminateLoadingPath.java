package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.github.glomadrian.loadingpath.painter.determinate.DeterminatePathLoadingPainter;
import com.github.glomadrian.loadingpath.svg.ConstrainedSvgPathParser;
import com.github.glomadrian.loadingpath.svg.SvgPathParser;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public class DeterminateLoadingPath extends View {

  private static final String TAG = "DeterminateLoadingPath";
  private int min = 0;
  private int max = 100;
  private int originalWidth = 512;
  private int originalHeight = 512;
  private String pathData = Paths.GITHUB;
  private PathContainer pathContainer;
  private DeterminatePathLoadingPainter pathPainter;

  public DeterminateLoadingPath(Context context) {
    super(context);
  }

  public DeterminateLoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public DeterminateLoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void initPathPainter() {
    pathPainter = new DeterminatePathLoadingPainter(pathContainer, this);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    try {
      pathContainer = buildPathData(w, h);
      initPathPainter();
    } catch (ParseException e) {
      Log.e(TAG, "onSizeChanged: ", e);
    }
  }

  //region path build

  private PathContainer buildPathData(int viewWidth, int viewHeight) throws ParseException {
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

  //endregion

  public void update(int value) {
    float updateValue = getUpdateValue(value);
    pathPainter.updateInterval(updateValue);
  }

  public void animateUpdate(int value) {
    float updateValue = getUpdateValue(value);
    pathPainter.animatedUpdateInterval(updateValue);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    pathPainter.paintPath(canvas);
  }

  /**
   * Given the value to update transform in a range from 0f to 1f
   */
  private float getUpdateValue(int value) {
    float update = (float) value / (float) max;
    return update;
  }
}
