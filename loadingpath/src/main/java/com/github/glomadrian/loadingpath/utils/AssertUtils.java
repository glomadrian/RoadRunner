package com.github.glomadrian.loadingpath.utils;

/**
 * In android the java assert are disabled by default, this utils is a acceptable alternative
 * to the keyword assert
 *
 * @author Adrián García Lomas
 */
public final class AssertUtils {

  private AssertUtils() {
    //No instances allowed
  }

  public static void assertThis(boolean condition, String errorMsg, Class clazz) {
    if (!condition) {
      throw new AssertionError(errorMsg + "in " + clazz.getName());
    }
  }
}