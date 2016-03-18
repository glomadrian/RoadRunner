package com.github.glomadrian.pathloading.ui.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.github.glomadrian.pathloading.R;

/**
 * @author Adrián García Lomas
 */
public class MaterialViewFragment extends Fragment {

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.material_view, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, getActivity());
  }

  public static MaterialViewFragment newInstance() {

    Bundle args = new Bundle();

    MaterialViewFragment fragment = new MaterialViewFragment();
    fragment.setArguments(args);
    return fragment;
  }
}
