package com.github.glomadrian.loadingpath.painter.determinate;

/**
 * @author Adrián García Lomas
 */
public enum DeterminatePainter {
  TWO_WAY(0);

  int id;

  DeterminatePainter(int id) {
    this.id = id;
  }

  public static DeterminatePainter fromId(int id) {
    for (DeterminatePainter f : values()) {
      if (f.id == id) return f;
    }
    throw new IllegalArgumentException();
  }
}
