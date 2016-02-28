package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.github.glomadrian.loadingpath.painter.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.painter.indeterminate.factory.PathLoadingPainterFactory;
import com.github.glomadrian.loadingpath.path.PathContainer;
import com.github.glomadrian.loadingpath.path.Paths;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public class IndeterminateLoadingPath extends LoadingPath {

  private static final String TAG = "IndeterminateLoading";
  private int originalWidth = 512;
  private int originalHeight = 512;
  private String pathData = Paths.GITHUB;
  private PathContainer pathContainer;
  private IndeterminatePathPainter pathPainter;
  private String pathPainterSelected = PathLoadingPainterFactory.LOOP;

  public IndeterminateLoadingPath(Context context) {
    super(context);
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void initPathPainter() {
    pathPainter =
        PathLoadingPainterFactory.makeIndeterminatePathPainter(pathPainterSelected, pathContainer,
            this);
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
