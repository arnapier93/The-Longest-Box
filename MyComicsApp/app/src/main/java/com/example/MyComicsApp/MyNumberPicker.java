package com.example.MyComicsApp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

public class MyNumberPicker extends NumberPicker {

    private float bottomFadingEdgeStrength = 1.0f;
    private float topFadingEdgeStrength = 1.0f;

    public MyNumberPicker(Context context) {
        super(context);
    }

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNumberPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        return bottomFadingEdgeStrength;
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        return topFadingEdgeStrength;
    }

    void setTopFadingEdgeStrength(float strength){
        topFadingEdgeStrength = strength;
    }

    void setBottomFadingEdgeStrength(float strength){
        bottomFadingEdgeStrength = strength;
    }



}
