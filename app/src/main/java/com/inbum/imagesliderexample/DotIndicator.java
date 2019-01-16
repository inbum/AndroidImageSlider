package com.inbum.imagesliderexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DotIndicator extends LinearLayout {

    LinearLayout llDotIndicator;
    Drawable activeDot, nonActiveDot;
    private ImageView[] dots;

    public DotIndicator(Context context) {
        super(context);
        initView();
    }

    public DotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public DotIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        getAttrs(attrs, defStyleAttr);
    }

    private void initView(){
        inflate(getContext(), R.layout.dot_indicator, this);

        llDotIndicator = (LinearLayout) findViewById(R.id.linearlayout_dot_indicator);
        activeDot = ContextCompat.getDrawable(getContext(), R.drawable.active_dot);
        nonActiveDot = ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DotIndicator);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DotIndicator, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray){
        int bg_resID = typedArray.getResourceId(R.styleable.DotIndicator_bg, R.color.colorPrimary);
        llDotIndicator.setBackgroundResource(bg_resID);

        int dotActive_color = typedArray.getColor(R.styleable.DotIndicator_dotActiveColor, Color.GRAY);
        DrawableCompat.setTint( activeDot, dotActive_color );

        int dotNonActive_color = typedArray.getColor(R.styleable.DotIndicator_dotNonActiveColor, Color.BLACK);
        DrawableCompat.setTint( nonActiveDot, dotNonActive_color );

        typedArray.recycle();
    }

    void setViewpager(ViewPager viewPager){

        if ( viewPager == null ) return;

        final int indicatorSize = viewPager.getAdapter().getCount();

        dots = new ImageView[indicatorSize];

        for(int i = 0; i < indicatorSize; i++) {

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(nonActiveDot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            llDotIndicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< indicatorSize; i++){
                    dots[i].setImageDrawable( nonActiveDot );
                }

                dots[position].setImageDrawable( activeDot );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
