package com.android.comicz.customs;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.android.comicz.activities.ScreenSlidePageFragment;

public class CustomViewPager extends ViewPager {

  public CustomViewPager(Context context) {
    super(context);
  }

  public CustomViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  private boolean isInZoom() {

    ScreenSlidePageFragment f = (ScreenSlidePageFragment) (this.getAdapter()
        .instantiateItem(this, getCurrentItem()));
    return f.getCustomView().isInZoom();
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {

    if (!isInZoom()) {
      return super.onInterceptTouchEvent(ev);
    }

    return false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {

    if (!isInZoom()) {
      return super.onTouchEvent(ev);
    }

    return false;
  }
}
