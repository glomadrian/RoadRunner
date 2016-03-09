package com.github.glomadrian.loadingpath.utils;

/**
 * @author Adrián García Lomas
 */
public class RangeUtils {

  public static int getIntValueInRange(int fromRangeMin, int fromRangeMax, int toRangeMin,
      int toRangeMax, int fromRangeValue) {
    int oldRange = (fromRangeMax - fromRangeMin);
    int newRange = (toRangeMax - toRangeMin);
    return (((fromRangeValue - fromRangeMin) * newRange) / oldRange) + toRangeMin;
  }

  public static float getFloatValueInRange(float fromRangeMin, float fromRangeMax, float toRangeMin,
      float toRangeMax, float fromRangeValue) {
    float oldRange = (fromRangeMax - fromRangeMin);
    float newRange = (toRangeMax - toRangeMin);
    return (((fromRangeValue - fromRangeMin) * newRange) / oldRange) + toRangeMin;
  }
}
