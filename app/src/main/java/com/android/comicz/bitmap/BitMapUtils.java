package com.android.comicz.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.android.comicz.utils.Constants;
import com.android.comicz.utils.Dimension;

public class BitMapUtils {


  //Decodificamos la imagen a la resolución que le pasamos
  public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int screenWidth,
      int screenHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateRatio(options, screenWidth, screenHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    Log.v(Constants.Log.SIZE,
        "+" + options.outWidth + "," + options.outHeight + " - " + options.inSampleSize + resId);
    Bitmap b = BitmapFactory.decodeResource(res, resId, options);
    Log.v(Constants.Log.SIZE,
        "-" + options.outWidth + "," + options.outHeight + " - " + options.inSampleSize + resId);
    return b;
  }

  //Calculamos el radio de reducción de la resolución de una imagen
  public static int calculateRatio(
      BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int ratio = 1;

    if (height > reqHeight || width > reqWidth) {

      // Calculate ratios of height and width to requested height and width
      final int heightRatio = Math.round((float) height / (float) reqHeight);
      final int widthRatio = Math.round((float) width / (float) reqWidth);

      // Choose the smallest ratio as inSampleSize value, this will guarantee
      // a final image with both dimensions larger than or equal to the
      // requested height and width.
      ratio = heightRatio < widthRatio ? heightRatio : widthRatio;
    }

    return ratio;
  }

  //Calculamos el radio de reducción de la resolución de una imagen
  public static int calculateRatioFromRes(Resources res, int resId, int screenWidth,
      int screenHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    final int height = options.outHeight;
    final int width = options.outWidth;
    int ratio = 1;

    if (height > screenHeight || width > screenWidth) {

      // Calculate ratios of height and width to requested height and width
      final int heightRatio = Math.round((float) height / (float) screenHeight);
      final int widthRatio = Math.round((float) width / (float) screenWidth);

      // Choose the smallest ratio as inSampleSize value, this will guarantee
      // a final image with both dimensions larger than or equal to the
      // requested height and width.
      ratio = heightRatio < widthRatio ? heightRatio : widthRatio;
    }

    return ratio;
  }

  //Devuelve la resolución del bitmap en un objeto Dimension
  public static Dimension getResolution(Resources res, int resId) {

    Dimension d;

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    d = new Dimension(options.outWidth, options.outHeight);
    return d;
  }

  public static float calculateScale(Dimension frameResolution,
      Dimension imageResolution) {

    Log.v(Constants.Log.SIZE, "BitMapUtils - calculateScale");

    float imageRatio;
    float frameRatio;
    float constant;

    frameRatio = (float) frameResolution.getWidth() / (float) frameResolution.getHeight();
    Log.v(Constants.Log.SIZE,"BitMapUtils - calculateScale - FrameDimensions = "
        +frameResolution.getWidth()+", "+frameResolution.getHeight());
    Log.v(Constants.Log.SIZE,"BitMapUtils - calculateScale - FrameRatio "+frameRatio);
    imageRatio = (float) imageResolution.getWidth() / (float) imageResolution.getHeight();
    Log.v(Constants.Log.SIZE,"BitMapUtils - calculateScale - ImageDimensions = "
        +imageResolution.getWidth()+", "+imageResolution.getHeight());
    Log.v(Constants.Log.SIZE,"BitMapUtils - calculateScale - ImageRatio "+imageRatio);

    //Comprobamos si nuestra referencia para la constante de relación es el ancho o el alto
    //Ancho
    if (imageRatio > frameRatio) {

      constant = (float) frameResolution.getWidth() / (float) imageResolution.getWidth();
    }
    //Alto
    else {
      constant = (float) frameResolution.getHeight() / (float) imageResolution.getHeight();
    }

    return constant;
  }

}












