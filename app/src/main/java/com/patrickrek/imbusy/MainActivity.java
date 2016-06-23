package com.patrickrek.imbusy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends BaseActivity {

    protected EditText editTextGeneralResponse;
    private static String generalResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Restore any saved state
        super.onCreate(savedInstanceState);

        // Set content view
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextGeneralResponse = (EditText) findViewById(R.id.editTextGeneralResponse);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleGeneralResponse);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                }
            }
        });

        //
        loadData();
    }

    // Override to remove back button in toolbar
    @Override
    protected void configureToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (useToolbar()) {
                setSupportActionBar(toolbar);
            }
            else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void saveData() {
        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("General Response", editTextGeneralResponse.getText().toString());
        editor.apply();

        generalResponse = editTextGeneralResponse.getText().toString();
    }

    private void loadData() {
        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editTextGeneralResponse.setText(sp.getString("General Response", ""), EditText.BufferType.EDITABLE);
    }

    public static String getGeneralResponse(){

        return generalResponse;
    }

}





