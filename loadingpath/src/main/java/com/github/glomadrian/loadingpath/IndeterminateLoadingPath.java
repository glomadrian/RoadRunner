package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * @author Adrián García Lomas
 */
public class IndeterminateLoadingPath extends LoadingPath {

  public IndeterminateLoadingPath(Context context) {
    super(context);
    init();
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public IndeterminateLoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    //Empty
  }

  @Override
  protected void onDraw(Canvas canvas) {
    //pathPainter.paintPath(canvas);
  }
}
