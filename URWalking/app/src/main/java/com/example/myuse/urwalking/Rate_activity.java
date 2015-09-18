package com.example.myuse.urwalking;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/*
*   Activity in der Bilder bewertet werden können
 */
public class Rate_activity extends AppCompatActivity {
  // int[] images = new int[]{R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four};
    ImageView current;
    HashMap<Integer,Bitmap> pictures = new HashMap<Integer, Bitmap>();
    private int currentPictureKey = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_activity);

        fillPictureArray();

        current = (ImageView)findViewById(R.id.currentImage);
        current.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.three, 100, 100));

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /*
    *   Zukünftiger Start um Bilder aus dem Internet zu ziehen
    */
    private void fillPictureArray() {
    }


    /*
    *   Zukünftiger Start um nächstes Bild auszuwählen
    */
    private void nextpicture(){
        currentPictureKey++;
        current.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.four, 100, 100));
        RateAlgorithm();
    }

    private void RateAlgorithm(){
        String counterString = getResources().getString (R.string.counter);
        int Count = 0;
        try {
            Count = Integer.parseInt(counterString);
        }
        catch(Exception e){System.out.println("Could not parse ");}
        try {
            Count += 20;
            String name = "score";
            String message = Count+"";
            FileOutputStream fos = openFileOutput(name,MODE_PRIVATE);
            fos.write(message.getBytes());
            fos.close();
            TextView counter = ((TextView) findViewById(R.id.header));
            counter.setText(message);
        }
        catch(Exception e){System.out.println("Could not save ");}


    }

    public void nicePhoto(View v) {
       nextpicture();
    }
    public void badPhoto(View v) {
       nextpicture();
    }

}
