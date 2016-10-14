package com.liu.glidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liu.glidedemo.ui.GlideBaseUseActivity;
import com.liu.glidedemo.ui.GlideListUseActivity;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnBaseUse;
    private Button mBtnListAndErrorUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        initListener();
    }

    private void initView() {


        mBtnBaseUse = (Button) findViewById(R.id.btnBaseUse);
        mBtnListAndErrorUse = (Button) findViewById(R.id.btnListAndErrorUse);


    }

    private void initListener() {
        mBtnBaseUse.setOnClickListener(this);
        mBtnListAndErrorUse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBaseUse:
                Intent baseUseIntent = new Intent(this, GlideBaseUseActivity.class);
                startActivity(baseUseIntent);
                break;
            case R.id.btnListAndErrorUse:
                Intent listUseIntent = new Intent(this, GlideListUseActivity.class);
                startActivity(listUseIntent);
                break;
        }
    }
}
