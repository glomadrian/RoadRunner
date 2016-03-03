package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.github.glomadrian.loadingpath.painter.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.Painter;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfiguration;
import com.github.glomadrian.loadingpath.painter.configuration.PathPainterConfigurationFactory;
import com.github.glomadrian.loadingpath.painter.indeterminate.MaterialPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;
import com.github.glomadrian.loadingpath.path.Paths;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public class IndeterminateLoadingPath extends LoadingPath {

  private static final String TAG = "IndeterminateLoading";
  private int originalWidth = 1024;
  private int originalHeight = 1024;
  private String pathData = Paths.WALLAPOP;
  private PathContainer pathContainer;
  private IndeterminatePathPainter pathPainter;
  private Painter pathPainterSelected = Painter.LOOP;
  private PathPainterConfiguration pathPainterConfiguration;

  public IndeterminateLoadingPath(Context context) {
    super(context);
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
    initConfiguration(attrs);
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initConfiguration(attrs);
  }

  private void initConfiguration(AttributeSet attrs) {
    TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingPath);
    pathPainterConfiguration =
        PathPainterConfigurationFactory.makeConfiguration(attributes, Painter.TWO_WAY);
  }

  private void initPathPainter() {
    pathPainter = new MaterialPainter(pathContainer, this);
    //PathLoadingPainterFactory.makeIndeterminatePathPainter(pathPainterSelected, pathContainer,
    //    this, pathPainterConfiguration);
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
    pathPainter.start();
  }

  /**
   * Tell the pathPainter to draw the path in the onDraw
   */
  @Override
  protected void onDraw(Canvas canvas) {
    pathPainter.paintPath(canvas);
  }
}
