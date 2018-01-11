package com.zf.compey.BaseHelp;


import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;



import com.zf.compey.R;

import permissions.dispatcher.PermissionRequest;


/**
 * ( )Created by ${Ethan_Zeng} on 2017/11/6.
 */

public class BaseActivity extends AppCompatActivity {
    protected int PageIndex = 1, PageSize = 10;
    protected int recommond = 0;
    private SharedPreferences sp;
    private Toast mToast;

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
    private TextView leftView;
    private TextView rightView;
    private ImageButton rightImageView;
    private ImageButton leftImageView;
    protected ImmersionBar mImmersionBar;
    private InputMethodManager imm;
    /**
     * 加載的條目數
     */
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        context = this;
        //ButterKnife.inject(this);
        //初始化沉浸式
       // ImmersionBar.with(this).statusBarColor(R.color.blue).init();//zhuangtailan
        if (isImmersionBarEnabled())
            initImmersionBar();

        // setTopTitleColor();
        //  tack.addActivity(this);
        // 默认软键盘不弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // 控制只能竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    protected void setTopTitleColor(int... color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

        }


    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }



    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public float getwindowwith() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        float x = display.getWidth();
        return x;
    }


    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }


    /**
     * Toaset消息
     *
     * @param message
     */
    protected void showToast(final String message) {
        if (!TextUtils.isEmpty(message)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    } else {
                        mToast.setText(message);
                    }
                    mToast.show();
                }
            });

        }
    }

    /**
     * 被拒绝并且不再提醒,提示用户去设置界面重新打开权限
     */
    protected void TosetPermission(Context context, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.show();
    }

    /**
     * 告知用户具体需要权限的原因
     *
     * @param messageResId
     * @param request
     */
    protected void ShowSystemQuanxian(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();//请求权限

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

}
