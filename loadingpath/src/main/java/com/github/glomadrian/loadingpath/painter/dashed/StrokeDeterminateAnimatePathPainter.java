package com.github.glomadrian.loadingpath.painter.dashed;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.github.glomadrian.loadingpath.painter.FractionPathPainter;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class StrokeDeterminateAnimatePathPainter extends FractionPathPainter {

  private float fractionA = 0f;
  private float fractionB = 1f;
  private Paint paint;
  private ValueAnimator valueAnimator;
  private int animationDuration = 400;
  private int strokeWidth = 20;
  private int strokeColor = Color.RED;

  public StrokeDeterminateAnimatePathPainter(PathContainer pathData, View view) {
    super(pathData, view);
    paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.RED);
    paint.setStrokeWidth(15);
    valueAnimator = ValueAnimator.ofFloat(0f, 1f);
    valueAnimator.setDuration(animationDuration);
    valueAnimator.addUpdateListener(new StrokeDetermianteAnimationUpdateListener());
  }

  @Override
  public void paintPath(Canvas canvas) {
    drawPathInterval(canvas, fractionA, fractionB, paint);
  }

  private class StrokeDetermianteAnimationUpdateListener
      implements ValueAnimator.AnimatorUpdateListener {

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      float fraction = animation.getAnimatedFraction();
      fractionB = fraction;
      view.invalidate();
    }
  }
}
