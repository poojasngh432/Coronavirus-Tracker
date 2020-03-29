package com.example.coronavirustrackerapp.widget;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface newType;
    private int post;

    public CustomTypefaceSpan(String family, Typeface type) {
        super(family);
        newType = type;
    }

    public CustomTypefaceSpan(String family, Typeface type, int post) {
        super(family);
        newType = type;
        this.post = post;
    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf, int post) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
        paint.setColor(Color.BLUE);

        if (post == 1) {
            paint.setTypeface(Typeface.DEFAULT);
            paint.setTextSize(convertDptoPixles(14));
        } else if (post == 2) {
            paint.setColor(Color.BLACK);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(convertDptoPixles(14));
        }
    }


    public static int convertDptoPixles(int dp) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp);
    }


    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeFace(ds, newType, post);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType, post);
    }
}

