package com.munchado.piyush.android.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

/**
 * Created by munchado on 3/12/16.
 */
public class MyEditText extends EditText {
    int min;
    int max;
    int color;
    Drawable drawable;

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadStateFromAttrs(attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadStateFromAttrs(attrs);
    }

    public void setNextTextColor() {
        setTextColor(color);
    }

    private void loadStateFromAttrs(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return; // quick exit
        }
        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(attributeSet, R.styleable.MyEditText);
            min = a.getInt(R.styleable.MyEditText_charMinimumLimit, 0);
            max = a.getInt(R.styleable.MyEditText_charMaximumLimit, Integer.MAX_VALUE);
            color = a.getColor(R.styleable.MyEditText_charTextColor, 0x000000);
            drawable= a.getDrawable(R.styleable.MyEditText_chardrawableLeftPadding);
            setNextTextColor();
            if (getText().toString() != null && getText().toString().length() > max) {
                setText(getText().toString().substring(0, max));
            }
            addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > max) {
                        setText(getText().toString().substring(0, max));
                        setLeftDrawableWithpadding();
                    }
                    else {
                        setEmptyLeftDrawableWithpadding();
                    }
                }
            });
        } finally {
            if (a != null) {
                a.recycle(); // ensure this is always called
            }
        }
    }

    private void setLeftDrawableWithpadding() {
        setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        setCompoundDrawablePadding(10);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private void setEmptyLeftDrawableWithpadding() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
//        setCompoundDrawablePadding(10);
        setGravity(Gravity.CENTER_VERTICAL);
    }

}
