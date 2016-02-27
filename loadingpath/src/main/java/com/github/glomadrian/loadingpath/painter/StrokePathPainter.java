package com.github.glomadrian.loadingpath.painter;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.github.glomadrian.loadingpath.PathData;
import com.github.glomadrian.loadingpath.R;

/**
 * @author Adrián García Lomas
 */
public class StrokePathPainter extends FractionPathPainter {

  /**
   * Stroke length from 0 to 1.0
   */
  private float strokeLength = 0.2f;
  private int color;
  private float strokeWidth;
  private ValueAnimator valueAnimator;
  private float fraction;
  private int animationDuration;
  private Paint paint;

  public StrokePathPainter(PathData pathData, View view) {
    super(pathData, view);
    init();
  }

  public StrokePathPainter(PathData pathData, float strokeLength, View view) {
    super(pathData, view);
    this.strokeLength = strokeLength;
    init();
  }

  private void init() {
    initDefaultConfiguration();
    initValueAnimator();
    configureAnimation();
    initPaint();
  }

  private void initDefaultConfiguration() {
    Resources resources = getView().getContext().getResources();
    color = resources.getColor(R.color.loadingpath_default_stroke_color);
    strokeWidth = resources.getDimension(R.dimen.loading_default_stroke_width);
    animationDuration = resources.getInteger(R.integer.loadingpath_default_animation_duration);
  }

  private void initValueAnimator() {
    valueAnimator = ValueAnimator.ofFloat(0, 1);
    valueAnimator.setDuration(animationDuration);
    valueAnimator.addUpdateListener(new AnimationUpdateListener());
  }

  private void configureAnimation() {
    valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
  }

  private void initPaint(){
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColor(color);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(strokeWidth);
  }

  @Override
  public void paintPath(Canvas canvas) {
    drawPathInterval(canvas, fraction - strokeLength, fraction, paint);
  }

  @Override
  public void onViewSizeChange(int w, int h, int oldw, int oldh) {
    valueAnimator.start();
  }

  public float getStrokeLength() {
    return strokeLength;
  }

  public void setStrokeLength(float strokeLength) {
    this.strokeLength = strokeLength;
  }

  private class AnimationUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      fraction = (float) animation.getAnimatedValue();
      requestInvalidate();
    }
  }
}
