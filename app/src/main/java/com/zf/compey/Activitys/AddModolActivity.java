package com.zf.compey.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gyf.barlibrary.ImmersionBar;
import com.zf.compey.BaseHelp.BaseActivity;
import com.zf.compey.R;
import com.zf.compey.UserInfoBean.ListDataSave;
import com.zf.compey.UserInfoBean.SomeInfoData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ( 自定义显示模块)Created by ${Ethan_Zeng} on 2017/11/16.
 */

public class AddModolActivity extends BaseActivity {
    @InjectView(R.id.btn_ok_addmodole)
    Button btnOkAddmodole;
    @InjectView(R.id.btn_oa)
    Button btnOa;
    @InjectView(R.id.btn_pd)
    Button btnPd;
    @InjectView(R.id.lin_isshow_model)
    LinearLayout linshow;
    private List<Map<String, Object>> listdata = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modole);
        ButterKnife.inject(this);

        //  btnOa.getBackground().setAlpha(50);透明度
        ImmersionBar.with(this).statusBarColor(R.color.blue).init();
        getdata();


    }

    public void getdata() {
        listdata = new ListDataSave(AddModolActivity.this, "newlist").getDataList("newlist");
        if (listdata.size() > 0) {
            for (int i = 0; i < listdata.size(); i++) {
                if (listdata.get(i).get("name").equals("OA")) {
                    btnOa.setText("del");
                }
                if (listdata.get(i).get("name").equals("PD")) {
                    btnPd.setText("del");
                }
            }
        }
    }


    @OnClick({R.id.btn_oa, R.id.btn_pd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_oa:
                String dd = btnOa.getText().toString();
                if (btnOa.getText().toString().equals("Add")) {
                    btnOa.setText("del");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", "OA");
                    map.put("nums", "6");
                    map.put("id", "6");
                    // SomeInfoData.map=map;
                    listdata.add(listdata.size() - 1, map);
                } else {
                    btnOa.setText("Add");
                    for (int i = 0; i < listdata.size(); i++) {
                        if (listdata.get(i).get("name").equals("OA")) {
                            listdata.remove(i);
                        }
                    }
                }

                break;
            case R.id.btn_pd:
                String ff = btnPd.getText().toString();
                if (btnPd.getText().toString().equals("Add")) {
                    btnPd.setText("del");
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("name", "PD");
                    map1.put("nums", "7");
                    map1.put("id", "7");
                    //SomeInfoData.map=map;
                    listdata.add(listdata.size() - 1, map1);
                } else {
                    btnPd.setText("Add");
                    for (int i = 0; i < listdata.size(); i++) {
                        if (listdata.get(i).get("name").equals("PD")) {
                            listdata.remove(i);
                        }
                    }

                    break;
                }
        }
    }

    @OnClick(R.id.btn_ok_addmodole)
    public void onViewClicked() {
        List<Map<String, Object>> listold = new ArrayList<>();
        listold = new ListDataSave(AddModolActivity.this, "newlist").getDataList("newlist");
        if ((listold.containsAll(listdata) && listdata.containsAll(listold)) == false) {
            new ListDataSave(AddModolActivity.this, "newlist").clreadata();
            new ListDataSave(AddModolActivity.this, "newlist").setDataList("newlist", listdata);
            SomeInfoData.newlistchanged = true;

        }
        finish();
    }
}
