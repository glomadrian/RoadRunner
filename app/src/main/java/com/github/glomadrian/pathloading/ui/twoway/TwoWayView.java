package com.github.glomadrian.pathloading.ui.twoway;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.glomadrian.loadingpath.IndeterminateLoadingPath;
import com.github.glomadrian.pathloading.R;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

/**
 * @author Adrián García Lomas
 */
public class TwoWayView extends Fragment {

  @Bind(R.id.two_way)
  IndeterminateLoadingPath indeterminateLoadingPath;
  @Bind(R.id.color_picker)
  ColorSeekBar colorSeekBar;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.two_way_view, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, getActivity());

    colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
      @Override public void onColorChangeListener(int i, int i1, int color) {
        indeterminateLoadingPath.setColor(color);
      }
    });
  }


  @OnClick(R.id.retry_action)
  public void onRetryAction(){
    indeterminateLoadingPath.restart();
  }

  public static TwoWayView newInstance() {

    Bundle args = new Bundle();

    TwoWayView fragment = new TwoWayView();
    fragment.setArguments(args);
    return fragment;
  }
}
