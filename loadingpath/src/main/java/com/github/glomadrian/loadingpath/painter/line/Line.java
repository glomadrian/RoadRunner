package com.github.glomadrian.loadingpath.painter.line;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.github.glomadrian.loadingpath.painter.configuration.Direction;

public class Line {

  private float zone = 0f;
  private int movementLoopTime = 5000;
  private ValueAnimator movementAnimator;
  private String movementDirection = Direction.LEFT;
  private float movementLineSize = 0.005f;
  private int startDelay = 500;
  private View view;

  public Line(View view) {
    this.view = view;
    initMovementAnimator();
  }

  private void initMovementAnimator() {
    if (movementDirection.equals(Direction.RIGHT)) {
      movementAnimator = ValueAnimator.ofFloat(0, 1);
    } else {
      movementAnimator = ValueAnimator.ofFloat(1, 0);
    }
    movementAnimator.setDuration(movementLoopTime);
    movementAnimator.setInterpolator(new LinearInterpolator());
    movementAnimator.setRepeatCount(ValueAnimator.INFINITE);
    movementAnimator.setStartDelay(startDelay);
    movementAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        zone = (float) animation.getAnimatedValue();
        view.invalidate();
      }
    });
  }

  public void start() {
    movementAnimator.start();
  }

  public float getZone() {
    return zone;
  }

  public float getMovementLineSize() {
    return movementLineSize;
  }

  public void setStartDelay(int startDelay) {
    this.startDelay = startDelay;
  }
}