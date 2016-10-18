package com.liu.glidedemo.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.liu.glidedemo.R;
import com.liu.glidedemo.view.FutureStudioView;

public class TargetActivity extends AppCompatActivity {

    private ImageView mIvt1;
    private ImageView mIvt2;
    private ImageView mIvt3;
    private ImageView mIvt4;
    private ImageView mIvt5;
    private FutureStudioView mFutrueStudioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        initView();
        initData();
    }



    private void initView() {


        mIvt1 = (ImageView) findViewById(R.id.ivt1);
        mIvt2 = (ImageView) findViewById(R.id.ivt2);
        mIvt3 = (ImageView) findViewById(R.id.ivt3);
        mIvt4 = (ImageView) findViewById(R.id.ivt4);
        mIvt5 = (ImageView) findViewById(R.id.ivt5);
        //自定义的view
        mFutrueStudioView = (FutureStudioView) findViewById(R.id.futrueStudioView);
    }

    private void initData() {


        String url1 = "http://img4.imgtn.bdimg.com/it/u=21385237,1665284751&fm=21&gp=0.jpg";

        /**
         * 代码的第一部分，创建一个target字段对象，里面定义了个方法，
         * 这个方法一旦Glide加载和处理完图片将会被调用。
         * 回调方法传回Bitmap作为参数，你可以在你所需要用的地方随意使用这个Bitmap对象。
         */
        SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                //设置图片显示
                mIvt1.setImageBitmap(resource);
            }
        };

        Glide.with(this).load(url1)
                .asBitmap() //作为bitmap显示
                .into(target); //显示在这个对象

        String url2 = "http://scs.ganjistatic1.com/gjfs01/M00/89/F4/CgEHklWmXzvov6K-AABrKnXXM9U624_600-0_6-0.jpg";


        /**
         *  通过构造方法设置图片大小
         */
        SimpleTarget<Bitmap> target2 = new SimpleTarget<Bitmap>(300,300) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                //设置图片显示
                mIvt2.setImageBitmap(resource);
            }
        };

        Glide.with(this).load(url2)
                .asBitmap() //作为bitmap显示
                .into(target2); //显示在这个对象

        //ViewTarget


        String targetUrl = "http://photo.enterdesk.com/2009-4-21/200901241609531378.png";

        /**
         * ViewTarget设置泛型为 自定义的控件 FutureStudioView，
         * 同时构造方法中也传入()
         */
        ViewTarget<FutureStudioView,GlideDrawable> viewTarget = new ViewTarget<FutureStudioView, GlideDrawable>(mFutrueStudioView) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                //通过暴露的方法来设置图片
                this.view.setImage(resource.getCurrent());

            }
        };

        Glide.with(this)
                .load(targetUrl)
                .centerCrop()
                .override(300,300)
                //显示自定义控件中
                .into(viewTarget);



    }
}
