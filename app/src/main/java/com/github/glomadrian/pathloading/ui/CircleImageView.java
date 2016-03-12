package com.github.glomadrian.pathloading.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author adrian
 */
public class CircleImageView extends ImageView {

  private static final float RADIUS_FACTOR = 8.0f;
  private static final int TRIANGLE_WIDTH = 120;
  private static final int TRIANGLE_HEIGHT = 100;
  private static final int TRIANGLE_OFFSET = 300;
  private Paint paint;

  public CircleImageView(Context context) {
    super(context);
    init();
  }

  public CircleImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    initPaint();
  }

  private void initPaint() {
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(Color.WHITE);
    paint.setStyle(Paint.Style.FILL);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    setImageAlpha(1);
    super.onDraw(canvas);
    Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
    Bitmap scaledBitmap = scaleCenterCrop(bitmap, getHeight(), getWidth());

    BitmapShader shader =
        new BitmapShader(scaledBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

    float radius = Math.min(bitmap.getWidth(), bitmap.getHeight()) / RADIUS_FACTOR;

    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setShader(shader);
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
  }

  public Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
    int sourceWidth = source.getWidth();
    int sourceHeight = source.getHeight();

    // Compute the scaling factors to fit the new height and width, respectively.
    // To cover the final image, the final scaling will be the bigger
    // of these two.
    float xScale = (float) newWidth / sourceWidth;
    float yScale = (float) newHeight / sourceHeight;
    float scale = Math.max(xScale, yScale);

    // Now get the size of the source bitmap when scaled
    float scaledWidth = scale * sourceWidth;
    float scaledHeight = scale * sourceHeight;

    // Let's find out the upper left coordinates if the scaled bitmap
    // should be centered in the new size give by the parameters
    float left = (newWidth - scaledWidth) / 2;
    float top = (newHeight - scaledHeight) / 2;

    // The target rectangle for the new, scaled version of the source bitmap will now
    // be
    RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

    // Finally, we create a new bitmap of the specified size and draw our new,
    // scaled bitmap onto it.
    Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
    Canvas canvas = new Canvas(dest);
    canvas.drawBitmap(source, null, targetRect, null);

    return dest;
  }

  private Path createTriangle() {
    Path triangle = new Path();
    triangle.moveTo(getWidth(), getHeight());
    triangle.lineTo(getWidth(), 0);
    triangle.lineTo(0, getHeight() - (getHeight() / 3));
    triangle.moveTo(0, getHeight() - (getHeight() / 3));
    triangle.lineTo(0, 0);
    triangle.lineTo(getWidth(), 0);

    triangle.close();
    return triangle;
  }
}