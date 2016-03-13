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
import com.github.glomadrian.pathloading.ui.determinate.DeterminateViewFragment;
import com.github.glomadrian.pathloading.ui.drag.DragViewFragment;
import com.github.glomadrian.pathloading.ui.home.HomeViewFragment;
import com.github.glomadrian.pathloading.ui.material.MaterialViewFragment;
import com.github.glomadrian.pathloading.ui.twoway.TwoWayView;

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
    showHomeView();
  }

  private void initNavigationView() {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
              case R.id.home:
                showHomeView();
                return true;
              case R.id.drag_sample:
                dragSampleMenuTouch();
                return true;
              case R.id.determinate_sample:
                determinateSampleMenuTouch();
                return true;
              case R.id.material_sample:
                materialSampleMenuTouch();
                return true;
              case R.id.two_way_sample:
                twoWaylSampleMenuTouch();
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

  private void determinateSampleMenuTouch() {
    showDeterminateView();
    closeNav();
  }

  private void materialSampleMenuTouch() {
    showMaterialView();
    closeNav();
  }

  private void twoWaylSampleMenuTouch() {
    showTwoWayView();
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

  private void showMaterialView() {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.content_frame, MaterialViewFragment.newInstance())
        .commit();
  }

  private void showTwoWayView() {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.content_frame, TwoWayView.newInstance())
        .commit();
  }

  private void showHomeView() {
    HomeViewFragment homeViewFragment = HomeViewFragment.newInstance();
    homeViewFragment.setFinishLoadingListener(new HomeViewFragment.FinishLoadingListener() {
      @Override public void onLoadingFinish() {
        drawerLayout.openDrawer(Gravity.LEFT);
      }
    });
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.content_frame, homeViewFragment)
        .commit();
  }
}
