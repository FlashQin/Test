package com.zf.compey.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.gyf.barlibrary.ImmersionBar;


import com.tsy.sdk.myokhttp.MyOkHttp;
import com.zf.compey.BaseHelp.BaseActivity;
import com.zf.compey.BaseHelp.BaseAppcalition;
import com.zf.compey.BaseHelp.GetNetResult;
import com.zf.compey.BaseHelp.ToJson;
import com.zf.compey.HttpUtils.HttpUtils;
import com.zf.compey.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * (logain )Created by ${Ethan_Zeng} on 2017/11/6.
 */

public class LogainActivity extends BaseActivity {
    @InjectView(R.id.btn_forget_password_logain)
    Button btnForgetPasswordLogain;
    @InjectView(R.id.edt_account_logain)
    EditText edtAccountLogain;
    @InjectView(R.id.edt_password_logain)
    EditText edtPasswordLogain;
    MyOkHttp myOKHttp = BaseAppcalition.getInstance().getMyOkHttp();
    String url="http://192.168.10.251:8080/bgms-app/account/login";
    String urll="http://192.168.10.251:8080/bgms-app/test/test";
    String json;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==HTTP_SUCC){
                String jString = new String(msg.obj.toString());
                showToast(jString);
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map = GetNetResult.GetJosn(jString);

                        List<Map<String, Object>> mapdata = new ArrayList<>();
                        String code = map.get("c").toString();
                        mapdata  = (List<Map<String, Object>>) map.get("d");
                        String mess = map.get("m").toString();
                       // String token=mapdata.get("token").toString();
                        //Map<String, String> mapnull = new HashMap<String, String>();
                        //new HttpUtils().UpJsonString(myOKHttp,urll,handler,HTTP_SUCC,json,token);




                } catch (Exception e) {

                }
            }


        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logain_zf);
        ButterKnife.inject(this);



    }
public  void  logain(){
    Map<String,String> map=new HashMap<>();
    map.put("userName","test");
    map.put("password","123");
    json=ToJson.mapToJson(map);
    new HttpUtils().DoGet(myOKHttp,"http://192.168.10.244:8080/jxy/h/qinqin",handler,HTTP_SUCC);
    //new HttpUtils().UpJsonString(myOKHttp,"http://localhost:8080/jxy/h/sayhello",handler,HTTP_SUCC,json,"");
}


    @OnClick(R.id.btn_forget_password_logain)
    public void onViewClicked() {
     //   logain();
        Intent intent = new Intent(LogainActivity.this, MainActivity.class);
        startActivity(intent);


    }
}
