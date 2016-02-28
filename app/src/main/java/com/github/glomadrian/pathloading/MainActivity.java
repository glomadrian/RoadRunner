package com.github.glomadrian.pathloading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;
import com.github.glomadrian.loadingpath.DeterminateLoadingPath;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private SeekBar seekBar;
  private DeterminateLoadingPath determinateLoadingPath;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    seekBar = (SeekBar) findViewById(R.id.seek);
    determinateLoadingPath = (DeterminateLoadingPath) findViewById(R.id.path);

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i(TAG, "onProgressChanged: " + progress);
        determinateLoadingPath.animateUpdate(progress);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }
}
