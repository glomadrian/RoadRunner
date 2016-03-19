package com.github.glomadrian.pathloading.ui.home;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.github.glomadrian.roadrunner.DeterminateRoadRunner;
import com.github.glomadrian.pathloading.R;

/**
 * @author Adrián García Lomas
 */
public class HomeViewFragment extends Fragment {

  @Bind(R.id.determinate)
  DeterminateRoadRunner determinateLoadingPath;
  @Bind(R.id.text_image)
  ImageView textImage;
  private ValueAnimator progressAnimator;
  private FinishLoadingListener finishLoadingListener;
  private Animation textAnimation;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.home_view, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, getActivity());
    textAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.text);
    textAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {

      }

      @Override public void onAnimationEnd(Animation animation) {
        if (finishLoadingListener != null) {
          finishLoadingListener.onLoadingFinish();

        }
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
    progressAnimator = ValueAnimator.ofInt(0, 1000).setDuration(4000);
    progressAnimator.setStartDelay(2000);
    progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        int value = (Integer) animation.getAnimatedValue();
        determinateLoadingPath.setValue(value);
      }
    });

    progressAnimator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {
      }

      @Override public void onAnimationEnd(Animator animation) {
        determinateLoadingPath.stop();
        textImage.startAnimation(textAnimation);
        textImage.setVisibility(View.VISIBLE);
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
    progressAnimator.start();
  }

  public void setFinishLoadingListener(
      FinishLoadingListener finishLoadingListener) {
    this.finishLoadingListener = finishLoadingListener;
  }

  public static HomeViewFragment newInstance() {

    Bundle args = new Bundle();

    HomeViewFragment fragment = new HomeViewFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public interface FinishLoadingListener {
    void onLoadingFinish();
  }
}
