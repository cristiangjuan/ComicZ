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

    return !isInZoom() && super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {

    return !isInZoom() && super.onTouchEvent(ev);
  }
}
