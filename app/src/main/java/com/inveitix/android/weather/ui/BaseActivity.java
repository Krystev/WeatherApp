package com.inveitix.android.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.inveitix.android.weather.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected boolean showBackupNavigation = true;
    protected boolean useToolbar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        onViewCreated();
    }

    protected abstract void onViewCreated();

    protected abstract int getLayout();

    protected void setToolBarAndUpNavigation(){
        if (useToolbar){
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }
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
                    //if a parent is not specified in the Manifest up nav behaves the same as back press.
                    onBackPressed();
                    return true;
                }
                NavUtils.navigateUpFromSameTask(this); //finishes the source activity and intent is flagged activity_clear_top.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
