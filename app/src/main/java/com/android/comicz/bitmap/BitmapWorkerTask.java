package com.android.comicz.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.android.comicz.customs.CustomView;
import java.lang.ref.WeakReference;

/**
 * Tarea para decodificar una imagen.
 */
public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {

  private final WeakReference<ImageView> imageViewReference;
  private Resources resources;

  public BitmapWorkerTask(CustomView imageView, Resources res) {
    // Use a WeakReference to ensure the ImageView can be garbage collected
    imageViewReference = new WeakReference<ImageView>(imageView);
    resources = res;
  }

  // Decode image in background.
  @Override
  protected Bitmap doInBackground(Integer... params) {

    final Bitmap bitmap = BitMapUtils
        .decodeSampledBitmapFromResource(resources, params[0], params[1], params[2]);
    return bitmap;
  }

  // Once complete, see if ImageView is still around and set bitmap.
  @Override
  protected void onPostExecute(Bitmap bitmap) {
    if (imageViewReference != null && bitmap != null) {
      final ImageView imageView = imageViewReference.get();
      if (imageView != null) {

        //Insertamos el bitmap en el view. Se ajustar� al marco del view por los atributos del layout xml.
        imageView.setImageBitmap(bitmap);

        //Obtenemos la matriz del view que ya tendr� los valores ajustados al marco del view.
        Matrix mx = new Matrix();
        mx = imageView.getImageMatrix();
        imageView.setScaleType(ImageView.ScaleType.MATRIX);

        //Llamamos al m�todo de nuestra custom class.
        ((CustomView) imageView).setInitialMatrix(mx);
      }
    }
  }

}
