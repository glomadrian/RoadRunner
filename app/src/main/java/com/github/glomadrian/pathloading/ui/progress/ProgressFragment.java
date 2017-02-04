package com.github.glomadrian.pathloading.ui.progress;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.github.glomadrian.pathloading.R;
import com.github.glomadrian.roadrunner.ProgressRoadRunner;
import com.github.glomadrian.roadrunner.painter.configuration.Direction;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yahya Bayramoglu on 08/04/16.
 */
public class ProgressFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    @Bind(R.id.seek)
    SeekBar seekBar;

    @Bind(R.id.runnerHolder)
    RelativeLayout runnerHolder;

    ProgressRoadRunner roadRunner;

    private ObjectAnimator infiniteAnimator;

    public static ProgressFragment newInstance() {
        Bundle args = new Bundle();
        ProgressFragment fragment = new ProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_view, container, false);
        ButterKnife.bind(this, view);

        seekBar.setOnSeekBarChangeListener(this);

        roadRunner = (ProgressRoadRunner) new ProgressRoadRunner.Builder(view.getContext())
                .setOriginalSizes(getResources().getInteger(R.integer.twitter_original_width),
                        getResources().getInteger(R.integer.twitter_original_height))
                .setPath(getString(R.string.twitter))
                .setColor(Color.BLACK)
                .setStrokeWidth(10)
                .setMovementDirection(Direction.CLOCKWISE)
                .build();

        runnerHolder.addView(roadRunner, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @OnClick(R.id.runButton)
    public void runButtonClick() {
        if (infiniteAnimator == null) {
            infiniteAnimator = ObjectAnimator.ofInt(roadRunner, ProgressRoadRunner.PROGRESS, 100);
            infiniteAnimator.setDuration(1000);
            infiniteAnimator.setRepeatMode(ObjectAnimator.RESTART);
            infiniteAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            infiniteAnimator.setInterpolator(new FastOutSlowInInterpolator());
        }
        infiniteAnimator.start();
    }

    @OnClick(R.id.stopButton)
    public void stopButtonClick() {
        if (infiniteAnimator != null)
            infiniteAnimator.end();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (roadRunner != null) {
            stopButtonClick(); // Stop the animation first, if it is running
            roadRunner.setProgress(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}