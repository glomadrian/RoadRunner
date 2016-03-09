package com.github.glomadrian.pathloading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import com.github.glomadrian.loadingpath.DeterminateLoadingPath;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private DeterminateLoadingPath determinateLoadingPath;
  private SeekBar seekBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    determinateLoadingPath = (DeterminateLoadingPath) findViewById(R.id.determinate);
    seekBar = (SeekBar) findViewById(R.id.seekBar);
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        determinateLoadingPath.setValue(progress);
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
