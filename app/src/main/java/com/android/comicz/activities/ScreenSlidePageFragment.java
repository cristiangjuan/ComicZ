package com.android.comicz.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.comicz.R;
import com.android.comicz.customs.CustomView;
import com.android.comicz.utils.Dimension;


public class ScreenSlidePageFragment extends Fragment {

  public static final String EXTRA_VIGNETTE_NUM = "vigNum";
  private static final String IMAGE_DATA_EXTRA = "resId";
  private int mImageNum;

  private CustomView mImageView;
  private GestureDetector gestureDetector;
  private Dimension screenResolution;


  // Empty constructor, required as per Fragment docs
  public ScreenSlidePageFragment() {
  }

  public static ScreenSlidePageFragment newInstance(int imageNum) {
    Log.v("Method", "newInstance ScreenSlidePageFragment");
    final ScreenSlidePageFragment f = new ScreenSlidePageFragment();
    final Bundle args = new Bundle();
    args.putInt(IMAGE_DATA_EXTRA, imageNum);
    f.setArguments(args);
    return f;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.v("Method", "OnCreate ScreenSlidePageFragment");
    super.onCreate(savedInstanceState);
    mImageNum = getArguments() != null ? getArguments().getInt(IMAGE_DATA_EXTRA) : -1;

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Log.v("Method", "OnCreateView ScreenSlidePageFragment");
    // image_detail_fragment.xml contains just an ImageView
    final View v = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    mImageView = (CustomView) v.findViewById(R.id.slide_imageView);

    return v;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    Log.w("WW", "OnActivityCreated ScreenSlidePageFragment");
    super.onActivityCreated(savedInstanceState);

    if (ScreenSlidePagerActivity.class.isInstance(getActivity())) {

      final int resId = ScreenSlidePagerActivity.pages[mImageNum];

      DisplayMetrics metrics = new DisplayMetrics();
      getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
      Log.w("SZ", "Screen: " + metrics.widthPixels + " x " + metrics.heightPixels);

      //Pasamos a la vista la página en la que se encuentra.
      mImageView.setPage(mImageNum);

      ((ScreenSlidePagerActivity) getActivity())
          .loadBitmap(resId, mImageView, metrics.widthPixels, metrics.heightPixels);
    }

  }

  public CustomView getCustomView() {
    return mImageView;
  }
}
