package com.android.comicz.activities;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.android.comicz.R;
import com.android.comicz.bitmap.BitmapWorkerTask;
import com.android.comicz.customs.CustomView;

public class ZoomActivity extends FragmentActivity {

  /**
   * Ids de los recursos de las vi�etas
   */
  public final static Integer[] imageResIds = new Integer[]{
      R.drawable.fables_01_02_cut_01, R.drawable.fables_01_02_cut_02,
      R.drawable.fables_01_02_cut_03,
      R.drawable.fables_01_03_cut_01, R.drawable.fables_01_03_cut_02,
      R.drawable.fables_01_03_cut_03, R.drawable.fables_01_03_cut_04,
      R.drawable.fables_01_04_cut_01, R.drawable.fables_01_04_cut_02,
      R.drawable.fables_01_04_cut_03, R.drawable.fables_01_04_cut_04,
      R.drawable.fables_01_04_cut_05,
      R.drawable.fables_01_05_cut_01, R.drawable.fables_01_05_cut_02,
      R.drawable.fables_01_05_cut_03, R.drawable.fables_01_05_cut_04,
      R.drawable.fables_01_05_cut_05, R.drawable.fables_01_05_cut_06,
      R.drawable.fables_01_06_cut_01, R.drawable.fables_01_06_cut_02,
      R.drawable.fables_01_06_cut_03, R.drawable.fables_01_06_cut_04,
      R.drawable.fables_01_06_cut_05,
      R.drawable.fables_01_07_cut_01, R.drawable.fables_01_07_cut_02,
      R.drawable.fables_01_08_cut_01, R.drawable.fables_01_08_cut_02,
      R.drawable.fables_01_08_cut_03, R.drawable.fables_01_08_cut_04,
      R.drawable.fables_01_09_cut_01, R.drawable.fables_01_09_cut_02,
      R.drawable.fables_01_09_cut_03, R.drawable.fables_01_09_cut_04,
      R.drawable.fables_01_09_cut_05,
      R.drawable.fables_01_10_cut_01, R.drawable.fables_01_10_cut_02,
      R.drawable.fables_01_10_cut_03, R.drawable.fables_01_10_cut_04,
      R.drawable.fables_01_10_cut_05,
      R.drawable.fables_01_11_cut_01, R.drawable.fables_01_11_cut_02,
      R.drawable.fables_01_11_cut_03, R.drawable.fables_01_11_cut_04,
      R.drawable.fables_01_11_cut_05, R.drawable.fables_01_11_cut_06,
      R.drawable.fables_01_12_cut_01, R.drawable.fables_01_12_cut_02,
      R.drawable.fables_01_12_cut_03, R.drawable.fables_01_12_cut_04,
      R.drawable.fables_01_12_cut_05,
      R.drawable.fables_01_13_cut_01, R.drawable.fables_01_13_cut_02,
      R.drawable.fables_01_13_cut_03, R.drawable.fables_01_13_cut_04,
      R.drawable.fables_01_13_cut_05,
      R.drawable.fables_01_14_cut_01, R.drawable.fables_01_14_cut_02,
      R.drawable.fables_01_14_cut_03, R.drawable.fables_01_14_cut_04,
      R.drawable.fables_01_14_cut_05,
      R.drawable.fables_01_15_cut_01, R.drawable.fables_01_15_cut_02,
      R.drawable.fables_01_15_cut_03, R.drawable.fables_01_15_cut_04,
      R.drawable.fables_01_15_cut_05,
      R.drawable.fables_01_16_cut_01, R.drawable.fables_01_16_cut_02,
      R.drawable.fables_01_16_cut_03, R.drawable.fables_01_16_cut_04,
      R.drawable.fables_01_16_cut_05,
      R.drawable.fables_01_17,
      R.drawable.fables_01_18_cut_01, R.drawable.fables_01_18_cut_02,
      R.drawable.fables_01_18_cut_03, R.drawable.fables_01_18_cut_04,
      R.drawable.fables_01_18_cut_05, R.drawable.fables_01_18_cut_06,
      R.drawable.fables_01_18_cut_07,
      R.drawable.fables_01_19_cut_01, R.drawable.fables_01_19_cut_02,
      R.drawable.fables_01_19_cut_03, R.drawable.fables_01_19_cut_04,
      R.drawable.fables_01_19_cut_05, R.drawable.fables_01_19_cut_06,
      R.drawable.fables_01_20_cut_01, R.drawable.fables_01_20_cut_02,
      R.drawable.fables_01_20_cut_03, R.drawable.fables_01_20_cut_04,
      R.drawable.fables_01_20_cut_05,
      R.drawable.fables_01_21_cut_01, R.drawable.fables_01_21_cut_02,
      R.drawable.fables_01_21_cut_03, R.drawable.fables_01_21_cut_04,
      R.drawable.fables_01_21_cut_05,
      R.drawable.fables_01_22_cut_01, R.drawable.fables_01_22_cut_02,
      R.drawable.fables_01_22_cut_03, R.drawable.fables_01_22_cut_04, R.drawable.fables_01_22_cut_05
  };
  /**
   * The pager widget, which handles animation and allows swiping horizontally to access previous
   * and next wizard steps.
   */
  private ViewPager mPager;

  /**
   * The pager adapter, which provides the pages to the view pager widget.
   */
  // private ImagePagerAdapter mAdapter;
  /**
   * The pager adapter, which provides the pages to the view pager widget.
   */
  private ImagePagerAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.w("WW", "onCreate ZoomActivity");
    super.onCreate(savedInstanceState);

    //Ocultamos status bar
        /*
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
        	
        	View decorView = getWindow().getDecorView();
        	// Hide the status bar.
        	int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        	decorView.setSystemUiVisibility(uiOptions);
        }
        */
    //Ocultamos ActionBar
    ActionBar actionBar = getActionBar();
    actionBar.hide();

    // Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
        /*
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.i("II", "Max Memory: "+ maxMemory);
        
        final int cacheSize = maxMemory / 4;
        Log.i("II", "Cache size: "+ cacheSize);

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
            	Log.i("II", "Size of Bitmap "+ key+ ": "+ bitmap.getByteCount() / 1024);
                return bitmap.getByteCount() / 1024;
            }
        };
        */

    setContentView(R.layout.activity_zoom_pager);

    mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), imageResIds.length);
    mPager = (ViewPager) findViewById(R.id.zoom_pager);
    mPager.setAdapter(mAdapter);
    mPager.setCurrentItem(getIntent().getIntExtra(ScreenSlidePageFragment.EXTRA_VIGNETTE_NUM, 0));
  }

  @Override
  public void onBackPressed() {

    setResult(imageResIds[mPager.getCurrentItem()]);
    super.onBackPressed();
  }

  /**
   * Carga una imagen en la p�gina. Primero la busca en cach�.
   */
  public void loadBitmap(int resId, CustomView imageView, int screenWidth, int screenHeight) {

    Log.w("WW", "loadBitmap ZoomActivity");
      /*
	 	final String imageKey = String.valueOf(resId);

	    final Bitmap bitmap = getBitmapFromMemCache(imageKey);
	    if (bitmap != null) {
	    	Log.i("II", "Cach�!!");
	    	imageView.setImageBitmap(bitmap);
	    } else {
	    	Log.i("II", "Not found in cache: "+imageKey);
	    	//imageView.setImageResource(R.drawable.image_placeholder);
	        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
	        task.execute(resId);
	    }
	    */
    BitmapWorkerTask task = new BitmapWorkerTask(imageView, getResources());
    task.execute(resId, screenWidth, screenHeight);
  }

  /**
   * Adaptador para el ViewPager
   */

  private class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private final int mSize;

    public ImagePagerAdapter(FragmentManager fm, int size) {
      super(fm);
      mSize = size;
    }

    @Override
    public int getCount() {
      return mSize;
    }

    @Override
    public Fragment getItem(int position) {
      return ZoomFragment.newInstance(position);
    }
  }


}
