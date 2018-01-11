package com.zf.compey.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zf.compey.views.BannerLayout;


/**
 * ( 加载图片)Created by ${Ethan_Zeng} on 2017/11/6.
 */

public class PicassoImageLoader implements BannerLayout.ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Picasso.with(context).load(path).into(imageView);
    }
}
