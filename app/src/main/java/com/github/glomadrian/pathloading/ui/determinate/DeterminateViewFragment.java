package com.github.glomadrian.pathloading.ui.determinate;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.glomadrian.loadingpath.DeterminateRoadRunner;
import com.github.glomadrian.pathloading.R;

/**
 * @author Adrián García Lomas
 */
public class DeterminateViewFragment extends Fragment {

  private static final int DOWNLOAD_TIME = 5000;
  private ValueAnimator progressAnimator;

  @Bind(R.id.determinate)
  DeterminateRoadRunner determinateLoadingPath;
  @Bind(R.id.progress_text)
  TextView progressText;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.determinate_view, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, getActivity());

    progressAnimator = ValueAnimator.ofInt(0, 100).setDuration(DOWNLOAD_TIME);
    progressAnimator.setStartDelay(1200);
    progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        int value = (Integer) animation.getAnimatedValue();
        determinateLoadingPath.setValue(value);
        progressText.setText(value +" %");
      }
    });
  }

  public static DeterminateViewFragment newInstance() {

    Bundle args = new Bundle();

    DeterminateViewFragment fragment = new DeterminateViewFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @OnClick(R.id.download_action)
  public void onDownloadAction() {
    determinateLoadingPath.start();
    progressAnimator.start();
  }
}
