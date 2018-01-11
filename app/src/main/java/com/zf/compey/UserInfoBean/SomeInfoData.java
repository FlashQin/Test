package com.zf.compey.UserInfoBean;

import android.content.Context;

import com.zf.compey.BaseHelp.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ( datahelp)Created by ${Ethan_Zeng} on 2017/11/16.
 */

public class SomeInfoData {
    public    static   Boolean newlistchanged=false;
    public    static   float with;

    /***
     * px to dp
     * @param context
     * @param dp
     * @return
     */
    public static int Dp2px(Context context, float dp) {
        final float sc = context.getResources().getDisplayMetrics().density;
        int dd=(int)(dp * sc + 0.5f);
        return dd;

    }
}
