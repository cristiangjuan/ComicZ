package com.android.comicz.customs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.android.comicz.activities.ScreenSlidePageFragment;

/**
 * Adaptador para el ViewPager
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

  private final int mSize;

  public CustomPagerAdapter(FragmentManager fm, int size) {
    super(fm);
    mSize = size;
  }

  @Override
  public int getCount() {
    return mSize;
  }

  @Override
  public Fragment getItem(int position) {
    return ScreenSlidePageFragment.newInstance(position);
  }


}
