package com.zf.compey.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zf.compey.views.BannerLayout;


/**
 * ( 加载图片)Created by ${Ethan_Zeng} on 2017/11/16.
 */

public class GlideImageLoader implements BannerLayout.ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {

        Glide.with(context).load(path).centerCrop().into(imageView);

    }
}
