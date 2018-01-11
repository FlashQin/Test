package com.zf.compey.Activitys;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.gyf.barlibrary.ImmersionBar;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import com.zf.compey.BaseHelp.BaseActivity;
import com.zf.compey.BaseHelp.BaseAppcalition;
import com.zf.compey.R;
import com.zf.compey.UserInfoBean.SomeInfoData;
import com.zf.compey.fragment.HomeFragment;
import com.zf.compey.fragment.InventoryFragment;
import com.zf.compey.fragment.MineFragment;
import com.zf.compey.fragment.ProduceFragment;
import com.zf.compey.fragment.WorkFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/***
 * ( )Created by ${Ethan_Zeng} on 2017/11/6.
 */

@RuntimePermissions
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @InjectView(R.id.img_scan_erweima_main)
    ImageView imgScanErweimaMain;
    @InjectView(R.id.rel_title_main)
    RelativeLayout relTitleMain;

    @InjectView(R.id.layFrame)
    FrameLayout layFrame;
    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @InjectView(R.id.txt_name_title)
    TextView txtNameTitle;
    private ArrayList<Fragment> fragments;
    MyOkHttp myOKHttp = BaseAppcalition.getInstance().getMyOkHttp();
    private int SCAN_ER_WEI_MA = 5;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == HTTP_SUCC) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        ImmersionBar.with(this).statusBarColor(R.color.blue).init();//zhuangtailan
        myOKHttp = new MyOkHttp();
        setfargmentList();

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int wh = display.getWidth();

        SomeInfoData.with = getwindowwith();
        //int px = Dp2px(MainActivity.this, with);

    }


    /***
     * First Page Fagment set
     */
    public void setfargmentList() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
//        BadgeItem numberBadgeItem = new BadgeItem()//设置底部显示消息数量信息
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.WHITE)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar.setBarBackgroundColor(R.color.bg_color);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "首页").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "生产").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "办公").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "库存").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "我的").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, HomeFragment.newInstance("首页"));
        txtNameTitle.setText("首页");
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {//将实例化Fragment放入数组
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(ProduceFragment.newInstance("Produce"));
        fragments.add(WorkFragment.newInstance("offcie"));
        fragments.add(InventoryFragment.newInstance("inventory"));
        fragments.add(MineFragment.newInstance("Mine"));
        // fragments.add(MineFragment.newInstance(",,,"));
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {//在实现方法里进行切换fragment
        //  点击Item时调用此方法
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //当前的fragment
                Fragment oldfragment = fm.findFragmentById(R.id.layFrame);
                //点击即将跳转的fragment
                Fragment newfragment = fragments.get(position);
                if (newfragment.isAdded()) {
                    // 隐藏当前的fragment，显示下一个
                    ft.hide(oldfragment).show(newfragment);
                } else {
                    // 隐藏当前的fragment，add下一个到Activity中
                    ft.hide(oldfragment).add(R.id.layFrame, newfragment);

                    if (newfragment.isHidden()) {
                        ft.show(newfragment);
                        // Logger.d("被隐藏了");

                    }

                }
                ft.commitAllowingStateLoss();

                //顶部显示对应的文字
                if (position == 0) {
                    txtNameTitle.setText("首页");
                }
                if (position == 1) {
                    txtNameTitle.setText("生产管理");
                }
                if (position == 2) {
                    txtNameTitle.setText("办公");
                }
                if (position == 3) {
                    txtNameTitle.setText("物资库存");
                }
                if (position == 4) {

                    txtNameTitle.setText("个人中心");
                }
            }

        }

    }

    @Override
    public void onTabUnselected(int position) { //  对没有选中的Item进行处理的方法,
        //这儿也要操作隐藏，否则Fragment会重叠
        if (fragments != null) {

            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.hide(fragment);          // 隐藏当前的fragment
                ft.commitAllowingStateLoss();

            }
        }
    }

    @Override
    public void onTabReselected(int position) {
        // 当被选中的Item 再一次被点击时调用此方法
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                showToast("解析结果:" + result);

            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                showToast("解析二维码失败");

            }
        }
    }

    @OnClick(R.id.img_scan_erweima_main)
    public void onViewClicked() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            MainActivityPermissionsDispatcher.quanxianlistWithCheck(MainActivity.this);

        } else {
            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(intent, SCAN_ER_WEI_MA);
        }

    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void quanxianlist() {
//this can be null

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(intent, SCAN_ER_WEI_MA);
        }
    }

    /**
     * 唤出权限时的提示
     *
     * @param request 所要申请的权限
     */
    @OnShowRationale(Manifest.permission.CAMERA)
    void two(final PermissionRequest request) {
        request.proceed();
        //ShowSystemQuanxian("使用此功能需要打开相机权限", request);
    }

    /**
     * 被用户拒绝
     */
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void jujue() {
        showToast("权限未授予，功能无法使用");

    }

    /**
     * 被拒绝并勾选不在提醒授权时，应用需提示用户未获取权限，需用户自己去设置中打开
     */
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void notask() {
        TosetPermission(MainActivity.this,"当前应用缺少相机权限,请去设置界面打开");
       // showToast("当前应用缺少相机权限,请去设置界面打开");

    }


}
