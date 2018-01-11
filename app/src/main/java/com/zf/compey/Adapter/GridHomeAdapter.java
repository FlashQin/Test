package com.zf.compey.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zf.compey.Activitys.AddModolActivity;
import com.zf.compey.R;
import com.zf.compey.UserInfoBean.SomeInfoData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ( 首页Adapter)Created by ${Ethan_Zeng} on 2017/11/16.
 */

public class GridHomeAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list = new ArrayList<>();

    public GridHomeAdapter(Context cotent, List<Map<String, Object>> listdata) {
        this.context = cotent;
        this.list = listdata;


    }

    /**
     * 刷新列表数据
     *
     * @param listdata
     */
    public void setList(List<Map<String, Object>> listdata) {
       // this.list.clear();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_girdview_fragment_home, null);
            holder = new ViewHolder(convertView);
            //设置gridview item宽高自动适应各种屏幕
            int width = (int) ((SomeInfoData.with - (5 * SomeInfoData.Dp2px(context, 5))) / 3);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(width, width-10);
            convertView.setLayoutParams(params);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String name = list.get(position).get("name").toString();
        holder.txtNameItemGirdhome.setText(list.get(position).get("name").toString());

        String dd=list.get(position).get("nums").toString();
        if (!list.get(position).get("nums").toString().equals("0.0")){
            holder.txtNumsItemGirdhome.setText(list.get(position).get("nums").toString() + "件");
        }
        if (name.equals("添加")){
            holder.txtNumsItemGirdhome.setText("");
        }

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
    
        @InjectView(R.id.txt_name_item_girdhome)
        TextView txtNameItemGirdhome;
        @InjectView(R.id.txt_nums_item_girdhome)
        TextView txtNumsItemGirdhome;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
