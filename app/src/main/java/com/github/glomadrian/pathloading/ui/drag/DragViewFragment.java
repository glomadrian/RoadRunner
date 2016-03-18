package com.github.glomadrian.pathloading.ui.drag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.github.glomadrian.loadingpath.DeterminateRoadRunner;
import com.github.glomadrian.pathloading.R;
import com.github.glomadrian.pathloading.ui.MediaFavButton;
import com.github.glomadrian.pathloading.utils.RangeUtils;

/**
 * @author Adrián García Lomas
 */
public class DragViewFragment extends Fragment {

  @Bind(R.id.determinate)
  DeterminateRoadRunner determinateLoadingPath;
  @Bind(R.id.drag_view)
  View dragView;
  @Bind(R.id.media_action)
  MediaFavButton mediaFavButton;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.drag_view, container, false);
  }

  @Override public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, getActivity());
    prepareMediaButton();

    view.setOnGenericMotionListener(new View.OnGenericMotionListener() {
      @Override public boolean onGenericMotion(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.i("asd", "onGenericMotion: ");
        return false;
      }
    });
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        Log.i("asd", "onTouch: ");

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
          case MotionEvent.ACTION_MOVE:
            float animationPosition =
                RangeUtils.getFloatValueInRange(0, view.getHeight(), 0f, 1f, y);
            float animationValue = RangeUtils.getFloatValueInRange(0, view.getWidth(), 0, 100, x);
            Log.i("asd", "onTouch: " + animationPosition);
            determinateLoadingPath.setPosition(animationPosition);
            determinateLoadingPath.setValue((int) animationValue);
            return true;
        }
        return true;
      }
    });
  }

  private void prepareMediaButton() {
    mediaFavButton.setMediaFavButtonListener(new MediaFavButton.MediaFavButtonListener() {
      @Override public void onPlayAction() {
        determinateLoadingPath.start();
      }

      @Override public void onStopAction() {
        determinateLoadingPath.stop();
      }
    });
  }

  public static DragViewFragment newInstance() {

    Bundle args = new Bundle();

    DragViewFragment fragment = new DragViewFragment();
    fragment.setArguments(args);
    return fragment;
  }
}
