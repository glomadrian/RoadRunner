package com.github.glomadrian.pathloading;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.github.glomadrian.pathloading.ui.determinateview.DeterminateViewFragment;
import com.github.glomadrian.pathloading.ui.dragview.DragViewFragment;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  @Bind(R.id.drawer_layout)
  DrawerLayout drawerLayout;
  @Bind(R.id.content_frame)
  FrameLayout content;
  @Bind(R.id.nav)
  NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initNavigationView();
  }

  private void initNavigationView() {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
              case R.id.drag_sample:
                dragSampleMenuTouch();
                return true;
              case R.id.determinate_sample:
                dragDeterminateSampleMenuTouch();
                return true;
            }
            return false;
          }
        });
  }

  private void dragSampleMenuTouch() {
    showDragView();
    closeNav();
  }

  private void dragDeterminateSampleMenuTouch() {
    showDeterminateView();
    closeNav();
  }

  private void closeNav() {
    drawerLayout.closeDrawer(Gravity.LEFT);
  }

  private void showDragView() {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.content_frame, DragViewFragment.newInstance())
        .commit();
  }

  private void showDeterminateView() {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.content_frame, DeterminateViewFragment.newInstance())
        .commit();
  }
}
