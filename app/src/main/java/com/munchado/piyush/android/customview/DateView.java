package com.munchado.piyush.android.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by munchado on 3/12/16.
 */
public class DateView extends TextView {
    public String delimiter;
    public String text;
    public int color;
    public boolean fancyText;
    public Drawable drawable;

    public DateView(Context context) {
        super(context);
        setDate();
    }

    public DateView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DateView);
        for (int i = 0; i < typedArray.getIndexCount(); ++i) {
            int attr = typedArray.getIndex(i);
            switch (typedArray.getIndex(i)) {
                case R.styleable.DateView_delimiter:
                    delimiter = typedArray.getString(attr);
                    setDate();
                    break;

                case R.styleable.DateView_fancyText:
                    fancyText = typedArray.getBoolean(attr, false);
                    fancyText();

                case R.styleable.DateView_newTextColor:
                    color = typedArray.getColor(attr, 0x000000);
                    setNewTextColor(color);
                    break;

                case R.styleable.DateView_drawableLeftPadding:
                    drawable = typedArray.getDrawable(attr);
                    setLeftDrawableWithpadding();
            }
        }
        typedArray.recycle();
    }

    public DateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDate();
    }

    private void setNewTextColor(int color) {
        setTextColor(color);
    }

    private void setDate() {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("EEE" + delimiter + " MMM dd" + delimiter + " yyyy ");
        String today = dateFormat.format(Calendar.getInstance().getTime());
        setText(today);
    }

    private void fancyText() {
        if (this.fancyText) {
            setShadowLayer(9, 1, 1, Color.rgb(44, 44, 40));
        }
    }

    private void setLeftDrawableWithpadding() {
        setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        setCompoundDrawablePadding(10);
        setGravity(Gravity.CENTER_VERTICAL);
    }
}
