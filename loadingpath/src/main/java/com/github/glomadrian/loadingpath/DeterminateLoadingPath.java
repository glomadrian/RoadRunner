package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.factory.PathPainterConfigurationFactory;
import com.github.glomadrian.loadingpath.painter.determinate.DeterminatePainter;
import com.github.glomadrian.loadingpath.painter.determinate.DeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.determinate.factory.DeterminatePathLoadingPainterFactory;
import com.github.glomadrian.loadingpath.path.PathContainer;
import com.github.glomadrian.loadingpath.utils.AssertUtils;
import com.github.glomadrian.loadingpath.utils.RangeUtils;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public class DeterminateLoadingPath extends LoadingPath {

  private static final String TAG = "DeterminateLoadingPath";
  private int originalWidth;
  private int originalHeight;
  private String pathData;
  private PathContainer pathContainer;
  private DeterminatePathPainter twoWayDeterminatePainter;
  private int min = 0;
  private int max = 100;
  private int value;
  private PathPainterConfiguration pathPainterConfiguration;
  private boolean animateOnStart = true;

  public DeterminateLoadingPath(Context context) {
    super(context);
    throw new UnsupportedOperationException("The view can not be created programmatically yet");
  }

  public DeterminateLoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPath(attrs);
    initConfiguration(attrs);
  }

  public DeterminateLoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPath(attrs);
    initConfiguration(attrs);
  }

  @Override
  public void setColor(int color) {
    twoWayDeterminatePainter.setColor(color);
  }

  private void initPath(AttributeSet attrs) {
    TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingPath);
    pathData = attributes.getString(R.styleable.LoadingPath_path_data);
    originalWidth = (int) attributes.getDimension(R.styleable.LoadingPath_path_original_width, 0);
    originalHeight = (int) attributes.getDimension(R.styleable.LoadingPath_path_original_height, 0);
    animateOnStart = attributes.getBoolean(R.styleable.LoadingPath_animate_on_start, true);

    AssertUtils.assertThis(pathData != null, "Path data must be defined", this.getClass());
    AssertUtils.assertThis(!pathData.isEmpty(), "Path data must be defined", this.getClass());
    AssertUtils.assertThis(!pathData.equals(""), "Path data must be defined", this.getClass());
    AssertUtils.assertThis(originalWidth > 0, "Original with of the path must be defined",
        this.getClass());
    AssertUtils.assertThis(originalHeight > 0, "Original height of the path must be defined",
        this.getClass());
  }

  private void initConfiguration(AttributeSet attrs) {
    TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingPath);
    min = attributes.getInteger(R.styleable.LoadingPath_min, 0);
    max = attributes.getInteger(R.styleable.LoadingPath_max, 100);
    pathPainterConfiguration =
        PathPainterConfigurationFactory.makeConfiguration(attributes, DeterminatePainter.TWO_WAY);
    attributes.recycle();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    try {
      pathContainer = buildPathData(w, h, pathData, originalWidth, originalHeight);
      initPathPainter();
    } catch (ParseException e) {
      Log.e(TAG, "Path parse exception:", e);
    }
    if (animateOnStart) {
      twoWayDeterminatePainter.start();
    }
  }

  private void initPathPainter() {
    twoWayDeterminatePainter = DeterminatePathLoadingPainterFactory.makeIndeterminatePathPainter(
        DeterminatePainter.TWO_WAY, pathContainer, this, pathPainterConfiguration);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    twoWayDeterminatePainter.paintPath(canvas);
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public void setValue(int value) {
    if (value <= max || value >= min) {
      this.value = value;
      float progress = RangeUtils.getFloatValueInRange(min, max, 0f, 1f, value);
      if (twoWayDeterminatePainter != null) {
        twoWayDeterminatePainter.setProgress(progress);
      }
    }
  }

  public void setPosition(float position) {
    twoWayDeterminatePainter.setPosition(position);
  }

  public void start() {
    twoWayDeterminatePainter.start();
  }

  public void stop() {
    twoWayDeterminatePainter.stop();
  }
}
