package com.github.glomadrian.roadrunner.builder;

import android.content.Context;
import android.graphics.Color;

import com.github.glomadrian.roadrunner.RoadRunner;
import com.github.glomadrian.roadrunner.painter.configuration.Direction;

/**
 * Created by Yahya Bayramoglu on 08/04/16.
 */
public abstract class RoadRunnerBuilder {

    public Context context;

    public int originalWidth;
    public int originalHeight;
    public String pathData;

    public int color = Color.RED;
    public Direction movementDirection = Direction.CLOCKWISE;
    public float strokeWidth = 10;

    public RoadRunnerBuilder(Context context) {
        this.context = context;
    }

    public RoadRunnerBuilder setColor(int color) {
        this.color = color;
        return this;
    }

    public RoadRunnerBuilder setMovementDirection(Direction movementDirection) {
        this.movementDirection = movementDirection;
        return this;
    }

    public RoadRunnerBuilder setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public RoadRunnerBuilder setPath(String path) {
        this.pathData = path;
        return this;
    }

    public RoadRunnerBuilder setOriginalSizes(int width, int height) {
        this.originalWidth = width;
        this.originalHeight = height;
        return this;
    }

    public abstract RoadRunner build();

}