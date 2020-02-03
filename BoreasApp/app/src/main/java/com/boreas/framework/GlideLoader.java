package com.boreas.framework;

import android.widget.ImageView;

import com.boreas.App;
import com.bumptech.glide.Glide;
import com.lcw.library.imagepicker.utils.ImageLoader;

public class GlideLoader implements ImageLoader {

    @Override
    public void loadImage(ImageView imageView, String imagePath) {
        Glide.with(imageView.getContext()).load(imagePath).into(imageView);
    }

    @Override
    public void loadPreImage(ImageView imageView, String imagePath) {
        //大图加载
        Glide.with(imageView.getContext()).load(imagePath).into(imageView);
    }

    @Override
    public void clearMemoryCache() {
        //清理缓存
        Glide.get(App.getInstance().getApplicationContext()).clearMemory();
    }
}
