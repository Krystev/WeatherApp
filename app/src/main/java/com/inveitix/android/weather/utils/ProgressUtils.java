package com.inveitix.android.weather.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.inveitix.android.weather.R;

public class ProgressUtils {
    private ProgressDialog dialog;

    private void initProgress(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(context.getString(R.string.loading));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void showProgress(Context context){
        initProgress(context);
    }

    public void hideProgress() {
        dialog.dismiss();
    }
}
