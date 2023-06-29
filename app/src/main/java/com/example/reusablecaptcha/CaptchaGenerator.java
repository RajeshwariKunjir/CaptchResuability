package com.example.reusablecaptcha;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class CaptchaGenerator {
    public static Bitmap generateCaptchaImage(String captchaText) {
        // Set the dimensions of the CAPTCHA image
        int width = 200;
        int height = 100;

        // Create a Bitmap object with the specified dimensions and ARGB_8888 configuration
        Bitmap captchaImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // Create a Canvas object to draw on the Bitmap
        Canvas canvas = new Canvas(captchaImage);

        // Set the background color of the CAPTCHA image
        canvas.drawColor(Color.WHITE);

        // Create a Paint object for the text
        Paint textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);

        // Calculate the position to center the text
        Rect bounds = new Rect();
        textPaint.getTextBounds(captchaText, 0, captchaText.length(), bounds);
        int x = (captchaImage.getWidth() - bounds.width()) / 2;
        int y = (captchaImage.getHeight() + bounds.height()) / 2;

        // Draw the text on the CAPTCHA image
        canvas.drawText(captchaText, x, y, textPaint);

        return captchaImage;
    }
}
