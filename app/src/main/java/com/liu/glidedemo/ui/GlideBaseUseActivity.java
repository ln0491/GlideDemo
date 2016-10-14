package com.liu.glidedemo.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liu.glidedemo.R;

import java.io.File;

/**
 * Glide入门教程一（基本使用）
 */
public class GlideBaseUseActivity extends AppCompatActivity {

    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    private ImageView mIv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {

        mIv1 = (ImageView) findViewById(R.id.iv1);
        mIv2 = (ImageView) findViewById(R.id.iv2);
        mIv3 = (ImageView) findViewById(R.id.iv3);
        mIv4 = (ImageView) findViewById(R.id.iv4);

    }

    private void initData() {


       Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=2615772929,948758168&fm=21&gp=0.jpg").into(mIv1);


        //加载资源
        Glide.with(this).load(R.mipmap.test).into(mIv2);



        //本地图片路径 /storage/emulated/0/1470634823290.jpg
        String path ="/storage/emulated/0/1470634823290.jpg";

        File file = new File(path);

       // Glide.with(this).load(file).into(mIv3);
        Uri uri = Uri.fromFile(file);


       Glide.with(this).load(uri).into(mIv3);

        //Picasso.with(this).load(file).into(mIv3);



    }
}
