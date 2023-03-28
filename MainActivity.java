import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView weatherText;

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherText = findViewById(R.id.weather_text);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String latitude = String.valueOf(location.getLatitude());
                String longitude = String.valueOf(location.getLongitude());

                // Get weather information for current location
                WeatherFetcher weatherFetcher = new WeatherFetcher();
                weatherFetcher.fetchWeather(latitude, longitude, new WeatherFetcher.Callback() {
                    @Override
                    public void onWeatherFetched(JSONObject weather) {
                        // Display weather information in text view
                        try {
                            String city = weather.getString("name");
                            String description = weather.getJSONArray("weather").getJSONObject(0).getString("description");
                            String temperature = weather.getJSONObject("main").getString("temp");
                            String updatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(weather.getLong("dt") * 1000));

                            String weatherString = String.format("%s\n%s\n%s°F\nUpdated at %s", city, description, temperature, updatedTime);
                            weatherText.setText(weatherString);
                        } catch (JSONException e) {
                            Log.e(TAG, "Error parsing JSON response", e);
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d(TAG, "Location provider disabled: " + provider);
                Toast.makeText(MainActivity.this, "Location provider disabled: " + provider, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d
