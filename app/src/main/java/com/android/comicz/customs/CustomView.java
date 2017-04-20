package com.android.comicz.customs;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.android.comicz.activities.ScreenSlidePagerActivity;
import com.android.comicz.activities.ZoomActivity;
import com.android.comicz.utils.Constants;
import com.android.comicz.utils.Dimension;
import com.android.comicz.utils.Page;
import com.android.comicz.utils.Vignette;

public class CustomView extends android.support.v7.widget.AppCompatImageView {


  private static final int NONE = 0;
  private static final int DRAG = 1;
  private static final int ZOOM = 2;
  private float newScaleFactor = 1.f;
  private float oldScaleFactor = 1.f;
  private Matrix matrix;
  private Matrix initialMatrix;
  private final PointF midPoint;
  private final PointF startPoint;
  private final PointF currentPan;
  private float oldDist;
  private int mode;
  private boolean inZoom = false;
  /**
   * Número de página correspondiente a la vista.
   */
  private int pageNum = 0;
  /**
   * Gestor del evento doble tap.
   */
  private final GestureDetector gestureDetector;


  public CustomView(Context context) {
    this(context, null, 0);
  }

  public CustomView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CustomView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    //Inicializamos el receptor del gesto doble tap
    gestureDetector = new GestureDetector(this.getContext(), new GestureListener(this));

    midPoint = new PointF();
    startPoint = new PointF();
    currentPan = new PointF(0, 0);
    mode = NONE;
  }

  public void setPage(int p) {
    pageNum = p;
  }

  /**
   * Asignamos la matriz al view, guardando sus valores para cuando haya que resetear
   * a su posición inicial.
   */
  public void setInitialMatrix(Matrix mx) {

    initialMatrix = new Matrix(mx);
    matrix = mx;
    oldDist = 1.f;
    oldScaleFactor = 1.f;
    newScaleFactor = 1.f;

    this.setImageMatrix(matrix);
  }

  /**
   * Reseteamos la imagen a su escala y colocacion inicial, volviendo a la
   * matriz inicial.
   */
  public void resetMatrix() {

    currentPan.set(0, 0);
    inZoom = false;
    this.setInitialMatrix(initialMatrix);

    invalidate();
  }
    
	/*
    public ScaleGestureDetector getScaleDetector() {
		return mScaleDetector;
	}
	*/

  public boolean isInZoom() {

    return inZoom;
  }


  @Override
  public boolean onTouchEvent(MotionEvent event) {

    myOnTouch(event);
    gestureDetector.onTouchEvent(event);

    return true;
  }

  /**
   * Recoge cualquier gesto excepto el doble tap
   */
  private boolean myOnTouch(MotionEvent event) {

    // Handle touch events here...
    switch (event.getAction() & MotionEvent.ACTION_MASK) {

      case MotionEvent.ACTION_DOWN:

        mode = DRAG;
        Log.d("Mode", "mode=DRAG");
        startPoint.set(event.getX(), event.getY());

        break;

      case MotionEvent.ACTION_POINTER_DOWN:

        //Calculamos el punto intermedio en el Pinchzoom
        midPoint(midPoint, event);
        oldDist = spacing(event);

        if (oldDist > 10f) {

          mode = ZOOM;
          Log.d("Mode", "mode=ZOOM");
        }
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_POINTER_UP:
        mode = NONE;
        Log.d("Mode", "mode=NONE");
        break;

      case MotionEvent.ACTION_MOVE:

        if (mode == DRAG) {

          currentPan.x += event.getX() - startPoint.x;
          currentPan.y += event.getY() - startPoint.y;
          //Log.w("Scale","Draggin' "+currentPan.x+","+currentPan.y);
          startPoint.set(event.getX(), event.getY());

          doPanAndZoom();
        } else if (mode == ZOOM) {

          float newDist = spacing(event);

          if (newDist > 10f) {

            float scale = newDist / oldDist;
            oldDist = newDist;
            oldScaleFactor = newScaleFactor;
            newScaleFactor *= scale;
            Log.w("ScaleZoom",
                "Pure Scale: " + newScaleFactor + "OldDist: " + oldDist + "NewDist: " + newDist);

            // Limitamos coeficiente del zoom.
            newScaleFactor = Math.max(1.f, Math.min(newScaleFactor, 4.0f));

            inZoom = newScaleFactor != 1.f;

            float width = this.getWidth();
            float height = this.getHeight();
            float oldScaledWidth = width * oldScaleFactor;
            float oldScaledHeight = height * oldScaleFactor;
            float newScaledWidth = width * newScaleFactor;
            float newScaledHeight = height * newScaleFactor;

            float reqXPos = (midPoint.x - currentPan.x) / oldScaledWidth;
            float reqYPos = (midPoint.y - currentPan.y) / oldScaledHeight;
            float actualXPos = (midPoint.x - currentPan.x) / newScaledWidth;
            float actualYPos = (midPoint.y - currentPan.y) / newScaledHeight;
            currentPan.x += (actualXPos - reqXPos) * newScaledWidth;
            currentPan.y += (actualYPos - reqYPos) * newScaledHeight;

            doPanAndZoom();
          }
        }

        break;
    }

    invalidate();

    return true;
  }

  /**
   * Limitamos el pan y ajustamos el tamaño de la imagen a la ventana
   */
  private void doPanAndZoom() {

    float maxPanX = this.getWidth() * (newScaleFactor - 1f);
    float maxPanY = this.getHeight() * (newScaleFactor - 1f);

    currentPan.x = Math.max(-maxPanX, Math.min(0, currentPan.x));
    currentPan.y = Math.max(-maxPanY, Math.min(0, currentPan.y));

    Bitmap bm = ((BitmapDrawable) this.getDrawable()).getBitmap();

    float bmWidth = bm.getWidth();
    float bmHeight = bm.getHeight();

    float fitToWindow = Math.min(this.getWidth() / bmWidth, this.getHeight() / bmHeight);
    float xOffset = (this.getWidth() - bmWidth * fitToWindow) * 0.5f * newScaleFactor;
    float yOffset = (this.getHeight() - bmHeight * fitToWindow) * 0.5f * newScaleFactor;

    matrix.reset();
    Log.w("ScaleZoom", "Scale: " + newScaleFactor + " Fit: " + fitToWindow + " Total: "
        + newScaleFactor * fitToWindow);
    Log.w("ScalePan",
        "PanX: " + currentPan.x + " XOffset: " + xOffset + " Total: " + (currentPan.x + xOffset));
    Log.w("ScalePan",
        "PanY: " + currentPan.y + " XOffset: " + yOffset + " Total: " + (currentPan.y + yOffset));
    matrix.postScale(newScaleFactor * fitToWindow, newScaleFactor * fitToWindow);
    matrix.postTranslate(currentPan.x + xOffset, currentPan.y + yOffset);

    this.setImageMatrix(matrix);
  }

  //Calcula el punto intermedio en el Pinchzoom
  private void midPoint(PointF point, MotionEvent event) {

    float x = event.getX(0) + event.getX(1);
    float y = event.getY(0) + event.getY(1);
    point.set(x / 2, y / 2);
  }

  // Determine the space between the first two fingers
  private float spacing(MotionEvent event) {

    float x = event.getX(0) - event.getX(1);
    float y = event.getY(0) - event.getY(1);
    return (float) Math.sqrt(x * x + y * y);
  }

  /**
   * Controla el evento doble tap
   */
  private class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private final CustomView view;
    private final Dimension screenResolution;

    public GestureListener(CustomView v) {
      super();

      view = v;
      DisplayMetrics metrics = new DisplayMetrics();
      ((Activity) view.getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
      Log.w("SZ", "Screen: " + metrics.widthPixels + " x " + metrics.heightPixels);
      screenResolution = new Dimension(metrics.widthPixels, metrics.heightPixels);
    }
    	
    	/*
		@Override
        public boolean onDown(MotionEvent e) {
			
			if (view.getScaleDetector().isInProgress()) 
				return false;
			else return true;
        }
        */


    // event when double tap occurs
    @Override
    public boolean onDoubleTap(MotionEvent e) {

      //Comprobamos si la imagen está aumentada o no
      if (view.isInZoom()) {

        view.resetMatrix();
      } else if (Constants.Options.ZOOM) {
        Page pag;
        Vignette vig;
        float relationScreenImage;

        float x = e.getRawX();
        float y = e.getRawY();
        Log.w("SZ", x + ", " + y);

        //int ratioConst = BitMapUtils.calculateRatioFromRes(getResources(), resId);
        //Calcular resolucion del bitmap y hacer el mapeo entre las coordenadas de la viñeta y las del tap
        if (ScreenSlidePagerActivity.pages == null) {
          Log.w("Err", "Pages array static es null!!");
        }

        if (ScreenSlidePagerActivity.mapaPages == null) {
          Log.w("Err", "MapPages es null!!");
        }
        pag = ScreenSlidePagerActivity.mapaPages
            .get(String.valueOf(ScreenSlidePagerActivity.pages[pageNum]));

        //relationScreenImage = BitMapUtils.calculateRelation(screenResolution, pag.getResolution());

        float imageRatio;
        float screenRatio;

        screenRatio = (float) screenResolution.getWidth() / (float) screenResolution.getHeight();
        Log.w("SZ", "ScreenRatio " + screenRatio);
        imageRatio =
            (float) pag.getResolution().getWidth() / (float) pag.getResolution().getHeight();
        Log.w("SZ", "ImageRatio " + imageRatio);

        /*
          Comprobamos si nuestra referencia para la constante de relacion es el ancho o el alto.
          Desplazamos una de las coordenadas ya que la imagen habitualmente no cuadra con la pantalla
          siempre sobrará algo de ancho o de alto. Entonces consideraremos desplazaramos el valor 0 del
          alto o el ancho a el punto donde comienza el alto o el ancho de la imagen.

         */
        //Ancho
        int newWidthOrHeight;
        if (imageRatio > screenRatio) {
          relationScreenImage =
              (float) screenResolution.getWidth() / (float) pag.getResolution().getWidth();
          newWidthOrHeight = (int) (pag.getResolution().getHeight() * relationScreenImage);
          y -= (screenResolution.getHeight() - newWidthOrHeight) / 2;
          Log.w("SZ", "New Y, " + y);
        }
        //Alto
        else {
          relationScreenImage =
              (float) screenResolution.getHeight() / (float) pag.getResolution().getHeight();
          newWidthOrHeight = (int) (pag.getResolution().getWidth() * relationScreenImage);
          x -= (screenResolution.getWidth() - newWidthOrHeight) / 2;
          Log.w("SZ", "New X, " + x);
        }

        //Recuperar delimitaciones de vi�eta (x,y)
        Intent intent = new Intent(view.getContext(), ZoomActivity.class);

        //Comprobamos que la página actual se encuentra en la matriz que relaciona viñetas con páginas
        if (pageNum < ScreenSlidePagerActivity.matrixPagVignette.length) {
          //Recorrer viñetas asociadas a página
          for (int i = 0; i < ScreenSlidePagerActivity.matrixPagVignette[pageNum].length; i++) {

            vig = ScreenSlidePagerActivity.mapaVignettes
                .get(String.valueOf(ScreenSlidePagerActivity.matrixPagVignette[pageNum][i]));
            if (vig.insideVignnette(x, y, relationScreenImage)) {
              Log.w("II", "Inside Viñeta: " + x + ", " + y);
              intent.putExtra(Constants.EXTRA_VIGNETTE_NUM, vig.getSeq());

              ((Activity) view.getContext())
                  .startActivityForResult(intent, Constants.ZOOM_PAGE_REQUEST);
              break;
            }

          }
        }
      }

      return true;
    }
  }

  /*
   private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {


  @Override public boolean onScale(ScaleGestureDetector detector) {

  oldScaleFactor = newScaleFactor;
  newScaleFactor *= detector.getScaleFactor();
  Log.w("ScaleZoom","Pure Scale: "+newScaleFactor);

  // Limitamos coeficiente del zoom.
  newScaleFactor = Math.max(1.f, Math.min(newScaleFactor, 3.0f));

  if (newScaleFactor == 1.f){
  inZoom=false;
  }
  else {
  inZoom=true;
  }

  return true;
  }
  }
   */
}
