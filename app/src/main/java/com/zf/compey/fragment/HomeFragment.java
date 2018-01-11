package com.zf.compey.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zf.compey.Adapter.GridHomeAdapter;
import com.zf.compey.BaseHelp.BaseFragment;
import com.zf.compey.R;
import com.zf.compey.UserInfoBean.ListDataSave;
import com.zf.compey.UserInfoBean.SomeInfoData;
import com.zf.compey.imageloader.GlideImageLoader;
import com.zf.compey.views.BannerLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ( 首页)Created by ${Ethan_Zeng} on 2017/11/6.
 */
public class HomeFragment extends BaseFragment {
    List<Map<String, Object>> list = new ArrayList<>();
    @InjectView(R.id.banner)
    BannerLayout banner;
    private GridHomeAdapter adpter;
    @InjectView(R.id.gridview_home)
    GridView gridviewHome;
    @InjectView(R.id.lin_isshow_fragmenthome)
    LinearLayout linearLayoutfragmenthome;
    private Context context = getActivity();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HTTP_SUCC) {
            }
            if (msg.what==HTTP_LISTVIEW_SUCC){
                linearLayoutfragmenthome.setVisibility(View.VISIBLE);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.inject(this, view);

        if (list.size()>0){

        }else {
            //showload();
        }
        init();



        return view;
    }

    public void init() {
        final List<String> urls = new ArrayList<>();
        urls.add("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");
        urls.add("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
        banner.setImageLoader(new GlideImageLoader());
        banner.setViewUrls(urls);

        //添加监听事件
        banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });


    }



    public void inidata() {
        String name, nums;


        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (i == 0) {
                map.put("name", "新任务");
                map.put("nums", "0");
                map.put("id", i);
            }
            if (i == 1) {
                map.put("name", "待办任务");
                map.put("nums", i);
                map.put("id", i);
            }
            if (i == 2) {
                map.put("name", "逾期任务");
                map.put("nums", i);
                map.put("id", i);
            }
            if (i == 3) {
                map.put("name", "审批");
                map.put("nums", i);
                map.put("id", i);
            }
            if (i == 4) {
                map.put("name", "工作通知");
                map.put("nums", i);
                map.put("id", i);
            }
            if (i == 5) {
                map.put("name", "添加");
                map.put("nums", "0");
                map.put("id", i);
            }
            list.add(map);
        }


        //   list.addAll(list.size()-1,new ListDataSave(getActivity(),"newlist").getDataList("newlist"));
        List<Map<String, Object>> listnew = new ArrayList<>();
        listnew = new ListDataSave(getActivity(), "newlist").getDataList("newlist");
        if (listnew.size() < 5) {
            list.addAll(list.size() - 1, listnew);

        } else {
            list = listnew;
        }
        new ListDataSave(getActivity(), "newlist").setDataList("newlist", list);

        gridviewHome.setFocusable(false);
        adpter = new GridHomeAdapter(getActivity(), list);
        gridviewHome.setAdapter(adpter);
    }


    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);

    }


    @Override
    public void onResume() {
        super.onResume();

        inidata();
        if (SomeInfoData.newlistchanged == true) {

            // list.addAll(list.size()-1,new ListDataSave(getActivity(),"newlist").getDataList("newlist"));
            List<Map<String, Object>> listnew = new ArrayList<>();
            listnew = new ListDataSave(getActivity(), "newlist").getDataList("newlist");
            adpter.setList(listnew);

            SomeInfoData.newlistchanged = false;

        }
    }


}
