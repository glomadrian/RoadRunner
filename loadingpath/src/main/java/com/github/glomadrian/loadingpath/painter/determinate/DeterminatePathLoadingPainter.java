package com.github.glomadrian.loadingpath.painter.determinate;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.github.glomadrian.loadingpath.PathContainer;
import com.github.glomadrian.loadingpath.painter.FractionDashPathPainter;

/**
 * @author Adrián García Lomas
 */
public class DeterminatePathLoadingPainter extends FractionDashPathPainter {

  private float intervalA = 0f;
  private float intervalB = 1f;
  private Paint paint;
  private ValueAnimator valueAnimator;
  private int animationDuration = 400;

  public DeterminatePathLoadingPainter(PathContainer pathData, View view) {
    super(pathData, view);
    initPaint();
    initValueAnimator();
  }

  private void initPaint() {
    paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.RED);
    paint.setStrokeWidth(15);
  }

  private void initValueAnimator() {
    valueAnimator = ValueAnimator.ofFloat(0f, 1f);
    valueAnimator.setDuration(animationDuration);
    valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    valueAnimator.addUpdateListener(new AnimationUpdateListener());
  }

  @Override
  public void paintPath(Canvas canvas) {
    drawPathInterval(canvas, intervalA, intervalB, paint);
  }

  public void updateInterval(float interval) {
    intervalB = interval;
    view.invalidate();
  }

  public void animatedUpdateInterval(float interval) {
    valueAnimator.setFloatValues(intervalB, interval);
    valueAnimator.start();
  }

  @Override
  public void onViewSizeChange(int w, int h, int oldw, int oldh) {
    //Empty
  }

  private class AnimationUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      float animatedFraction = (float) animation.getAnimatedValue();
      updateInterval(animatedFraction);
      view.invalidate();
    }
  }
}
