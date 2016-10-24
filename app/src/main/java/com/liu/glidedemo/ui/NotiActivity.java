package com.liu.glidedemo.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.Target;
import com.liu.glidedemo.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class NotiActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 110;
    private ImageView mIvEc1;
    private ImageView mIvEc2;
    private ImageView mIvEc3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        initView();
        initData();
        initListener();
    }

    private void initView() {

        //创建通知布局
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.remoteview_notification);


        remoteViews.setImageViewResource(R.id.remoteview_notification_icon,R.mipmap.ic_launcher);

        remoteViews.setTextViewText(R.id.remoteview_notification_headline,"头部分标题");
        remoteViews.setTextViewText(R.id.remoteview_notification_short_message,"二级标题内容");


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext())
                                   .setSmallIcon(R.mipmap.ic_launcher)
                                    .setTicker("ticker")
                                    .setContentText("contenttext")
                                    .setContentTitle("contenttitle")
                                    .setContent(remoteViews)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH);

        Notification notification = builder.build();

        if(Build.VERSION.SDK_INT>16){
            notification.bigContentView = remoteViews;
        }



        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID,notification);
        //这个类是Glide自带的
        NotificationTarget notificationTarget =
                new NotificationTarget(getApplicationContext(),remoteViews,R.id.remoteview_notification_icon,notification,NOTIFICATION_ID);


        String notUrl = "http://photo.enterdesk.com/2009-4-21/200901241609531378.png";
        Glide.with(getApplicationContext())
                .load(notUrl)
                .asBitmap()
                .into(notificationTarget);


        initImageView();
    }

    private void initImageView() {


        mIvEc1 = (ImageView) findViewById(R.id.ivEc1);
        mIvEc2 = (ImageView) findViewById(R.id.ivEc2);
        mIvEc3 = (ImageView) findViewById(R.id.ivEc3);

    }

    private void initData() {
//        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.custom_view_futurestudio);
//
//        FSAppWidgetProvider.pushWidgetUpdate(getApplicationContext(),remoteViews);


        String testUrl ="http://photo.enterdesk.com/2009-4-21/2009012416095313783333.png";

        RequestListener<String,GlideDrawable> listener = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {


                e.printStackTrace();
                Log.d("vivi", "onException: "+e.getMessage());


                /**
                 * false 时error中的才会显示
                 * true 时error中的就不显示
                 */
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        };

        Glide.with(this)
                .load(testUrl)
                .listener(listener)
                .error(R.mipmap.pictures_no)
                .into(mIvEc1);


        String trUrl = "http://pic.58pic.com/58pic/16/83/91/40558PICYDj_1024.jpg";


        Glide.with(this)
                .load(trUrl)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(mIvEc2);



        Glide.with(this)
                .load(trUrl)
                .bitmapTransform(new BlurTransformation(this))
                .into(mIvEc3);



    }

    private void initListener() {




    }


}
