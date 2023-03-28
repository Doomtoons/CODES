import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiClient {

    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String location, @Query("appid") String apiKey);

}
