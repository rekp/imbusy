package com.patrickrek.imbusy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected boolean useToolbar() {
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        configureToolbar(view);
        super.setContentView(view);
    }

    protected void configureToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (useToolbar()) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }
}
