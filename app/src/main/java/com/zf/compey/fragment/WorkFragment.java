package com.zf.compey.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zf.compey.Adapter.GridOffceAdapter;
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
 * ( 办公)Created by ${Ethan_Zeng} on 2017/11/6.
 */
public class WorkFragment extends BaseFragment {


    @InjectView(R.id.gridview_offce)
    GridView gridviewOffce;
    private GridOffceAdapter adapter;
    private List<Map<String, Object>> listdata = new ArrayList<>();

    private String[] namestr = {"项目管理", "月度任务", "订单任务", "临时任务", "审批", "用工统计", "发布通知"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offce, container, false);
        ButterKnife.inject(this, view);
        getdata();
        return view;
    }

    public void getdata() {
        for (int i = 0; i < 7; i++) {
            Map<String, Object> mapdata = new HashMap<String, Object>();
            mapdata.put("name", namestr[i]);
            listdata.add(mapdata);
        }
        adapter = new GridOffceAdapter(getActivity(), listdata);
        gridviewOffce.setFocusable(false);
        gridviewOffce.setAdapter(adapter);
    }


    public static WorkFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        WorkFragment fragment = new WorkFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);

    }
}
