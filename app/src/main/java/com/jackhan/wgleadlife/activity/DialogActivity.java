package com.jackhan.wgleadlife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.bean.LeadPlan;
import com.jackhan.wgleadlife.control.LeadPlanManager;
import com.jackhan.wgleadlife.utils.ToastUtils;
import com.jackhan.wgleadlife.utils.rxbus.RxBus;
import com.jackhan.wgleadlife.utils.rxbus.RxEvent;

import java.util.Date;

public class DialogActivity extends AppCompatActivity {
    int appWidgetId;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appWidgetId = getIntent().getIntExtra("AppWidgetId", -1);
        setContentView(R.layout.activity_dialog);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void onCancelClick(View v) {

        finish();
    }

    public void onOkClick(View v) {
        LeadPlanManager.getInstance(this).addPlan("", editText.getText().toString());
        finish();
    }

}
