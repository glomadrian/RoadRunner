package com.github.glomadrian.roadrunner.painter.configuration;

/**
 * @author Adrián García Lomas
 */
public enum Direction {
  CLOCKWISE(0), COUNTER_CLOCKWISE(1);

  int id;

  Direction(int id) {
    this.id = id;
  }

  public static Direction fromId(int id) {
    for (Direction f : values()) {
      if (f.id == id) return f;
    }
    throw new IllegalArgumentException();
  }
}
