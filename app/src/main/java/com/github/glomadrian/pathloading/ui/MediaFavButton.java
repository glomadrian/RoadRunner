package com.github.glomadrian.pathloading.ui;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import com.github.glomadrian.pathloading.R;

/**
 * @author Adrián García Lomas
 */
public class MediaFavButton extends FloatingActionButton {

  private boolean isInPlayMode = true;
  private MediaFavButtonListener mediaFavButtonListener;

  public MediaFavButton(Context context) {
    super(context);
    initButton();
  }

  public MediaFavButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    initButton();
  }

  public MediaFavButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initButton();
  }

  private void initButton() {
    setImageDrawable(getContext().getResources().getDrawable(R.drawable.arrows));
    setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        if (isInPlayMode) {
          if (mediaFavButtonListener != null) {
            mediaFavButtonListener.onPlayAction();
          }
          setImageDrawable(getContext().getResources().getDrawable(R.drawable.square));
          isInPlayMode = false;
        } else {
          if (mediaFavButtonListener != null) {
            mediaFavButtonListener.onStopAction();
          }
          setImageDrawable(getContext().getResources().getDrawable(R.drawable.arrows));
          isInPlayMode = true;
        }
      }
    });
  }

  public void setMediaFavButtonListener(
      MediaFavButtonListener mediaFavButtonListener) {
    this.mediaFavButtonListener = mediaFavButtonListener;
  }

  public interface MediaFavButtonListener {

    void onPlayAction();

    void onStopAction();
  }
}
