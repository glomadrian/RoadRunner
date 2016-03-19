package com.github.glomadrian.roadrunner.painter.indeterminate;

/**
 * @author Adrián García Lomas
 */
public enum IndeterminatePainter {
  MATERIAL(0), TWO_WAY(1);

  int id;

  IndeterminatePainter(int id) {
    this.id = id;
  }

  public static IndeterminatePainter fromId(int id) {
    for (IndeterminatePainter f : values()) {
      if (f.id == id) return f;
    }
    throw new IllegalArgumentException();
  }
}
