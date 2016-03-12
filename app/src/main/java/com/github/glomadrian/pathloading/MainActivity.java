package com.github.glomadrian.pathloading;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.glomadrian.pathloading.ui.dragview.DragViewFragment;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  @Bind(R.id.drawer_layout)
  DrawerLayout drawerLayout;
  @Bind(R.id.content_frame)
  FrameLayout content;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.drag_nav_action)
  public void dragNavActionTouch() {
    showDragView();
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
}
