package com.heb.eguzkia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heb.eguzkia.model.Weather;
import com.heb.eguzkia.model.WeatherResponse;
import com.heb.eguzkia.network.RestClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CityActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private TextView cityInfoView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_city, container, false);

            cityInfoView = (TextView) rootView.findViewById(R.id.city_info);

            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();
            updateWeather();
        }

        private void updateWeather() {
            RestClient.get().getWeather("Paris,fr", new Callback<WeatherResponse>() {
                @Override
                public void success(WeatherResponse weatherResponse, Response response) {
                    List<Weather> weatherList = weatherResponse.getWeather();
                    cityInfoView.setText(weatherList.get(0).getmain());
                }

                @Override
                public void failure(RetrofitError error) {
                    cityInfoView.setText("Can't retrieve weather at the moment");
                }
            });
        }
    }
}
