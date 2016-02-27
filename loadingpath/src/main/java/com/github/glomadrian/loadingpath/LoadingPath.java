package com.github.glomadrian.loadingpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import com.github.glomadrian.loadingpath.painter.PathPainter;
import com.github.glomadrian.loadingpath.painter.StrokePathPainter;
import com.github.glomadrian.loadingpath.svg.SvgPathParser;
import java.text.ParseException;

/**
 * @author Adrián García Lomas
 */
public class LoadingPath extends View {

  private Paint paint;
  private PathData pathData;
  private PathPainter pathPainter;

  public LoadingPath(Context context) {
    super(context);
    init();
  }

  public LoadingPath(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public LoadingPath(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    initPainter();
    initPathData();
    initPathPainter();
  }

  private void initPainter() {
    paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.RED);
    paint.setStrokeWidth(10);
  }

  private void initPathData() {
    pathData = new PathData();
    SvgPathParser svgPathParser = new SvgPathParser();
    try {
      pathData.path = svgPathParser.parsePath(Paths.INDOMINUS_REX);
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
    pathPainter = new StrokePathPainter(pathData, this);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    pathPainter.paintPath(canvas);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    pathPainter.onViewSizeChange(w, h, oldw, oldh);
  }
}
