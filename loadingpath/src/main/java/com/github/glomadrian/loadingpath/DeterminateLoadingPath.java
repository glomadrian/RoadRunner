package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.github.glomadrian.loadingpath.painter.determinate.DeterminatePathLoadingPainter;
import com.github.glomadrian.loadingpath.painter.determinate.factory.DeterminatePathLoadingFactory;
import com.github.glomadrian.loadingpath.path.PathContainer;
import com.github.glomadrian.loadingpath.path.Paths;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public class DeterminateLoadingPath extends LoadingPath {

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

  /**
   * Force to determinate painter
   */
  private void initPathPainter() {
    pathPainter =
        DeterminatePathLoadingFactory.makeDeterminatePathLoadingPainter(pathContainer, this);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    try {
      pathContainer = buildPathData(w, h, pathData, originalWidth, originalHeight);
      initPathPainter();
    } catch (ParseException e) {
      Log.e(TAG, "Path parse exception: ", e);
    }
  }

  /**
   * Use the path painter to update the path paint
   */
  public void update(int value) {
    float updateValue = getUpdateValue(value);
    pathPainter.updateInterval(updateValue);
  }

  /**
   * Use the path painter to update the path paint with animation
   */
  public void animateUpdate(int value) {
    float updateValue = getUpdateValue(value);
    pathPainter.animatedUpdateInterval(updateValue);
  }

  /**
   * Tell the pathPainter to draw the path in the onDraw
   */
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
