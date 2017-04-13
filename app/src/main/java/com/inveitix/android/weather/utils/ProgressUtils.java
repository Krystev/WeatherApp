package com.inveitix.android.weather.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.inveitix.android.weather.App;
import com.inveitix.android.weather.R;

import javax.inject.Inject;

public class ProgressUtils {

    private ProgressDialog dialog;
    private Context context;

    public ProgressUtils(Context context) {
        this.context = context;
    }

    private void initProgress() {
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(context.getString(R.string.loading));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void showProgress(){
        initProgress();
    }

    public void hideProgress() {
        dialog.dismiss();
    }
}
