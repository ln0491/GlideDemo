package com.liu.glidedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liu.glidedemo.R;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/14 0014 11:09
 */
public class GlideListUseAdapter extends RecyclerView.Adapter<GlideListUseAdapter.ViewHolder> {


    Context      mContext;
    String [] mDatas;

    LayoutInflater mInflater;

    public GlideListUseAdapter(Context context, String [] datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater= LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.item_glide_list_use,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String url = mDatas[position];


        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height   = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
        Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width,height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                .animate(R.anim.my_alpha)
                .centerCrop()
                .fitCenter()
                 .into(holder.mIv); //显示在哪个控件中


    }

    @Override
    public int getItemCount() {
        if(mDatas!=null){

           return mDatas.length;
        }
        return 0;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIv;
        public ViewHolder(View itemView) {

            super(itemView);

            mIv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
