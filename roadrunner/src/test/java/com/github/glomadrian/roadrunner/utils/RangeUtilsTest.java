package com.github.glomadrian.roadrunner.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Adrián García Lomas
 */
public class RangeUtilsTest {

  @Test
  public void theValue50InTheOldRangeMustBe100OnTheNewRange(){
    int fromMin = 0;
    int fromMax = 100;
    int toMin = 0;
    int toMax = 200;

    int result = RangeUtils.getIntValueInRange(fromMin, fromMax, toMin, toMax, 50);

    Assert.assertTrue(result == 100);
  }

  @Test
  public void theValue50InTheOldRangeMustBe0_5OnTheNewRange(){
    int fromMin = 0;
    int fromMax = 100;
    int toMin = 0;
    int toMax = 1;

    float result = RangeUtils.getFloatValueInRange(fromMin, fromMax, toMin, toMax, 50);

    Assert.assertTrue(result == 0.5f);
  }
}
