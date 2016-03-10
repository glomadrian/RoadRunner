package com.github.glomadrian.loadingpath.painter.indeterminate;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.github.glomadrian.loadingpath.painter.PointPathPainter;
import com.github.glomadrian.loadingpath.painter.configuration.Direction;
import com.github.glomadrian.loadingpath.painter.configuration.indeterminate.MaterialPainterConfiguration;
import com.github.glomadrian.loadingpath.path.PathContainer;

/**
 * @author Adrián García Lomas
 */
public class MaterialPainter extends PointPathPainter implements IndeterminatePathPainter {

  private static final String TAG = "MaterialPainter";
  private ValueAnimator movementAnimator;
  private ValueAnimator frontValueAnimator;
  private ValueAnimator backValueAnimator;
  private Paint paint;
  private int color = Color.WHITE;
  private float strokeWidth = 20;
  private float zone = 0f;
  private int movementLoopTime = 3000;
  private float movementLineSize = 0.07f;
  private int movementLinePoints = 50;
  private Direction movementDirection = Direction.CLOCKWISE;
  //Front
  private int frontOffset = 0;
  private float sideIncrementSize = 0.7f;
  private int sideAnimationTime = 600;
  private int sideStartDelay = 200;
  //Back
  private int backOffset = 0;

  public MaterialPainter(PathContainer pathData, View view,
      MaterialPainterConfiguration pathPainterConfiguration) {
    super(pathData, view);
    initConfiguration(pathPainterConfiguration);
    init();
  }

  private void initConfiguration(MaterialPainterConfiguration pathPainterConfiguration) {
    movementDirection = pathPainterConfiguration.getMovementDirection();
    color = pathPainterConfiguration.getColor();
    strokeWidth = pathPainterConfiguration.getStrokeWidth();
  }

  private void init() {
    initPaint();
    initLineMovement();
    initMovementAnimator();
    initFrontValueAnimator();
    initBackValueAnimator();
  }

  public void initPaint() {
    paint = new Paint();
    paint.setColor(color);
    paint.setStrokeWidth(strokeWidth);
  }

  private void initLineMovement() {
    movementLinePoints = getNumberOfLinePointsInRange(movementLineSize);
  }

  private void initMovementAnimator() {
    if (movementDirection.equals(Direction.CLOCKWISE)) {
      movementAnimator = ValueAnimator.ofFloat(0, 1);
    } else {
      movementAnimator = ValueAnimator.ofFloat(1, 0);
    }
    movementAnimator.setDuration(movementLoopTime);
    movementAnimator.setInterpolator(new LinearInterpolator());
    movementAnimator.setRepeatCount(ValueAnimator.INFINITE);
    movementAnimator.addUpdateListener(new MovementLineAnimatorUpdateListener());
  }

  private void initFrontValueAnimator() {
    int points = getPositionForZone(sideIncrementSize);
    frontValueAnimator = ValueAnimator.ofInt(0, points);
    frontValueAnimator.setDuration(sideAnimationTime);
    frontValueAnimator.setStartDelay(sideStartDelay);
    frontValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    frontValueAnimator.addListener(new FrontOffsetAnimatorListener());
    frontValueAnimator.addUpdateListener(new FrontOffsetAnimatorUpdateListener());
  }

  private void initBackValueAnimator() {
    int points = getPositionForZone(sideIncrementSize);
    backValueAnimator = ValueAnimator.ofInt(0, points);
    backValueAnimator.setDuration(sideAnimationTime);
    backValueAnimator.setStartDelay(sideStartDelay);
    backValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    backValueAnimator.addListener(new BackOffsetAnimatorListener());
    backValueAnimator.addUpdateListener(new BackOffsetAnimatorUpdateListener());
  }

  @Override
  public void paintPath(Canvas canvas) {
    if (movementDirection.equals(Direction.CLOCKWISE)) {
      paintPathRightDirection(canvas);
    } else {
      paintPathLetDirection(canvas);
    }
  }

  private void paintPathLetDirection(Canvas canvas) {
    drawWithOffset(zone, backOffset, frontOffset, movementLinePoints, canvas, paint);
  }

  private void paintPathRightDirection(Canvas canvas) {
    drawWithOffset(zone, frontOffset, backOffset, movementLinePoints, canvas, paint);
  }

  @Override
  public void start() {
    movementAnimator.start();
    frontValueAnimator.start();
  }

  @Override
  public void stop() {
    //Empty
  }

  @Override
  public void restart() {
    //Empty
  }

  private class MovementLineAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      zone = (float) animation.getAnimatedValue();
      view.invalidate();
    }
  }

  private class FrontOffsetAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      frontOffset = (int) animation.getAnimatedValue();
    }
  }

  private class FrontOffsetAnimatorListener implements ValueAnimator.AnimatorListener {

    @Override
    public void onAnimationStart(Animator animation) {
      //Empty
    }

    @Override
    public void onAnimationEnd(Animator animation) {
      if (movementDirection.equals(Direction.CLOCKWISE)) {
        onAnimationEndRight();
      } else {
        onAnimationEndLeft();
      }
      backOffset = frontOffset;
      frontOffset = 0;
      backValueAnimator.reverse();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
      //Empty
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
      //Empty
    }

    private void onAnimationEndRight() {
      zone += sideIncrementSize;
      if (zone < 1) {
        movementAnimator.setCurrentPlayTime((long) (zone * movementLoopTime));
      } else {
        movementAnimator.setCurrentPlayTime((long) ((zone - 1) * movementLoopTime));
      }
    }

    private void onAnimationEndLeft() {
      zone -= sideIncrementSize;
      movementAnimator.setCurrentPlayTime((long) ((1 - zone) * movementLoopTime));
    }
  }

  private class BackOffsetAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
      backOffset = (int) animation.getAnimatedValue();
    }
  }

  private class BackOffsetAnimatorListener implements ValueAnimator.AnimatorListener {

    @Override
    public void onAnimationStart(Animator animation) {
      //Empty
    }

    @Override
    public void onAnimationEnd(Animator animation) {
      backOffset = 0;
      frontOffset = 0;
      frontValueAnimator.start();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
      //Empty
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
      //Empty
    }
  }
}
