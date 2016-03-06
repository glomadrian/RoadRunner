package com.github.glomadrian.loadingpath.painter;

/**
 * @author Adrián García Lomas
 */
public enum Painter {
  MATERIAL(0), TWO_WAY(1);

  int id;

  Painter(int id) {
    this.id = id;
  }

  public static Painter fromId(int id) {
    for (Painter f : values()) {
      if (f.id == id) return f;
    }
    throw new IllegalArgumentException();
  }
}
