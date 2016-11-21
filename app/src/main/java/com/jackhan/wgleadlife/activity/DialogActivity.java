package com.jackhan.wgleadlife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jackhan.wgleadlife.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void onCancelClick(View v) {

        finish();
    }

    public void onOkClick(View v) {

        finish();
    }
}
