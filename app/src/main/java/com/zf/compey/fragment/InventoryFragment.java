package com.zf.compey.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zf.compey.Adapter.GridKucunAdapter;
import com.zf.compey.Adapter.GridOffceAdapter;
import com.zf.compey.BaseHelp.BaseFragment;
import com.zf.compey.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ( 物资库存)Created by ${Ethan_Zeng} on 2017/11/6.
 */
public class InventoryFragment extends BaseFragment {


    @InjectView(R.id.gridview_kucun)
    GridView gridviewKucun;
    private GridKucunAdapter adapter;
    private List<Map<String, Object>> listdata = new ArrayList<>();

    private String[] namestr = {"库存统计", "入库登记", "领料申请", "请购申请", "退料申请"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_kucun, container, false);

        ButterKnife.inject(this, view);
        getdata();
        return view;

    }

    public void getdata() {
        for (int i = 0; i < 5; i++) {
            Map<String, Object> mapdata = new HashMap<String, Object>();
            mapdata.put("name", namestr[i]);
            listdata.add(mapdata);
        }
        gridviewKucun.setFocusable(false);
        adapter = new GridKucunAdapter(getActivity(), listdata);
        gridviewKucun.setAdapter(adapter);
    }


    public static InventoryFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        InventoryFragment fragment = new InventoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);

    }
}
