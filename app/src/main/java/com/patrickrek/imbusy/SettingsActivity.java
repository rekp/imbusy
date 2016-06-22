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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
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

    }

    private void loadData() {

    }

}
