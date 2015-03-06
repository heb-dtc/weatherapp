package com.heb.eguzkia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_see_location) {
            return openMapWithCurrentLocation();
        } else if (id == R.id.action_see_city) {
            startActivity(new Intent(this, CityActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean openMapWithCurrentLocation() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String city = prefs.getString(getString(R.string.preference_location_key), getString(R.string.preference_location_default));
        Uri uri = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", city)
                .build();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            return true;
        } else {
            return false;
        }
    }
}
