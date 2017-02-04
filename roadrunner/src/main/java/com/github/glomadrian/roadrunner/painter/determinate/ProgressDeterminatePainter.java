package com.github.glomadrian.roadrunner.painter.determinate;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.github.glomadrian.roadrunner.painter.RoadRunnerPainter;
import com.github.glomadrian.roadrunner.painter.configuration.Direction;
import com.github.glomadrian.roadrunner.painter.configuration.indeterminate.MaterialPainterConfiguration;
import com.github.glomadrian.roadrunner.painter.indeterminate.IndeterminatePathPainter;
import com.github.glomadrian.roadrunner.path.PathContainer;

/**
 * Created by Yahya Bayramoglu on 08/04/16.
 */
public class ProgressDeterminatePainter extends RoadRunnerPainter
        implements DeterminatePathPainter, IndeterminatePathPainter {

    private ValueAnimator movementAnimator;
    private int movementLoopTime = 3000;
    private float movementLineSize = 0.03f;
    private float sideIncrementSize = 0f;

    public ProgressDeterminatePainter(PathContainer pathData, View view,
                                      MaterialPainterConfiguration twoWayDeterminateConfiguration) {
        super(pathData, view);
        initConfiguration(twoWayDeterminateConfiguration);
        init();
    }

    private void initConfiguration(MaterialPainterConfiguration twoWayDeterminateConfiguration) {
        movementDirection = twoWayDeterminateConfiguration.getMovementDirection();
        color = twoWayDeterminateConfiguration.getColor();
        strokeWidth = twoWayDeterminateConfiguration.getStrokeWidth();
    }

    private void init() {
        initPaint();
        initLineMovement();
        initMovementAnimator();
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

    @Override
    public void paintPath(Canvas canvas) {
        if (sideIncrementSize == 0) {
            // Display nothing if progress is 0
            return;
        }

        int pointInRange = getNumberOfPointsInRange(sideIncrementSize);

        int firstPosition = getPositionForZone(zone) - pointInRange - movementLinePoints;
        int absFirstPosition = Math.abs(firstPosition);

        int right = 0, left = 0;
        /**
         * Somehow when progress is about to 1.f then it crashes by IndexOutOfBoundsException
         * and as i debugged it's because multiplication creating an offset which bigger than pointsSize
         *
         * pointsLeft: 9900
         * fixedPoints: 150.0
         * firstPosition: -10050
         * offset: 10050
         *
         * To prevent that, i calculated the max value
         */
        if (absFirstPosition > pointsNumber) {
            if (firstPosition > 0) {
                left = pointInRange + (absFirstPosition - pointsNumber) + 1;
            } else {
                left = pointInRange - (absFirstPosition - pointsNumber) - 1;
            }
        } else {
            left = pointInRange;
        }

        // If direction is not clockWise then swap left and right
        if (movementDirection.equals(Direction.COUNTER_CLOCKWISE)) {
            int temp = left;
            left = right;
            right = temp;
        }

        drawWithOffset(zone, right, left, movementLinePoints, canvas, paint);
    }

    @Override
    public void setProgress(float value) {
        if (value <= 1F && value >= 0) {
            sideIncrementSize = value;
            view.invalidate();
        }
    }

    @Override
    public void start() {
        movementAnimator.start();
    }

    @Override
    public void stop() {
        movementAnimator.end();
    }

    @Override
    public void restart() {
        movementAnimator.end();
        movementAnimator.start();
    }

    private class MovementLineAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            zone = (float) animation.getAnimatedValue();
            view.invalidate();
        }
    }
}
