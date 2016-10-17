package com.liu.glidedemo.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    private ImageView mIv5;
    private ImageView mIv6;
    private ImageView mIv7;
    private ImageView mIv8;
    private ImageView mIv9;
    private ImageView mIv10;


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
        mIv5 = (ImageView) findViewById(R.id.iv5);
        mIv6 = (ImageView) findViewById(R.id.iv6);
        mIv7 = (ImageView) findViewById(R.id.iv7);
        mIv8 = (ImageView) findViewById(R.id.iv8);
        mIv9 = (ImageView) findViewById(R.id.iv9);
        mIv10 = (ImageView) findViewById(R.id.iv10);

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

        //加载网络gif
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide.with(this).load(gifUrl).placeholder(R.mipmap.pictures_no).into(mIv4);

        //加载资源gif
        Glide.with(this).load(R.mipmap.loading).asGif().placeholder(R.mipmap.pictures_no).into(mIv5);


        //加载本地gif文件
        String gifPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Movies/test.gif";
        //test.gif  animate.gif Starry_Night.mp4 test.mp4

        File gifFile = new File(gifPath);

        Glide.with(this).load(gifFile).placeholder(R.mipmap.ic_launcher).into(mIv6);


        //加载本地小视频 快照，只是快照 并不能播放
        String videoPath =Environment.getExternalStorageDirectory().getAbsolutePath()+"/Movies/test.mp4";;

        File videoFile = new File(videoPath);

        Glide.with(this).load(Uri.fromFile(videoFile)).placeholder(R.mipmap.ic_launcher).into(mIv7);


        //加载缩略图 /storage/emulated/0/MagazineUnlock/magazine-unlock-03-2.3.311-bigpicture_03_20.jpg
        ///storage/emulated/0/MagazineUnlock/magazine-unlock-02-2.3.302-bigpicture_02_70.jpg

        String urlPath="/storage/emulated/0/MagazineUnlock/magazine-unlock-03-2.3.311-bigpicture_03_20.jpg";
        //第一种 设置缩略图比例    先加载缩略图，再加载原图
        Glide.with(this).load(new File(urlPath)).thumbnail(0.1f).centerCrop().placeholder(R.mipmap.pictures_no).into(mIv8);
        //第二

        //先建立一个缩略图对象 先加载缩略图，再加载原图  控件移到第一个方便查看
        DrawableRequestBuilder thumbnailRequest=  Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=2615772929,948758168&fm=21&gp=0.jpg");



        Glide.with(this).load(Uri.fromFile(videoFile)).thumbnail(thumbnailRequest).centerCrop().placeholder(R.mipmap.pictures_no).into(mIv9);



        //10 http://h.hiphotos.baidu.com/zhidao/pic/item/0eb30f2442a7d9334f268ca9a84bd11372f00159.jpg
        String url10 = "http://h.hiphotos.baidu.com/zhidao/pic/item/0eb30f2442a7d9334f268ca9a84bd11372f00159.jpg";
        Glide.with(this)
                .load(url10)
                .placeholder(R.mipmap.pictures_no)
                .override(300,300)
                .skipMemoryCache(true)   //跳过内存缓存，不在内存中保存图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(mIv10);



    }
}
