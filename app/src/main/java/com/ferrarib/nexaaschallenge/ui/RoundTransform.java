package com.ferrarib.nexaaschallenge.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Bruno Ferrari on 24 October 2016.
 */

public class RoundTransform implements com.squareup.picasso.Transformation {

    int x, y, size = 0;

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        this.size = size;
        this.x = x;
        this.y = y;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);

        if (squaredBitmap != source)
            source.recycle();

        Bitmap output = Bitmap.createBitmap(size, size, source.getConfig());
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();

        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float radius = size / 2f;
        canvas.drawCircle(radius, radius, radius, paint);

        squaredBitmap.recycle();
        return output;
    }

    @Override
    public String key() {
        return "rounded(x: " + x + ", y: " + y + "size: " + size + ")";
    }
}
