package com.zf.compey.views;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * ( let the gridview show all)Created by ${Ethan_Zeng} on 2017/11/20.
 */

public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

    }

    public MyGridView(Context context) {
        super(context);

    }


    public MyGridView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //gridview and scroolview should show all height
        int ex = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, ex);
    }
}
