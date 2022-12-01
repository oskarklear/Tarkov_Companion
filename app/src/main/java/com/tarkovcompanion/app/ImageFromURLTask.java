package com.tarkovcompanion.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class ImageFromURLTask extends AsyncTask<String, Void, Bitmap> {
    private final ImageView imageView;
    private final Item item;
    private String imageType;

    public ImageFromURLTask(ImageView imageView, Item item) {
        this.imageView = imageView;
        this.item = item;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        imageType = strings[1];
        Bitmap imageData = null;
        try {
            Log.v("Error", "Loading image...");
            InputStream in = new URL(url).openStream();
            imageData = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageData;
    }

    protected void onPostExecute(Bitmap imageData) {
        imageView.setImageBitmap(imageData);
        if (imageType.equals("s")) {
            item.setSmallImageData(imageData);
        } else {
            item.setLargeImageData(imageData);
        }
    }

}
