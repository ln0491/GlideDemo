package com.liu.glidedemo.view;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.liu.glidedemo.R;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/19 0019 11:14
 */
public class FSAppWidgetProvider extends AppWidgetProvider {

    private AppWidgetTarget appWidgetTarget;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.custom_view_futurestudio);

        appWidgetTarget = new AppWidgetTarget( context, rv, R.id.custom_view_image, appWidgetIds );

        String url = "http://photo.enterdesk.com/2009-4-21/200901241609531378.png";
        Glide
                .with( context.getApplicationContext() ) // safer!
                .load( url)
                .asBitmap()
                .into( appWidgetTarget );

        pushWidgetUpdate(context, rv);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews rv) {
        ComponentName    myWidget = new ComponentName(context, FSAppWidgetProvider.class);
        AppWidgetManager manager  = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, rv);
    }
}