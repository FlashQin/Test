package com.zf.compey.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zf.compey.Activitys.AddModolActivity;
import com.zf.compey.R;
import com.zf.compey.UserInfoBean.SomeInfoData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ( kucun Adapter)Created by ${Ethan_Zeng} on 2017/11/16.
 */

public class GridKucunAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list = new ArrayList<>();

    public GridKucunAdapter(Context cotent, List<Map<String, Object>> listdata) {
        this.context = cotent;
        this.list = listdata;


    }

    /**
     * 获取列表数据
     *
     * @param listdata
     */
    public void setList(List<Map<String, Object>> listdata) {
        this.list.clear();
        this.list = listdata;

        this.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_kucun, null);
            holder = new ViewHolder(convertView);
            int with = (int) ((SomeInfoData.with - (5 * SomeInfoData.Dp2px(context, 10))) / 2);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(with, with-10);
            convertView.setLayoutParams(params);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        String name = list.get(position).get("name").toString();
        holder.txtNameItemGirdhome.setText(list.get(position).get("name").toString());
        //holder.txtNumsItemGirdhome.setText(list.get(position).get("nums").toString() + "件");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).get("name").toString().equals("添加")) {
                    Intent intent = new Intent(context, AddModolActivity.class);

                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.txt_name_item_girdkucun)
        TextView txtNameItemGirdhome;
        @InjectView(R.id.txt_nums_item_girdprodukucun)
        TextView txtNumsItemGirdhome;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
