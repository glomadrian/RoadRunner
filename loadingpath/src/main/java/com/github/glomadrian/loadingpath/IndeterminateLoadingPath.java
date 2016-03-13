package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.factory.PathPainterConfigurationFactory;
import com.github.glomadrian.loadingpath.painter.indeterminate.IndeterminatePainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.factory.IndeterminatePathLoadingPainterFactory;
import com.github.glomadrian.loadingpath.path.PathContainer;
import com.github.glomadrian.loadingpath.utils.AssertUtils;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public class IndeterminateLoadingPath extends LoadingPath {

  private static final String TAG = "IndeterminateLoading";
  private int originalWidth;
  private int originalHeight;
  private String pathData;
  private PathContainer pathContainer;
  private IndeterminatePathPainter pathPainter;
  private IndeterminatePainter pathIndeterminatePainterSelected = IndeterminatePainter.MATERIAL;
  private PathPainterConfiguration pathPainterConfiguration;
  private boolean animateOnStart = true;

  public IndeterminateLoadingPath(Context context) {
    super(context);
    throw new UnsupportedOperationException("The view can not be created programmatically yet");
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPath(attrs);
    initConfiguration(attrs);
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPath(attrs);
    initConfiguration(attrs);
  }

  @Override
  public void setColor(int color) {
    pathPainter.setColor(color);
  }

  private void initPath(AttributeSet attrs) {
    TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingPath);
    int animationValue = attributes.getInt(R.styleable.LoadingPath_path_animation_type, 0);
    pathIndeterminatePainterSelected = IndeterminatePainter.fromId(animationValue);
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
    pathPainterConfiguration =
        PathPainterConfigurationFactory.makeConfiguration(attributes,
            pathIndeterminatePainterSelected);
    attributes.recycle();
  }

  private void initPathPainter() {
    pathPainter =
        IndeterminatePathLoadingPainterFactory.makeIndeterminatePathPainter(
            pathIndeterminatePainterSelected, pathContainer,
            this, pathPainterConfiguration);
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
      pathPainter.start();
    }
  }

  /**
   * Tell the pathPainter to draw the path in the onDraw
   */
  @Override
  protected void onDraw(Canvas canvas) {
    pathPainter.paintPath(canvas);
  }

  public void start() {
    pathPainter.start();
  }

  public void stop() {
    pathPainter.stop();
  }

  public void restart() {
    pathPainter.restart();
  }
}
