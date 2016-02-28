package com.github.glomadrian.loadingpath.painter.indeterminate;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import com.github.glomadrian.loadingpath.painter.FractionPathPainter;
import com.github.glomadrian.loadingpath.painter.IndeterminatePathPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class LoopPathLoadingPainter extends FractionPathPainter
    implements IndeterminatePathPainter {

  private static final String TAG = "LinePathLoadingPainter";
  private float fractionA = 0f;
  private float fraction = 0f;
  private float lastFraction = 0f;
  private ValueAnimator lineValueAnimator;
  private Paint paint;
  private float correctionIncrement = 0f;
  private int animationDuration = 5000;
  private float visibleLineFraction = 0.3F;
  private int strokeColor = Color.RED;
  private int strokeWidth = 10;
  private Interpolator animationInterpolator;

  private LoopPathLoadingPainter(Builder builder) {
    super(builder.pathContainer, builder.view);
    animationInterpolator = builder.interpolator;
    strokeWidth = builder.strokeWidth;
    strokeColor = builder.strokeColor;
    visibleLineFraction = builder.visibleLineFraction;
    animationDuration = builder.animationDuration;
    init();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public void paintPath(Canvas canvas) {
    float pathCompleteFraction = fractionA + visibleLineFraction;

    if (pathCompleteFraction > 1) {
      correctionIncrement += (fraction - lastFraction);
      Log.i(TAG, "paintPath: " + correctionIncrement);
      drawPathInterval(canvas, 0f, correctionIncrement, paint);
    }

    fractionA = fraction;
    drawPathInterval(canvas, fractionA, visibleLineFraction, paint);
    lastFraction = fraction;
  }

  private void init() {
    initPaint();
    initValueAnimator();
  }

  private void resetValues() {
    correctionIncrement = 0f;
    fraction = 0f;
    lastFraction = 0f;
    fractionA = 0f;
  }

  private void initPaint() {
    paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(strokeColor);
    paint.setStrokeWidth(strokeWidth);
  }

  private void initValueAnimator() {
    lineValueAnimator = ValueAnimator.ofFloat(0f, 1f);
    lineValueAnimator.setDuration(animationDuration);
    lineValueAnimator.addUpdateListener(new LineValueAnimatorUpdateListener());
    lineValueAnimator.addListener(new LineValueAnimatorListener());
    lineValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    lineValueAnimator.setRepeatMode(ValueAnimator.RESTART);
    if (animationInterpolator != null) {
      lineValueAnimator.setInterpolator(animationInterpolator);
    }
  }

  public void start() {
    lineValueAnimator.start();
  }

  @TargetApi(Build.VERSION_CODES.KITKAT)
  @Override
  public void stop() {
    lineValueAnimator.pause();
  }

  @Override
  public void restart() {
    lineValueAnimator.cancel();
    lineValueAnimator.start();
  }

  public static final class Builder {
    private View view;
    private PathContainer pathContainer;
    private Interpolator interpolator;
    private int strokeWidth;
    private int strokeColor;
    private float visibleLineFraction;
    private int animationDuration;

    private Builder() {
    }

    public Builder withView(View val) {
      view = val;
      return this;
    }

    public Builder withPathContainer(PathContainer val) {
      pathContainer = val;
      return this;
    }

    public Builder withAnimationInterpolator(Interpolator val) {
      interpolator = val;
      return this;
    }

    public Builder withStrokeWidth(int val) {
      strokeWidth = val;
      return this;
    }

    public Builder withStrokeColor(int val) {
      strokeColor = val;
      return this;
    }

    public Builder withVisibleLineFraction(float val) {
      visibleLineFraction = val;
      return this;
    }

    public Builder withAnimationDuration(int val) {
      animationDuration = val;
      return this;
    }

    public LoopPathLoadingPainter build() {
      return new LoopPathLoadingPainter(this);
    }
  }

  private class LineValueAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      fraction = animation.getAnimatedFraction();
      view.invalidate();
    }
  }

  private class LineValueAnimatorListener implements ValueAnimator.AnimatorListener {

    @Override
    public void onAnimationStart(Animator animation) {
      resetValues();
    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {
      resetValues();
    }
  }
}
