package com.zf.compey.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.zf.compey.Adapter.GridHomeAdapter;
import com.zf.compey.Adapter.GridProduceAdapter;
import com.zf.compey.BaseHelp.BaseFragment;
import com.zf.compey.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ( 生产)Created by ${Ethan_Zeng} on 2017/11/6.
 */
public class ProduceFragment extends BaseFragment {

    @InjectView(R.id.gridview_produce)
    GridView gridviewProduce;
    private GridProduceAdapter adapter;
    private List<Map<String,Object>> listdata=new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produce, container, false);

        ButterKnife.inject(this, view);
        getdata();
        return view;
    }
    public  void getdata(){
        for (int i = 0; i <5 ; i++) {
            Map<String,Object> mapdata=new HashMap<String, Object>();
            if (i==0){
                mapdata.put("name","今日新任务");
                mapdata.put("nums","2");
            }
            if (i==1){
                mapdata.put("name","待办任务");
                mapdata.put("nums","2");
            }
            if (i==2){
                mapdata.put("name","逾期任务");
                mapdata.put("nums","3");
            }
            if (i==3){
                mapdata.put("name","完成任务统计");
                mapdata.put("nums","6");
            }
            if (i==4){
                mapdata.put("name","实时监控数据");
                mapdata.put("nums","1");
            }
            listdata.add(mapdata);
            gridviewProduce.setFocusable(false);
            adapter=new GridProduceAdapter(getActivity(),listdata);
            gridviewProduce.setAdapter(adapter);
            
        }
    }



    public static ProduceFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        ProduceFragment fragment = new ProduceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);

    }
}
