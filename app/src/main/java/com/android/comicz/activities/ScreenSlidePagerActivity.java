package com.android.comicz.activities;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.android.comicz.R;
import com.android.comicz.bitmap.BitMapUtils;
import com.android.comicz.bitmap.BitmapWorkerTask;
import com.android.comicz.customs.CustomPagerAdapter;
import com.android.comicz.customs.CustomView;
import com.android.comicz.customs.CustomViewPager;
import com.android.comicz.utils.Constants;
import com.android.comicz.utils.Page;
import com.android.comicz.utils.Vignette;
import java.util.HashMap;

public class ScreenSlidePagerActivity extends FragmentActivity {


  public final static Integer[] pages = new Integer[]{
      R.drawable.fables_01_01, R.drawable.fables_01_02, R.drawable.fables_01_03,
      R.drawable.fables_01_04, R.drawable.fables_01_05,
      R.drawable.fables_01_06, R.drawable.fables_01_07, R.drawable.fables_01_08,
      R.drawable.fables_01_09, R.drawable.fables_01_10, R.drawable.fables_01_11,
      R.drawable.fables_01_12, R.drawable.fables_01_13, R.drawable.fables_01_14,
      R.drawable.fables_01_15, R.drawable.fables_01_16, R.drawable.fables_01_17,
      R.drawable.fables_01_18, R.drawable.fables_01_19, R.drawable.fables_01_20,
      R.drawable.fables_01_21, R.drawable.fables_01_22
  };
  public static Integer[][] matrixPagVignette = new Integer[][]{
      {},
      {R.drawable.fables_01_02_cut_01, R.drawable.fables_01_02_cut_02,
          R.drawable.fables_01_02_cut_03},
      {R.drawable.fables_01_03_cut_01, R.drawable.fables_01_03_cut_02,
          R.drawable.fables_01_03_cut_03, R.drawable.fables_01_03_cut_04},
      {R.drawable.fables_01_04_cut_01, R.drawable.fables_01_04_cut_02,
          R.drawable.fables_01_04_cut_03, R.drawable.fables_01_04_cut_04,
          R.drawable.fables_01_04_cut_05},
      {R.drawable.fables_01_05_cut_01, R.drawable.fables_01_05_cut_02,
          R.drawable.fables_01_05_cut_03, R.drawable.fables_01_05_cut_04,
          R.drawable.fables_01_05_cut_05, R.drawable.fables_01_05_cut_06},
      {R.drawable.fables_01_06_cut_01, R.drawable.fables_01_06_cut_02,
          R.drawable.fables_01_06_cut_03, R.drawable.fables_01_06_cut_04,
          R.drawable.fables_01_06_cut_05},
      {R.drawable.fables_01_07_cut_01, R.drawable.fables_01_07_cut_02},
      {R.drawable.fables_01_08_cut_01, R.drawable.fables_01_08_cut_02,
          R.drawable.fables_01_08_cut_03, R.drawable.fables_01_08_cut_04},
      {R.drawable.fables_01_09_cut_01, R.drawable.fables_01_09_cut_02,
          R.drawable.fables_01_09_cut_03, R.drawable.fables_01_09_cut_04,
          R.drawable.fables_01_09_cut_05},
      {R.drawable.fables_01_10_cut_01, R.drawable.fables_01_10_cut_02,
          R.drawable.fables_01_10_cut_03, R.drawable.fables_01_10_cut_04,
          R.drawable.fables_01_10_cut_05},
      {R.drawable.fables_01_11_cut_01, R.drawable.fables_01_11_cut_02,
          R.drawable.fables_01_11_cut_03, R.drawable.fables_01_11_cut_04,
          R.drawable.fables_01_11_cut_06, R.drawable.fables_01_11_cut_05},
      {R.drawable.fables_01_12_cut_01, R.drawable.fables_01_12_cut_02,
          R.drawable.fables_01_12_cut_03, R.drawable.fables_01_12_cut_04,
          R.drawable.fables_01_12_cut_05},
      {R.drawable.fables_01_13_cut_01, R.drawable.fables_01_13_cut_02,
          R.drawable.fables_01_13_cut_03, R.drawable.fables_01_13_cut_04,
          R.drawable.fables_01_13_cut_05},
      {R.drawable.fables_01_14_cut_01, R.drawable.fables_01_14_cut_02,
          R.drawable.fables_01_14_cut_03, R.drawable.fables_01_14_cut_04,
          R.drawable.fables_01_14_cut_05},
      {R.drawable.fables_01_15_cut_01, R.drawable.fables_01_15_cut_02,
          R.drawable.fables_01_15_cut_03, R.drawable.fables_01_15_cut_04,
          R.drawable.fables_01_15_cut_05},
      {R.drawable.fables_01_16_cut_01, R.drawable.fables_01_16_cut_02,
          R.drawable.fables_01_16_cut_04, R.drawable.fables_01_16_cut_05,
          R.drawable.fables_01_16_cut_03},
      {R.drawable.fables_01_17},
      {R.drawable.fables_01_18_cut_01, R.drawable.fables_01_18_cut_02,
          R.drawable.fables_01_18_cut_03, R.drawable.fables_01_18_cut_04,
          R.drawable.fables_01_18_cut_05, R.drawable.fables_01_18_cut_06,
          R.drawable.fables_01_18_cut_07},
      {R.drawable.fables_01_19_cut_01, R.drawable.fables_01_19_cut_02,
          R.drawable.fables_01_19_cut_03, R.drawable.fables_01_19_cut_04,
          R.drawable.fables_01_19_cut_05, R.drawable.fables_01_19_cut_06},
      {R.drawable.fables_01_20_cut_01, R.drawable.fables_01_20_cut_02,
          R.drawable.fables_01_20_cut_03, R.drawable.fables_01_20_cut_04,
          R.drawable.fables_01_20_cut_05},
      {R.drawable.fables_01_21_cut_01, R.drawable.fables_01_21_cut_02,
          R.drawable.fables_01_21_cut_03, R.drawable.fables_01_21_cut_04,
          R.drawable.fables_01_21_cut_05},
      {R.drawable.fables_01_22_cut_01, R.drawable.fables_01_22_cut_02,
          R.drawable.fables_01_22_cut_03, R.drawable.fables_01_22_cut_04,
          R.drawable.fables_01_22_cut_05}
  };

  //private LruCache<String, Bitmap> mMemoryCache;
  public static final HashMap<String, Vignette> mapaVignettes = new HashMap<>();
  public static final HashMap<String, Page> mapaPages = new HashMap<>();
  /**
   * The pager widget, which handles animation and allows swiping horizontally to access previous
   * and next wizard steps.
   */
  private CustomViewPager mPager;
  /**
   * The pager adapter, which provides the pages to the view pager widget.
   */
  private CustomPagerAdapter mAdapter;
  /**
   * Vista para gestionar los cambios de visibilidad de las barras de status y nav
   */
  private View mDecorView;
  /**
   * Contexto
   */
  private Context mContext;
  /**
   * Control de gesto para esconder barras de status y navegación.
   */
  private GestureDetector gestureDetector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.v(Constants.Log.METHOD, "ScreenSlidePagerActivity OnCreate");

    super.onCreate(savedInstanceState);

    mContext = this;

    setContentView(R.layout.activity_screen_slide_pager);

    //Inicializamos el detector de gestos
    gestureDetector = new GestureDetector(mContext, new GestureListener());

    mDecorView = getWindow().getDecorView();

    mDecorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
      @Override
      public void onSystemUiVisibilityChange(int visibility) {

        boolean visible = (mDecorView.getSystemUiVisibility()
            & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;

        boolean visibleStatus = (mDecorView.getSystemUiVisibility()
            & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0;

        Log.w(Constants.Log.CONTROLS, "MainActivity - NavigationBar - " + visible);
        Log.w(Constants.Log.CONTROLS, "MainActivity - StatusBar - " + visibleStatus);
      }
    });

    /*
      Cargamos la resolución de las páginas y viñetas, las coordenadas de las viñetas
      y su relación con la página
     */
    loadPages();
    loadVignettes();

    mAdapter = new CustomPagerAdapter(getSupportFragmentManager(), pages.length);
    mPager = (CustomViewPager) findViewById(R.id.slide_pager);
    mPager.setAdapter(mAdapter);
  }

  /**
   * Carga información sobre las páginas, su resolución.
   */
  private void loadPages() {

    Log.v(Constants.Log.METHOD, "ScreenSlidePagerActivity loadPages");
    mapaPages.put("" + R.drawable.fables_01_01, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_01)));
    mapaPages.put("" + R.drawable.fables_01_02, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_02)));
    mapaPages.put("" + R.drawable.fables_01_03, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_03)));
    mapaPages.put("" + R.drawable.fables_01_04, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_04)));
    mapaPages.put("" + R.drawable.fables_01_05, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_05)));

    mapaPages.put("" + R.drawable.fables_01_06, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_06)));
    mapaPages.put("" + R.drawable.fables_01_07, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_07)));
    mapaPages.put("" + R.drawable.fables_01_08, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_08)));
    mapaPages.put("" + R.drawable.fables_01_09, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_09)));
    mapaPages.put("" + R.drawable.fables_01_10, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_10)));

    mapaPages.put("" + R.drawable.fables_01_11, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_11)));
    mapaPages.put("" + R.drawable.fables_01_12, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_12)));
    mapaPages.put("" + R.drawable.fables_01_13, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_13)));
    mapaPages.put("" + R.drawable.fables_01_14, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_14)));
    mapaPages.put("" + R.drawable.fables_01_15, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_15)));

    mapaPages.put("" + R.drawable.fables_01_16, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_16)));
    mapaPages.put("" + R.drawable.fables_01_17, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_17)));
    mapaPages.put("" + R.drawable.fables_01_18, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18)));
    mapaPages.put("" + R.drawable.fables_01_19, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_19)));
    mapaPages.put("" + R.drawable.fables_01_20, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_20)));

    mapaPages.put("" + R.drawable.fables_01_21, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_21)));
    mapaPages.put("" + R.drawable.fables_01_22, new Page(
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_22)));

  }

  /**
   * Carga las coordenadas de las viñetas
   */
  private void loadVignettes() {
    Log.v(Constants.Log.METHOD, "ScreenSlidePagerActivity loadVignettes");

    int vigSeq = 0;
    int vigPag = 1;

    mapaVignettes.put("" + R.drawable.fables_01_02_cut_01, new Vignette(0, 0, 380, 627,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_02_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_02_cut_02, new Vignette(375, 0, 1024, 625,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_02_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_02_cut_03, new Vignette(0, 630, 1024, 1334,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_02_cut_03)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_03_cut_01, new Vignette(0, 0, 1024, 850,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_03_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_03_cut_02, new Vignette(0, 720, 304, 1587,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_03_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_03_cut_03, new Vignette(314, 865, 825, 1587,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_03_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_03_cut_04, new Vignette(840, 850, 1024, 1587,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_03_cut_04)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_04_cut_01, new Vignette(0, 0, 435, 490,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_04_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_04_cut_02, new Vignette(450, 0, 1024, 490,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_04_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_04_cut_03, new Vignette(0, 512, 1024, 1160,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_04_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_04_cut_04, new Vignette(0, 1180, 400, 1588,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_04_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_04_cut_05, new Vignette(425, 1180, 1024, 1588,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_04_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_05_cut_01, new Vignette(0, 0, 355, 390,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_05_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_05_cut_02, new Vignette(379, 0, 1024, 485,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_05_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_05_cut_03, new Vignette(0, 410, 357, 883,
        357, 505, 1024, 883,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_05_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_05_cut_04, new Vignette(0, 900, 530, 1607,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_05_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_05_cut_05, new Vignette(530, 883, 1024, 1257,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_05_cut_05)));
    mapaVignettes.put("" + R.drawable.fables_01_05_cut_06, new Vignette(557, 1277, 1024, 1607,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_05_cut_06)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_06_cut_01, new Vignette(0, 0, 330, 750,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_06_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_06_cut_02, new Vignette(345, 0, 685, 750,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_06_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_06_cut_03, new Vignette(705, 0, 1024, 750,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_06_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_06_cut_04, new Vignette(0, 760, 560, 1594,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_06_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_06_cut_05, new Vignette(570, 760, 1024, 1594,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_06_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_07_cut_01, new Vignette(0, 0, 1000, 1596,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_07_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_07_cut_02, new Vignette(1100, 350, 2048, 1596,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_07_cut_02)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_08_cut_01, new Vignette(0, 0, 1024, 725,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_08_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_08_cut_02, new Vignette(0, 740, 425, 1600,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_08_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_08_cut_03, new Vignette(440, 740, 660, 1600,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_08_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_08_cut_04, new Vignette(675, 740, 1024, 1600,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_08_cut_04)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_09_cut_01, new Vignette(0, 0, 590, 555,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_09_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_09_cut_02, new Vignette(610, 0, 1024, 555,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_09_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_09_cut_03, new Vignette(0, 570, 1024, 905,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_09_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_09_cut_04, new Vignette(0, 925, 566, 1598,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_09_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_09_cut_05, new Vignette(585, 925, 1024, 1598,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_09_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_10_cut_01, new Vignette(70, 0, 545, 500,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_10_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_10_cut_02, new Vignette(0, 0, 1024, 920,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_10_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_10_cut_03, new Vignette(0, 935, 400, 1607,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_10_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_10_cut_04, new Vignette(415, 935, 760, 1607,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_10_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_10_cut_05, new Vignette(780, 935, 1024, 1607,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_10_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_11_cut_01, new Vignette(0, 0, 355, 555,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_11_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_11_cut_02, new Vignette(370, 0, 650, 555,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_11_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_11_cut_03, new Vignette(665, 0, 1024, 555,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_11_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_11_cut_04, new Vignette(0, 570, 1024, 880,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_11_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_11_cut_05, new Vignette(0, 900, 1024, 1618,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_11_cut_05)));
    mapaVignettes.put("" + R.drawable.fables_01_11_cut_06, new Vignette(640, 965, 995, 1520,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_11_cut_06)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_12_cut_01, new Vignette(0, 0, 410, 590,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_12_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_12_cut_02, new Vignette(430, 0, 1024, 590,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_12_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_12_cut_03, new Vignette(0, 605, 532, 1040,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_12_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_12_cut_04, new Vignette(550, 605, 1024, 1095,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_12_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_12_cut_05, new Vignette(0, 1055, 1024, 1619,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_12_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_13_cut_01, new Vignette(0, 0, 1024, 660,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_13_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_13_cut_02, new Vignette(75, 680, 385, 1045,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_13_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_13_cut_03, new Vignette(440, 725, 960, 1130,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_13_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_13_cut_04, new Vignette(0, 1055, 480, 1615,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_13_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_13_cut_05, new Vignette(475, 1130, 1024, 1615,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_13_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_14_cut_01, new Vignette(115, 25, 510, 585,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_14_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_14_cut_02, new Vignette(0, 0, 1024, 930,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_14_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_14_cut_03, new Vignette(0, 945, 260, 1603,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_14_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_14_cut_04, new Vignette(275, 945, 550, 1603,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_14_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_14_cut_05, new Vignette(570, 945, 1024, 1603,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_14_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_15_cut_01, new Vignette(0, 0, 455, 525,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_15_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_15_cut_02, new Vignette(475, 0, 1024, 525,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_15_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_15_cut_03, new Vignette(0, 540, 580, 1604,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_15_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_15_cut_04, new Vignette(590, 540, 1024, 1020,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_15_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_15_cut_05, new Vignette(590, 1040, 1024, 1604,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_15_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_16_cut_01, new Vignette(0, 0, 470, 495,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_16_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_16_cut_02, new Vignette(490, 0, 1024, 490,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_16_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_16_cut_03, new Vignette(0, 510, 1024, 1612,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_16_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_16_cut_04, new Vignette(25, 1050, 525, 1575,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_16_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_16_cut_05, new Vignette(570, 1050, 1000, 1575,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_16_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_17, new Vignette(0, 0, 1024, 1603,
        vigPag, vigSeq++, BitMapUtils.getResolution(getResources(), R.drawable.fables_01_17)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_18_cut_01, new Vignette(0, 0, 560, 450,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_18_cut_02, new Vignette(575, 0, 765, 450,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_18_cut_03, new Vignette(780, 0, 960, 450,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_18_cut_04, new Vignette(0, 470, 360, 1090,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_18_cut_05, new Vignette(375, 470, 1024, 1090,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18_cut_05)));
    mapaVignettes.put("" + R.drawable.fables_01_18_cut_06, new Vignette(0, 1105, 475, 1592,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18_cut_06)));
    mapaVignettes.put("" + R.drawable.fables_01_18_cut_07, new Vignette(495, 1105, 960, 1500,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_18_cut_07)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_19_cut_01, new Vignette(0, 0, 605, 525,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_19_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_19_cut_02, new Vignette(620, 50, 1024, 525,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_19_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_19_cut_03, new Vignette(40, 540, 520, 1040,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_19_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_19_cut_04, new Vignette(540, 540, 1024, 1040,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_19_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_19_cut_05, new Vignette(40, 1060, 485, 1560,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_19_cut_05)));
    mapaVignettes.put("" + R.drawable.fables_01_19_cut_06, new Vignette(505, 1060, 1024, 1608,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_19_cut_06)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_20_cut_01, new Vignette(0, 0, 1024, 565,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_20_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_20_cut_02, new Vignette(40, 580, 400, 1165,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_20_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_20_cut_03, new Vignette(415, 580, 1024, 1165,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_20_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_20_cut_04, new Vignette(40, 735, 430, 1500,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_20_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_20_cut_05, new Vignette(450, 735, 1024, 1614,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_20_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_21_cut_01, new Vignette(0, 0, 450, 525,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_21_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_21_cut_02, new Vignette(465, 0, 1024, 525,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_21_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_21_cut_03, new Vignette(0, 545, 515, 1055,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_21_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_21_cut_04, new Vignette(535, 545, 1024, 1055,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_21_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_21_cut_05, new Vignette(0, 1070, 1024, 1589,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_21_cut_05)));
    vigPag++;

    mapaVignettes.put("" + R.drawable.fables_01_22_cut_01, new Vignette(0, 0, 425, 530,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_22_cut_01)));
    mapaVignettes.put("" + R.drawable.fables_01_22_cut_02, new Vignette(445, 0, 1024, 530,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_22_cut_02)));
    mapaVignettes.put("" + R.drawable.fables_01_22_cut_03, new Vignette(30, 550, 485, 1180,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_22_cut_03)));
    mapaVignettes.put("" + R.drawable.fables_01_22_cut_04, new Vignette(505, 550, 1024, 1180,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_22_cut_04)));
    mapaVignettes.put("" + R.drawable.fables_01_22_cut_05, new Vignette(0, 1200, 1024, 1588,
        vigPag, vigSeq++,
        BitMapUtils.getResolution(getResources(), R.drawable.fables_01_22_cut_05)));
    vigPag++;

  }

  /*
    Añade una imagen a la memoria cache
    @param key
   * @param bitmap
   */
  /*
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
        	Log.i("II", "Added to cache: "+ key);
            mMemoryCache.put(key, bitmap);
        }
    }
*/

  /**
   * Recupera una imagen de la memoria cache
   */
	/*
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
*/
  @Override
  public void onBackPressed() {

    if (mPager.getCurrentItem() == 0) {
      // If the user is currently looking at the first step, allow the system to handle the
      // Back button. This calls finish() on this activity and pops the back stack.
      super.onBackPressed();
    } else {
      // Otherwise, select the previous step.
      mPager.setCurrentItem(0);
    }

  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

    Log.v(Constants.Log.METHOD, "ScreenSlidePagerActivity onActivityResult ");
    Vignette v;

    v = mapaVignettes.get(String.valueOf(resultCode));
    mPager.setCurrentItem(v.getPag());

  }

  /**
   * Carga una imagen en la página. Primero la busca en caché.
   */
  public void loadBitmap(int resId, CustomView imageView, int screenWidth, int screenHeight) {

    Log.v(Constants.Log.METHOD, "ScreenSlidePagerActivity loadBitMap ");
    	/*
	 	final String imageKey = String.valueOf(resId);

	    final Bitmap bitmap = getBitmapFromMemCache(imageKey);
	    if (bitmap != null) {
	    	Log.i("II", "Caché!!");
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

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    gestureDetector.onTouchEvent(event);

    return super.onTouchEvent(event);
  }

  /**
   * Controlamos con el singletap esconder las barras de status y nav
   *
   * @author quayo
   */
  private class GestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

      boolean visible = (mDecorView.getSystemUiVisibility()
          & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;

      if (visible) {

        hideSystemUI();
      }

      return super.onSingleTapUp(e);
    }
  }

  // This snippet hides the system bars.
  private void hideSystemUI() {

    Log.w(Constants.Log.METHOD, "ScreenSlidePagerActivity - hideSystemUI ");

    // Set the IMMERSIVE flag.
    // Set the content to appear under the system bars so that the content
    // doesn't resize when the system bars hide and show.
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
            | View.SYSTEM_UI_FLAG_IMMERSIVE);
  }

  /**
   * Escondemos las barras de status y nav al coger foco
   */
  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);

    if (hasFocus) {

      boolean visible = (mDecorView.getSystemUiVisibility()
          & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;

      if (visible) {

        hideSystemUI();
      }
    }
  }
}
