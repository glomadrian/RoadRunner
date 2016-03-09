package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.github.glomadrian.loadingpath.painter.determinate.CircularLinePainter;
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
  private CircularLinePainter circularLinePainter;
  private int min = 0;
  private int max = 100;
  private int value;

  public DeterminateLoadingPath(Context context) {
    super(context);
    throw new UnsupportedOperationException("The view can not be created programmatically yet");
  }

  public DeterminateLoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPath(attrs);
  }

  public DeterminateLoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPath(attrs);
  }

  private void initPath(AttributeSet attrs) {
    TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingPath);
    pathData = attributes.getString(R.styleable.LoadingPath_path_data);
    originalWidth = (int) attributes.getDimension(R.styleable.LoadingPath_path_original_width, 0);
    originalHeight = (int) attributes.getDimension(R.styleable.LoadingPath_path_original_height, 0);

    AssertUtils.assertThis(pathData != null, "Path data must be defined", this.getClass());
    AssertUtils.assertThis(!pathData.isEmpty(), "Path data must be defined", this.getClass());
    AssertUtils.assertThis(!pathData.equals(""), "Path data must be defined", this.getClass());
    AssertUtils.assertThis(originalWidth > 0, "Original with of the path must be defined",
        this.getClass());
    AssertUtils.assertThis(originalHeight > 0, "Original height of the path must be defined",
        this.getClass());
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
    circularLinePainter.start();
  }

  private void initPathPainter() {
    circularLinePainter = new CircularLinePainter(pathContainer, this);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    circularLinePainter.paintPath(canvas);
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
      circularLinePainter.setProgress(progress);
    }
  }
}
