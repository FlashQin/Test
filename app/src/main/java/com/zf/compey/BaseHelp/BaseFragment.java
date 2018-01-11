package com.zf.compey.BaseHelp;

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;


/**
 * ( )Created by ${Ethan_Zeng} on 2017/11/6.
 */

public class BaseFragment extends Fragment {
    protected int PageIndex = 1, PageSize = 10;
    protected int recommond = 0;
    private SharedPreferences sp;

    /*
       * 屏幕的宽度、高度、密度
       */
    protected int mScreenWidth;
    protected int mScreenHeight;
    protected float mDensity;
    /**
     * activity栈管理
     */

    protected AlertDialog progressDialog = null;
    protected boolean isCancel = false;
    protected Context context;
    protected final int SDK_PAY_FLAG = 9;
    /**
     * 网络请求成功标识
     */
    protected final int HTTP_SUCC = 1;
    /**
     * 发送图片
     */
    protected final int SEND_PIC_SUCC = 5;
    /**
     * 网络请求成功标识
     */
    protected final int GET_HTTP_SUCC = 0x4;

    /**
     * listview网络请求成功标识
     */
    protected final int HTTP_LISTVIEW_SUCC = 0x2;
    /**
     * listview网络请求加载更多成功标识
     */
    protected final int HTTP_LISTVIEW_LOAD_MORE_SUCC = 0x3;
    protected Activity mActivity;
    protected View mRootView;

    protected ImmersionBar mImmersionBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        if (isImmersionBarEnabled())
//            initImmersionBar();

    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    //protected boolean isImmersionBarEnabled() {
//        return true;
//    }

    /**
     * 初始化沉浸式
     */
//    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(mActivity);
//        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
//    }
    @Override
    public void onDestroy() {
        super.onDestroy();

//        if (mImmersionBar != null)
//            mImmersionBar.destroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        if (!hidden && mImmersionBar != null)
//            mImmersionBar.init();
    }
}
