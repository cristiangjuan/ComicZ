package com.android.comicz.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.android.comicz.R;
import com.android.comicz.customs.CustomView;

public class ZoomFragment extends Fragment {

  private static final String IMAGE_DATA_EXTRA = "resId";
  private int mImageNum;
  private CustomView mImageView;
  private GestureDetector gestureDetector;

  // Empty constructor, required as per Fragment docs
  public ZoomFragment() {
  }

  static ZoomFragment newInstance(int imageNum) {

    Log.w("WW", "newInstance ZoomFragment");

    final ZoomFragment f = new ZoomFragment();
    final Bundle args = new Bundle();
    args.putInt(IMAGE_DATA_EXTRA, imageNum);
    f.setArguments(args);
    return f;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.w("WW", "OnCreate ZoomFragment");
    super.onCreate(savedInstanceState);
    mImageNum = getArguments() != null ? getArguments().getInt(IMAGE_DATA_EXTRA) : -1;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Log.w("WW", "OnCreateView ZoomFragment");
    // image_detail_fragment.xml contains just an ImageView
    final View v = inflater.inflate(R.layout.fragment_zoom_page, container, false);
    mImageView = (CustomView) v.findViewById(R.id.zoom_imageView);
    return v;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    Log.w("WW", "OnActivityCreated ZoomFragment");
    super.onActivityCreated(savedInstanceState);

    if (ZoomActivity.class.isInstance(getActivity())) {

      final int resId = ZoomActivity.imageResIds[mImageNum];

      DisplayMetrics metrics = new DisplayMetrics();
      getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

      ((ZoomActivity) getActivity())
          .loadBitmap(resId, mImageView, metrics.widthPixels, metrics.heightPixels);
    }

    //Definimos el receptor de gestos
    gestureDetector = new GestureDetector(getActivity(), new GestureListener());

    mImageView.setOnTouchListener(new OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
      }
    });

  }

  private class GestureListener extends GestureDetector.SimpleOnGestureListener {


    @Override
    public boolean onDown(MotionEvent e) {
      return true;
    }

    // event when double tap occurs
    @Override
    public boolean onDoubleTap(MotionEvent e) {

      ((ZoomActivity) getActivity()).onBackPressed();

      return true;
    }
  }
}
