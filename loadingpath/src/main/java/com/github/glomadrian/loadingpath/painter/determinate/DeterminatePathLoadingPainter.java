package com.github.glomadrian.loadingpath.painter.determinate;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.Interpolator;
import com.github.glomadrian.loadingpath.painter.FractionPathPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class DeterminatePathLoadingPainter extends FractionPathPainter {

  private float intervalB = 1f;
  private Paint paint;
  private ValueAnimator valueAnimator;
  private int animationDuration = 400;
  private int strokeColor = Color.RED;
  private int strokeWidth = 10;
  private Interpolator animationInterpolator;

  public DeterminatePathLoadingPainter(PathContainer pathData, View view) {
    super(pathData, view);
    initPaint();
    initValueAnimator();
  }

  private DeterminatePathLoadingPainter(Builder builder) {
    super(builder.pathData, builder.view);
    animationDuration = builder.animationDuration;
    strokeColor = builder.strokeColor;
    strokeWidth = builder.strokeWidth;
    animationInterpolator = builder.animationInterpolator;
  }

  public static Builder newBuilder() {
    return new Builder();
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
    valueAnimator.addUpdateListener(new AnimationUpdateListener());
    if (animationInterpolator != null) {
      valueAnimator.setInterpolator(animationInterpolator);
    }
  }

  @Override
  public void paintPath(Canvas canvas) {
    drawPathInterval(canvas, 0f, intervalB, paint);
  }

  public void updateInterval(float interval) {
    intervalB = interval;
    view.invalidate();
  }

  public void animatedUpdateInterval(float interval) {
    valueAnimator.setFloatValues(intervalB, interval);
    valueAnimator.start();
  }

  public static final class Builder {
    private PathContainer pathData;
    private View view;
    private int animationDuration;
    private int strokeColor;
    private int strokeWidth;
    private Interpolator animationInterpolator;

    private Builder() {
    }

    public Builder withAnimationDuration(int animationDuration) {
      this.animationDuration = animationDuration;
      return this;
    }

    public Builder withStrokeColor(int strokeColor) {
      this.strokeColor = strokeColor;
      return this;
    }

    public Builder withStrokeWidth(int strokeWidth) {
      this.strokeWidth = strokeWidth;
      return this;
    }

    public Builder withAnimationInterpolator(Interpolator animationInterpolator) {
      this.animationInterpolator = animationInterpolator;
      return this;
    }

    public Builder withView(View view) {
      this.view = view;
      return this;
    }

    public Builder withPathContainer(PathContainer pathContainer) {
      this.pathData = pathContainer;
      return this;
    }

    public DeterminatePathLoadingPainter build() {
      return new DeterminatePathLoadingPainter(this);
    }
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
