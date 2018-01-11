package com.zf.compey.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zf.compey.BaseHelp.BaseFragment;
import com.zf.compey.R;

import butterknife.ButterKnife;

/**
 * ( 个人中心)Created by ${Ethan_Zeng} on 2017/11/6.
 */
public class MineFragment extends BaseFragment {



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.inject(this, view);
        return view;
    }




    public static MineFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);

        return fragment;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);

    }
}
