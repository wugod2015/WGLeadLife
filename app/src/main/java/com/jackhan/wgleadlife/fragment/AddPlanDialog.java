package com.jackhan.wgleadlife.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.utils.ToastUtils;

/**
 * Created by Administrator on 2016/9/30.
 */
public class AddPlanDialog extends DialogFragment {
    public AddPlanDialog.OnAddPlanClickListener getOnAddPlanClickListener() {
        return onAddPlanClickListener;
    }

    public void setOnAddPlanClickListener(AddPlanDialog.OnAddPlanClickListener onAddPlanClickListener) {
        onAddPlanClickListener = onAddPlanClickListener;
    }

    OnAddPlanClickListener onAddPlanClickListener;

    public interface OnAddPlanClickListener {
        public void onAdd(String title, String content);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_plan, null);
        final EditText titleET = (EditText) view.findViewById(R.id.titleET);
        final EditText contentET = (EditText) view.findViewById(R.id.contentET);
        builder.setTitle(R.string.add_plan).setView(view).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = titleET.getText().toString();
                String content = contentET.getText().toString();
                if(TextUtils.isEmpty(title)&&TextUtils.isEmpty(content)){
                    ToastUtils.showShortToast("不能为空");
                    return;
                }
                if (TextUtils.isEmpty(title)) {
                    titleET.setText(content);
                    title = titleET.getText().toString();
                }
                onAddPlanClickListener.onAdd(title, content);
            }
        }).setNegativeButton("Cancel", null);
        return builder.create();
    }
}
