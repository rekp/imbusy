package com.patrickrek.imbusy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Amux on 6/18/2016.
 */
public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Restore any saved state
        super.onCreate(savedInstanceState);

        // Set content view
        setContentView(R.layout.activity_settings);

        // Initialize UI elements


        // Link UI elements to code

        //
        loadData();
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

    }

    private void loadData() {

    }

}
