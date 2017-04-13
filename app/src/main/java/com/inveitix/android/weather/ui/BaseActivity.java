package com.inveitix.android.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.inveitix.android.weather.App;
import com.inveitix.android.weather.di.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected boolean showBackupNavigation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent(this).inject(this);
        doInject(App.getAppComponent(this));

        setContentView(getLayout());
        ButterKnife.bind(this);
        onViewCreated();
    }

    protected abstract void doInject(AppComponent component);

    protected abstract void onViewCreated();

    protected abstract int getLayout();

    protected void setToolBarAndUpNavigation(){

        if (showBackupNavigation){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                if(intent == null) {
                    onBackPressed();
                    return true;
                }
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
